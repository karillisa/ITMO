package mainProgram;

import bd.BdMng;
import classes.*;
import command.CommandManager;
import datas.BdReader;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server{
    private static int port = 3909;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerSocket serverSocket = null;

        // Инициализация менеджера команд, читателя базы данных и управления базой данных
        CommandManager cmd = new CommandManager();
        BdReader bdReader = new BdReader();
        BdMng dbMng = new BdMng();

        // Установка соединения с базой данных
        Connection cnt = dbMng.cnt();
        if (cnt == null){
            System.out.println("Connection BD Error!");
            System.exit(0);
        }

        // Загрузка всех данных из базы данных в коллекцию
        TreeSet<SpaceMarine> myset = new TreeSet<>();
        myset = bdReader.getAllData(cnt);

        String allres = "All data has been dowloaded from the database!\n";
        ArrayList<Socket> listSc = new ArrayList<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        // Запуск потока для обработки команд сервера
        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            while (true){
                String command = s.nextLine();
                if (command.equals("exit")){
                    System.out.println("Exit!...");
                    System.exit(0);
                }

                if (command.equals("save")){
                    System.out.println("Save ana Exit!...");
                    System.exit(0);
                }
            }
        }).start();

        // Инициализация пулов потоков
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // Запуск сервера
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("The server is running.");
            System.out.println("Waiting for the client to connect...");
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
        // Основной цикл для обработки подключений клиентов
        while (true){
            try{
                // Принятие нового подключения
                Socket socket = serverSocket.accept();
                listSc.add(socket);
                System.out.println("The client " + socket.getInetAddress() + "is connected.");

                // Запуск асинхронной обработки клиента
                TreeSet<SpaceMarine> finalMySet = myset;
                forkJoinPool.submit(() -> handleClient(socket, cmd, finalMySet, allres, lock, cachedThreadPool, listSc, forkJoinPool, bdReader, cnt));
            } catch (IOException e){
                System.err.println("Accept error: " + e.getMessage());
            }
        }
    }

    // Метод для обработки клиента
    private static void handleClient(Socket socket, CommandManager cmd, TreeSet<SpaceMarine> mySet, String allRes, ReadWriteLock lock, ExecutorService cachedThreadPool, ArrayList<Socket> listSc, ForkJoinPool forkJoinPool, BdReader bdReader, Connection cnt){
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){

            // Отправка первоначального ответа клиенту
            ObjectResAns initialResponse = new ObjectResAns(allRes, true, null);
            out.writeObject(initialResponse);

            // Основной цикл для обработки запросов клиента
            while (true){
                ObjectResAns clientRequest = (ObjectResAns) in.readObject();
                System.out.println("Request from the client: " + clientRequest.getResTesxt());

                TreeSet<SpaceMarine> finalMySet = mySet; // Сериализация mySet перед выполнением команды
                Future<ObjectResAns> responseFuture = cachedThreadPool.submit(() -> {   // Запуск задачи для выполнения команды в пуле потоков
                    lock.writeLock().lock(); // Блокировка для обеспечения безопасного многопоточного доступа
                    try{
                        return cmd.commandsEditor(finalMySet, clientRequest.getResTesxt(), clientRequest.getUser(), cnt);
                    } catch (Exception e){
                        return new ObjectResAns("Command Error!", true, clientRequest.getUser());
                    }finally {
                        lock.writeLock().unlock();   // Разблокировка
                    }
                });


                ObjectResAns response = responseFuture.get();

                lock.writeLock().lock();
                try{
                    mySet = bdReader.getAllData(cnt);
                } finally {
                    lock.writeLock().unlock();
                }

                // Отправка ответа клиенту
                forkJoinPool.submit(() -> {
                    try{
                        out.writeObject(response);
                    }catch (IOException e){
                        System.err.println("Error sending response: " + e.getMessage());
                    }
                });
            }
        }catch (IOException | ClassNotFoundException | InterruptedException | ExecutionException | SQLException e){
            System.err.println("Client disconnected or error occurred: " + e.getMessage());
            listSc.remove(socket);
        }
    }
}
