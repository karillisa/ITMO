package command;

import classes.SpaceMarine;
import datas.DataParseManager;

import java.util.TreeSet;

public class SaveCommand extends AbsCommand{
    public SaveCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String s, TreeSet<SpaceMarine> mySet){
        DataParseManager dbManager = new DataParseManager();
        try {
            dbManager.writeCsv(mySet, s.split(" ")[1]);

            System.out.println("Коллекция успешно сохранено!");
        }catch (Exception e){
            System.out.println("Ошибка сохранения!");
        }

        return true;
    }

    @Override
    public String des(){
        return "save : сохранить коллекцию в файл";
    }
}
