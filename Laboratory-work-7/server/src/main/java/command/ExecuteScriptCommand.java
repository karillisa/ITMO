package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteScriptCommand extends AbsCommand{
    public ExecuteScriptCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user){
        String allRes = "";
        Pattern pt = Pattern.compile("\"([^\"]*)\"");
        Matcher mt = pt.matcher(args);
        List<String> dt = new ArrayList<>();
        while(mt.find()){
            dt.add(mt.group(1));
        }
        CommandManager cmd = new CommandManager();

        for (String tmp: dt) {
            if(!tmp.equals("Recurse!") && !tmp.equals("execute_script")) {
                try {
                    allRes = allRes + cmd.commandsEditor(mySet, tmp, user).getResTesxt() + "\n";
                }catch (Exception e){
                    allRes = allRes + "Command Error!\n\n";
                }
            }
            if(tmp.equals("Recurse!")){
                allRes = allRes + "Recurse!\n\n";
            }
        }
        return new ObjectResAns(allRes, true, user);
    }

    @Override
    public String des(){
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
