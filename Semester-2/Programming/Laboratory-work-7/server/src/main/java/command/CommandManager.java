package command;

import classes.SpaceMarine;
import mainProgram.Server;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CommandManager {
    public Map<String, Command> listOfCommands; // Карта для хранения списка команд
    public static ConcurrentMap<String, Boolean> myMapUpdate = new ConcurrentHashMap<>(); // ConcurrentMap для отслеживания обновлений коллекции

    // Конструктор CommandManager, инициализирует карту команд
    public CommandManager(){
        listOfCommands = new LinkedHashMap<>();
        // Добавление команд в список (карта)
        listOfCommands.put("help", new HelpCommand("help"));
        listOfCommands.put("info", new InfoCommand("info"));
        listOfCommands.put("show", new ShowCommand("show"));
        listOfCommands.put("clear", new ClearCommand("clear"));
        listOfCommands.put("save", new SaveCommand("save"));
        listOfCommands.put("remove_by_id", new RemoveIdCommand("remove_by_id"));
        listOfCommands.put("filter_starts_with_name", new FilterStartsWithNameCommand("filter_starts_with_name"));
        listOfCommands.put("print_field_ascending_health", new PrintFieldAscendingHealthCommand("print_field_ascending_health"));
        listOfCommands.put("add", new AddCommand("add"));
        listOfCommands.put("update", new UpdateByIdCommand("update"));
        listOfCommands.put("count_greater_than_chapter", new CountGreaterThanChapterCommand("count_greater_than_chapter"));
        listOfCommands.put("remove_greater", new RemoveGreaterCommand("remove_greater"));
        listOfCommands.put("remove_lower", new RemoveLowerCommand("remove_lower"));
        listOfCommands.put("execute_script", new ExecuteScriptCommand("execute_script"));
        listOfCommands.put(new AddUserCommand("addUser").getName(), new AddUserCommand("addUser"));
        listOfCommands.put("login", new LogIn("login"));
        listOfCommands.put("logout", new LogOut("logout"));
        listOfCommands.put("user", new User("user"));
    }

    // Метод для редактирования команд
    public ObjectResAns commandsEditor(TreeSet<SpaceMarine> mySet, String line, String user, Connection cnt) throws SQLException, NoSuchAlgorithmException, IOException {
        String[] cmdStr = line.split(" "); // Разделение входной строки на отдельные команды
        ObjectResAns obs = this.listOfCommands.get(cmdStr[0]).doo(line, mySet, user, cnt);  // Выполнение команды и возврат результата

        /*for(Command c: Static.listOfCommand.values()){
            if(c.getName().toString().equals(cmdStr[0])){
                c.doo(mySet, line);
            }
        }*/
        return obs;
    }

    // Метод для добавления записи в myMapUpdate
    public static void addingMap(String name, Boolean b){
        myMapUpdate.put(name, b);
    }

    // Метод для получения значения из myMapUpdate
    public static Boolean getRes(String name){
        return myMapUpdate.get(name);
    }

    // Метод для удаления записи из myMapUpdate
    public static void removeMap(String name){
        myMapUpdate.remove(name);
    }
}