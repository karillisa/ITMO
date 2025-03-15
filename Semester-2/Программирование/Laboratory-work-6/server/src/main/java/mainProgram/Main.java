package mainProgram;

import classes.*;
import collection.CollectionManager;
import command.CommandManager;
import command.SaveCommand;
import objectResAns.ObjectResAns;
import statics.ConsoleManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private static int port = 3909;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean b = false;
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        ServerSocket serverSocket = null;

        String filename = System.getenv("BD"); //(BD=Datas/baza.csv) environment variable  if in IntelliJ*/
        CollectionManager collectionManager = new CollectionManager();
        CommandManager cmd = new CommandManager();
        ConsoleManager console = new ConsoleManager();

        SaveCommand sv = new SaveCommand("save");

        TreeSet<SpaceMarine> mySet = new TreeSet<>();
        mySet = collectionManager.getAllData(filename);

        String allRes = "All data has been downloaded from the database!\n";

        ArrayList<Socket> listSc = new ArrayList<>();

        TreeSet<SpaceMarine> finalMySet = mySet;
        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            if(s.nextLine().equals("exit")){
                sv.doo("save " + filename, finalMySet);
                System.out.println("Exit!...");
                System.exit(0);
            }

            if(s.nextLine().equals("save")){
                sv.doo("save " + filename, finalMySet);
                System.out.println("Exit!...");
                System.exit(0);
            }
        }).start();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The server is running.");
            System.out.println("Waiting for the client to connect...");
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }

        while (true) {
            try {
                // Ожидаем подключение клиента
                if (!b) {
                    socket = serverSocket.accept();
                    listSc.add(socket);
                    System.out.println("The client " + socket.getInetAddress() + " is connected.");
                    // Получаем потоки ввода-вывода для обмена данными с клиентом
                    in = new ObjectInputStream(socket.getInputStream());
                    out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectResAns response = new ObjectResAns(allRes, true);
                    out.writeObject(response);
                    b = true;
                }
            }catch (IOException e) {
                continue;
            }
            try {
                while (b) {
                    ObjectResAns clientRequest = null;
                    // Читаем запрос от клиента
                    clientRequest = (ObjectResAns) in.readObject();
                    System.out.println("Request from the client: " + clientRequest.getResTesxt());
                    ObjectResAns response = null;

                    if (clientRequest.getResTesxt().split(" ")[0].equals("update") && clientRequest.getResTesxt().split(" ").length >= 2 && clientRequest.getResTesxt().split(" ")[2].equals("tmp")) {
                        ObjectResAns finalClientRequest = clientRequest;
                        boolean bb = false;
                        if(mySet.stream().filter(p -> p.getId() == Long.parseLong(finalClientRequest.getResTesxt().split(" ")[1])).count() >= 1) {
                            bb = true;
                        }

                        try {
                            out.writeObject(new ObjectResAns(finalClientRequest.getResTesxt(), bb));
                            clientRequest = (ObjectResAns) in.readObject();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    // Создаем и отправляем объект Res в ответ клиенту
                    try {
                        response = cmd.commandsEditor(mySet, clientRequest.getResTesxt());

                    }catch (Exception e){
                        response = new ObjectResAns("Command error\n", false);
                    }
                    //ObjectResAns response = new ObjectResAns(clientRequest.getResTesxt(), true);
                    out.writeObject(response);
                }
            }catch (Exception e){
                System.err.println("The client: " + socket.getInetAddress() + " is disconnected!");
                sv.doo("save " + filename, finalMySet);
                listSc.remove(socket);
                try {
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                b = false;
            }
        }
    }
}