package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class HelpCommand extends AbsCommand {
    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet){
        StringBuilder allRes = new StringBuilder();
        String allres = "";
        CommandManager cmd = new CommandManager();
        for(Command c : cmd.listOfCommands.values()){
            allres = allres + c.des() + "\n";
        }
        return new ObjectResAns(allres, true);
    }

    @Override
    public String des(){
        return "help : вывести справку по доступным командам";
    }
}
