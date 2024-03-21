package command;


import classes.SpaceMarine;

import java.util.TreeSet;

public class InfoCommand extends AbsCommand{
    public InfoCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet){
        String allRes = ("id - identification number\n" +
                "name - название\n" +
                "coordinates - координаты\n" +
                "creationDate - дата создания\n" +
                "health\n"+
                "loyal\n" +
                "weaponType\n"+
                "meleeWeapon\n"+
                "chapter"
                );
        System.out.println(allRes);
        System.out.println("Количество Объектов:    " + mySet.size());

        return true;
    }
    @Override
    public String des(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
