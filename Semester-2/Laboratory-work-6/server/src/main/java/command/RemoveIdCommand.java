package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class RemoveIdCommand extends AbsCommand{
    public RemoveIdCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet){
        String[] idS = args.split(" ");
        long id = -111111;
        try {
            id = Long.parseLong(idS[1]);
        }catch (Exception e){
            return new ObjectResAns("Id should be a number!",  true);
        }

        for(SpaceMarine s: mySet){
            if(s.getId() == id){
                mySet.remove(s);

                return new ObjectResAns("Object with id " + id + " deleted!", true);
            }
        }

        return new ObjectResAns("Doesnt find object with id " + id + "!", true);
    }

    @Override
    public String des(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
