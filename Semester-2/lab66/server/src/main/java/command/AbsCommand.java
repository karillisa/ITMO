package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class AbsCommand implements Command {
    private String name;

    public AbsCommand(String name){
        this.name = name;
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet) {
        return new ObjectResAns("", true);
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
