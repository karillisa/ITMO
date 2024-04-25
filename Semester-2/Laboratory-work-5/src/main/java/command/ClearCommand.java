package command;


import classes.SpaceMarine;

import java.util.TreeSet;

public class ClearCommand extends AbsCommand{
    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String s, TreeSet<SpaceMarine> mySet){
        mySet.clear();
        System.out.println("Коллекция успешно очищено!");

        return true;
    }

    @Override
    public String des(){
        return "clear : очистить коллекцию";
    }
}
