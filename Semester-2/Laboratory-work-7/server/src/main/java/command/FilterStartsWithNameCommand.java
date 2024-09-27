package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class FilterStartsWithNameCommand extends AbsCommand{
    public FilterStartsWithNameCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user, Connection cnt){
        String nameArg = args.split(" ")[1];
        int count = 0;

        // Проверка, что аргумент не пуст
        if(nameArg.isEmpty()){
            return new ObjectResAns("Argument Name should be not null or empty!", true, user);
        }

        // Проход по коллекции и поиск элементов, имя которых начинается с заданной подстроки
        for(SpaceMarine s: mySet){
            if(s.getName().startsWith(nameArg)){
                System.out.println(s.toString());
                count = count + 1;
            }
        }
        // Проверка, нашлись ли такие элементы
        if(count == 0){
            System.out.println("Not found Object with name start with " + nameArg);
            return new ObjectResAns("Not found Object with name start with " + nameArg, true, user);
        }
        return new ObjectResAns("Count : " + count, true, user); // Возвращение результата с количеством найденных элементов и их названия
    }

    @Override
    public String des(){
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
