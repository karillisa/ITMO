package mainProgram;

import classes.SpaceMarine;
import command.ExecuteScriptCommand;
import command.ObjectCreator;
import objectResAns.ObjectResAns;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ResponseCache;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import command.*;

public class Main {
    private static int port = 3909;
    public static void main(String[] args) throws Exception {
        boolean b = false;
        SocketChannel socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Scanner snr = null;
        int tmp = 0;
        System.out.println("Connecting to the server...");
        Selector selector = null;

        while (true) {
            if(!b) {
                try {
                    // Устанавливаем соединение с сервером
                    socket = SocketChannel.open();
                    socket.configureBlocking(true);
                    socket.connect(new InetSocketAddress("localhost", port));
                    System.out.println("I'm connected to the server " + socket.getRemoteAddress());

                    // Получаем потоки ввода-вывода для обмена данными с сервером
                    out = new ObjectOutputStream(socket.socket().getOutputStream());
                    in = new ObjectInputStream(socket.socket().getInputStream());

                    ObjectResAns serverResponse = (ObjectResAns) in.readObject();
                    System.out.println(serverResponse.getResTesxt());

                    snr = new Scanner(System.in);
                    b = true;
                }catch (Exception e){
                    tmp = tmp + 1;
                    if(tmp >= 99999){
                        System.err.println("Server Error! Reconnecting...");
                        tmp = 0;
                    }
                }
            }

            while (b) {
                try {
                    System.out.print(">>> ");
                    String line = snr.nextLine();
                    ObjectResAns response = null;

                    if(line.equals("exit")){
                        System.exit(0);
                    }

                    if(line.split(" ")[0].equals("add")){
                        ObjectCreator obj = new ObjectCreator();
                        SpaceMarine newObj = obj.createObjectFromConsole(line);

                        response = new ObjectResAns(obj.writeCsv(newObj), true);

                    }else if(line.split(" ")[0].equals("execute_script")){
                        ExecuteScriptCommand ex = new ExecuteScriptCommand();
                        String res = ex.doo(out, in, line);

                        response = new ObjectResAns("execute_script " + res, true);

                    }else if(line.split(" ")[0].equals("update") && line.split(" ").length >= 2){
                        UpdateByIdCommand ex2 = new UpdateByIdCommand();
                        String res = "";

                        try {
                            res = ex2.doo(out, in, line);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                        response = new ObjectResAns(line + " " + res, true);
                    }else {
                        response = new ObjectResAns(line, true);
                    }
                    // Создаем и отправляем объект Res на сервер
                    out.writeObject(response);

                    ObjectResAns serverResponse = null;
                    // Читаем ответ от сервера
                    serverResponse = (ObjectResAns) in.readObject();
                    //System.out.println("Ответ от сервера: " + serverResponse.getResTesxt());
                    System.out.println(serverResponse.getResTesxt());

                }catch (Exception e){
                    System.err.println("Server Error! Reconnecting...");
                    socket.close();
                    b = false;
                }
            }
        }
    }
}