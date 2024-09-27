package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.util.TreeSet;

public class CountGreaterThanChapterCommand extends AbsCommand{
    public CountGreaterThanChapterCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user, Connection cnt){
        String chapterName = args.split(" ")[1];  // Извлечение имени главы (chapterName) из аргументов

        // Проверка, пустая ли коллекция
        if(mySet.isEmpty()){
            System.out.println("Collection is empty!");
            return new ObjectResAns("Collection is empty!", true, user);
        }
        // Проверка корректности аргумента
        if(chapterName == null || chapterName.isEmpty()){
            System.out.println("Arg error!");
            return new ObjectResAns("Arg error!", true, user);
        }

        int count = 0;
        // Подсчет элементов, у которых длина имени главы больше заданного
        for(SpaceMarine s: mySet){
            if(s.getChapter().getName().length() > chapterName.length()){
                count = count + 1;
            }
        }
        // Возвращение результата подсчета
        return new ObjectResAns("Count: " + count, true, user);
    }

    @Override
    public String des(){
        return "count_greater_than_chapter chapter : вывести количество элементов, значение поля chapter которых больше заданного";
    }
}
