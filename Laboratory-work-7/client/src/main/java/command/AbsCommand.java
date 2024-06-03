package command;

import classes.SpaceMarine;

import java.util.TreeSet;

public class AbsCommand implements Command {
    private String name;

    public AbsCommand(String name){
        this.name = name;
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet) {
        return true;
    }

    @Override
    public String des() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
