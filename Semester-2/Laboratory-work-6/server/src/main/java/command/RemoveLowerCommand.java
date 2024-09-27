package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class RemoveLowerCommand extends AbsCommand{
    public RemoveLowerCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet){
        String[] idS = args.split(" ");
        long id = -111111;
        try {
            id = Long.parseLong(idS[1]);
        }catch (Exception e){
            return new ObjectResAns("Id should be a number!", true);
        }

        String allRess = "";
        for(SpaceMarine s: mySet){
            if(s.getId() < id){
                mySet.remove(s);

                allRess = allRess + "Object with id " + id + " deleted!" + "\n";
            }
        }

        return new ObjectResAns(allRess, true);
    }

    @Override
    public String des(){
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
