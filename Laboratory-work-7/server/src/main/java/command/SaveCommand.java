package command;

import classes.SpaceMarine;
import datas.DataParseManager;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public class SaveCommand extends AbsCommand{
    public SaveCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user){
        DataParseManager dbManager = new DataParseManager();
        try {
            /*dbManager.writeCsv(mySet, s.split(" ")[1]);*/

            System.out.println("Collection is saved!");
        }catch (Exception e){
            System.out.println("Error in saving!");
        }

        return new ObjectResAns("", true, user);
    }

    @Override
    public String des(){
        return "save : сохранить коллекцию в файл";
    }
}
