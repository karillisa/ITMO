package command;

import classes.SpaceMarine;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExecuteScriptCommand extends AbsCommand{
    public ExecuteScriptCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet){
        String[] idS = args.split(" ");
        String filename = idS[1];
        ExecuteScriptCommand ex = new ExecuteScriptCommand("execute_script");
        CommandManager cmd = new CommandManager();

        try {
            Scanner sc = new Scanner(new File("Scripts/" + filename));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.split(" ").length >= 2 && line.split(" ")[0].equals("execute_script") && line.split(" ")[1].equals(filename)) {
                    System.out.println("Рекурсия!");
                } else if(line.split(" ").length >= 2 && line.split(" ")[0].equals("execute_script")){
                    ex.doo(line, mySet);
                }
                else if (!line.isEmpty()) {
                    cmd.listOfCommands.get(line.split(" ")[0]).doo(line, mySet);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка скрипт файла!");
            return false;
        }
    }

    @Override
    public String des(){
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
