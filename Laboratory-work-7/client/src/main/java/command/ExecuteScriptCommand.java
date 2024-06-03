package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ExecuteScriptCommand{

    public String doo(ObjectOutputStream out, ObjectInputStream in, String args, String user){
        String[] idS = args.split(" ");
        String filename = idS[1];
        ExecuteScriptCommand ex = new ExecuteScriptCommand();
        String allRes = "";

        try {
            Scanner sc = new Scanner(new File("Scripts/" + filename));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.split(" ").length >= 2 && line.split(" ")[0].equals("execute_script") && line.split(" ")[1].equals(filename)) {
                    allRes = allRes + "\"Recurse!\",";
                } else if(line.split(" ").length >= 2 && line.split(" ")[0].equals("execute_script")){
                    allRes = allRes + ex.doo(out, in, (line), user);
                }
                else if (!line.isEmpty()) {
                    try {
                        allRes = allRes + line;
                        out.writeObject(new ObjectResAns(allRes, true, user));
                        allRes = "";
                        ObjectResAns serverResponse = (ObjectResAns) in.readObject();
                        System.out.println(serverResponse.getResTesxt());
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            return allRes;
        } catch (FileNotFoundException e) {
            System.out.println("Error script file!");
            return "Error script file!";
        }
    }

}
