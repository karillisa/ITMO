package command;

import classes.SpaceMarine;

import java.util.TreeSet;

public class RemoveLowerCommand extends AbsCommand{
    public RemoveLowerCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet){
        String[] idS = args.split(" ");
        long id = -111111;
        try {
            id = Long.parseLong(idS[1]);
        }catch (Exception e){
            System.out.println("Id should be a number!");
            return false;
        }

        for(SpaceMarine s: mySet){
            if(s.getId() < id){
                mySet.remove(s);

                System.out.println("Object with id " + id + " deleted!");
            }
        }

        return false;
    }

    @Override
    public String des(){
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
