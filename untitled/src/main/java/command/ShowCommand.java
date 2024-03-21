package command;


import classes.SpaceMarine;

import java.util.TreeSet;

public class ShowCommand extends AbsCommand{
    public ShowCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String s, TreeSet<SpaceMarine> mySet){
        StringBuilder allRes = new StringBuilder();
        if (!mySet.isEmpty()) {
            for(SpaceMarine o: mySet){
                System.out.println(o.toString());
            }
        }else{
            System.out.println("Коллекция Пуста!");
        }
        return true;
    }

    @Override
    public String des(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
