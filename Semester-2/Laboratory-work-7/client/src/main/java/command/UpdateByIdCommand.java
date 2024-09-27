package command;


import classes.*;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

public class UpdateByIdCommand{

    public String doo(ObjectOutputStream out, ObjectInputStream in, String s, String user) throws IOException, ClassNotFoundException {
        boolean b = false;
        String allRes = "";

        if(!b){
            ObjectResAns res = new ObjectResAns(s + " tmp", true, user);

            out.writeObject(res);
            allRes = "";
            ObjectResAns responder = (ObjectResAns) in.readObject();

            if(responder != null){
                ///print a answer
                if(responder.isResAns() == true){
                    b = true;
                }
            }else{
                System.out.println("Server no response!");
            }
        }

        if(b){
            ObjectCreator objectCreator = new ObjectCreator();
            SpaceMarine sNew = objectCreator.createObjectFromConsole(s);

            return writeCsv(sNew);
        }

        return "Not find this element!";
    }

    public String writeCsv(SpaceMarine marine) {
        return marine.getName() + " " + marine.getCoordinates().getX() + " " + marine.getCoordinates().getY() + " " + marine.getHealth() + " " + marine.isLoyal() + " " + marine.getWeaponType().name() + " " + marine.getMeleeWeapon().name() + " " +marine.getChapter().getName() + " " + marine.getChapter().getWorld();
    }
}