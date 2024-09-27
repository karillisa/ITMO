package command;

import classes.SpaceMarine;

import java.util.TreeSet;

public class HelpCommand extends AbsCommand {
    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet){
        StringBuilder allRes = new StringBuilder();
        CommandManager cmd = new CommandManager();
        for(Command c : cmd.listOfCommands.values()){
            System.out.println(c.des());
        }
        return true;
    }

    @Override
    public String des(){
        return "help : вывести справку по доступным командам";
    }
}
