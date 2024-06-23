package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.util.TreeSet;

public class HelpCommand extends AbsCommand {
    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user, Connection cnt){
        StringBuilder allRes = new StringBuilder(); // Переменная для хранения результата
        String allres = ""; // Строка для накопления описаний команд
        CommandManager cmd = new CommandManager();

        // Проход во всем командам и добавление их описаний в строку
        for(Command c : cmd.listOfCommands.values()){
            allres = allres + c.des() + "\n";
        }
        return new ObjectResAns(allres, true, user);  // Возвращение результирующего объекта с описаниями всех команд
    }

    @Override
    public String des(){
        return "help : вывести справку по доступным командам";
    }
}
