package command;


import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class InfoCommand extends AbsCommand{
    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user){
        String allRes = ("id - identification number\n" +
                "name - name of object\n" +
                "coordinates\n" +
                "creationDate\n" +
                "health\n"+
                "loyal\n" +
                "weaponType\n"+
                "meleeWeapon\n"+
                "chapter"
                );
        allRes = allRes + "\n" + "Count of objects:    " + mySet.size();

        return new ObjectResAns(allRes, true, user);
    }
    @Override
    public String des(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
