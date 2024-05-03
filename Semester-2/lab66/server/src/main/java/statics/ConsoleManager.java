package statics;

import classes.SpaceMarine;
import command.CommandManager;

import java.util.Scanner;
import java.util.TreeSet;

public class ConsoleManager {
    public void start(CommandManager cmd, String filename, TreeSet<SpaceMarine> mySet){
        try (Scanner reader = new Scanner(System.in)) {
            String line;
            while (!(line = reader.nextLine()).equals("exit")) {
                try {
                    if(line.equals("save")) {
                        cmd.commandsEditor(mySet,line + " " + filename);
                    }else{
                        cmd.commandsEditor(mySet, line);
                    }
                    System.out.print(">>> ");
                } catch (Exception e) {
                    System.out.println("The command was entered incorrectly");
                    System.out.print(">>> ");
                }
            }
        }
    }
}
