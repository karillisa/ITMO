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

public class Client {
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
        String user = null;

        while (true) {
            if(!b) {
                try {
                    // Устанавливаем соединение с сервером
                    socket = SocketChannel.open();
                    socket.connect(new InetSocketAddress("localhost", port));
                    System.out.println("I'm connected to the server " + socket.getRemoteAddress());

                    // Получаем поток вводы-вывода для обмена данными с сервером
                    out = new ObjectOutputStream(socket.socket().getOutputStream());
                    in = new ObjectInputStream(socket.socket().getInputStream());

                    ObjectResAns serverResponse = (ObjectResAns) in.readObject();
                    System.out.println(serverResponse.getResTesxt());

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
                    snr = new Scanner(System.in);
                    String line = snr.nextLine();
                    ObjectResAns response = null;

                    if(line.equals("exit")){
                        System.exit(0);
                    }

                    if(line.split(" ")[0].equals("add")){
                        if(user != null) {
                            ObjectCreator obj = new ObjectCreator();
                            SpaceMarine newObj = obj.createObjectFromConsole(line);

                            response = new ObjectResAns(obj.writeCsv(newObj), true, user);
                        }else{
                            response = new ObjectResAns("null", false, user);
                            System.out.println("Access Error!");
                        }
                    }else if(line.split(" ")[0].equals("execute_script")){
                        ExecuteScriptCommand ex = new ExecuteScriptCommand();
                        String res = ex.doo(out, in, line, user);

                        response = new ObjectResAns("execute_script " + res, true, user);

                    }else if(line.split(" ")[0].equals("update") && line.split(" ").length >= 2){
                        if(user != null) {
                            UpdateByIdCommand ex2 = new UpdateByIdCommand();
                            String res = "";

                            try {
                                res = ex2.doo(out, in, line, user);
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }

                            response = new ObjectResAns(line + " " + res, true, user);
                        }else{
                            response = new ObjectResAns(line, false, user);
                            System.out.println("Access Error!");
                        }
                    }else {
                        response = new ObjectResAns(line, true, user);
                    }
                    // -> server
                    out.writeObject(response);

                    ObjectResAns serverResponse = null;
                    // <- server
                    serverResponse = (ObjectResAns) in.readObject();
                    //System.out.println("from server: " + serverResponse.getResTesxt());
                    System.out.println(serverResponse.getResTesxt());
                    user = serverResponse.getUser();

                }catch (Exception e){
                    System.err.println("Server Error! Reconnecting...");
                    socket.close();
                    b = false;
                }
            }
        }
    }
}