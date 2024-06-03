package mainProgram;

import classes.*;
import command.CommandManager;
import datas.BdReader;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    private static int port = 3909;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerSocket serverSocket = null;

        CommandManager cmd = new CommandManager();

        BdReader bdReader = new BdReader();

        TreeSet<SpaceMarine> mySet = new TreeSet<>();
        mySet = bdReader.getAllData();

        String allRes = "All data has been downloaded from the database!\n";

        ArrayList<Socket> listSc = new ArrayList<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            while (true) {
                String command = s.nextLine();
                if (command.equals("exit")) {
                    System.out.println("Exit!...");
                    System.exit(0);
                }

                if (command.equals("save")) {
                    System.out.println("Save and Exit!...");
                    System.exit(0);
                }
            }
        }).start();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The server is running.");
            System.out.println("Waiting for the client to connect...");
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                listSc.add(socket);
                System.out.println("The client " + socket.getInetAddress() + " is connected.");

                // Обработка каждого клиента в отдельном потоке
                TreeSet<SpaceMarine> finalMySet = mySet;
                forkJoinPool.submit(() -> handleClient(socket, cmd, finalMySet, allRes, lock, cachedThreadPool, listSc, forkJoinPool, bdReader));
            } catch (IOException e) {
                System.err.println("Accept error: " + e.getMessage());
            }
        }
    }

    private static void handleClient(Socket socket, CommandManager cmd, TreeSet<SpaceMarine> mySet, String allRes,
                                     ReadWriteLock lock, ExecutorService cachedThreadPool, ArrayList<Socket> listSc, ForkJoinPool forkJoinPool, BdReader bdReader) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            ObjectResAns initialResponse = new ObjectResAns(allRes, true, null);
            out.writeObject(initialResponse);

            while (true) {
                ObjectResAns clientRequest = (ObjectResAns) in.readObject();
                System.out.println("Request from the client: " + clientRequest.getResTesxt());

                // Обработка запроса
                TreeSet<SpaceMarine> finalMySet = mySet;
                Future<ObjectResAns> responseFuture = cachedThreadPool.submit(() -> {
                    lock.writeLock().lock();
                    try {
                        return cmd.commandsEditor(finalMySet, clientRequest.getResTesxt(), clientRequest.getUser());
                    } catch (Exception e) {
                        return new ObjectResAns("Command Error!", true, clientRequest.getUser());
                    } finally {
                        lock.writeLock().unlock();
                    }
                });

                ObjectResAns response = responseFuture.get();

                // Обновление mySet после обработки запроса
                lock.writeLock().lock();
                try {
                    mySet = bdReader.getAllData();
                } finally {
                    lock.writeLock().unlock();
                }

                // Отправка ответа
                forkJoinPool.submit(() -> {
                    try {
                        out.writeObject(response);
                    } catch (IOException e) {
                        System.err.println("Error sending response: " + e.getMessage());
                    }
                });
            }
        } catch (IOException | ClassNotFoundException | InterruptedException | ExecutionException | SQLException e) {
            System.err.println("Client disconnected or error occurred: " + e.getMessage());
            listSc.remove(socket);
        }
    }
}
