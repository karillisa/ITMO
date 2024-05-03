package command;


import classes.*;

import java.util.Scanner;
import java.util.TreeSet;

public class AddCommand extends AbsCommand{
    public AddCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String s, TreeSet<SpaceMarine> mySet){
        ObjectCreator objectCreator = new ObjectCreator();
        SpaceMarine sNew = objectCreator.createObjectFromConsole(s);
        mySet.add(sNew);
        System.out.println("Object " + sNew.getName() + " added!");
        return true;
    }

    @Override
    public String des(){
        return "add {element} : add a new item to the collection";
    }
}
