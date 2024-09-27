package command;


import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class AddCommand extends AbsCommand{
    public AddCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet){
        ObjectCreator objectCreator = new ObjectCreator();
        SpaceMarine sNew = objectCreator.createObjectFromConsole(s);
        mySet.add(sNew);

        return new ObjectResAns("Object " + sNew.getName() + " added!", true);
    }

    @Override
    public String des(){
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
