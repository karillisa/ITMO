package command;


import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class ShowCommand extends AbsCommand{
    public ShowCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet){
        String allRes = new String();

        if (!mySet.isEmpty()) {
            for(SpaceMarine o: mySet){
                allRes = allRes + o.toString() + "\n";
            }
        }else{
            allRes = "Collection is empty!";
        }
        return new ObjectResAns(allRes, true);
    }

    @Override
    public String des(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
