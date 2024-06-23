package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteScriptCommand extends AbsCommand{
    public ExecuteScriptCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user, Connection cnt){
        String allRes = ""; // Перемменная для хранения всех результатов выполнения команд
        Pattern pt = Pattern.compile("\"([^\"]*)\""); // Регулярное выражение для выделения команд в кавычках
        Matcher mt = pt.matcher(args); // Matcher для поиска соответствий
        List<String> dt = new ArrayList<>(); // Список для хранения найденных команд

        //Поиск всех команд в строке аргументов
        while(mt.find()){
            dt.add(mt.group(1));
        }
        CommandManager cmd = new CommandManager();

        // Выполнения каждой команды из списка
        for (String tmp: dt) {
            if(!tmp.equals("Recurse!") && !tmp.equals("execute_script")) {
                try {
                    // Выполнение команды и добавление результата в allRes
                    allRes = allRes + cmd.commandsEditor(mySet, tmp, user, cnt).getResTesxt() + "\n";
                }catch (Exception e){
                    allRes = allRes + "Command Error!\n\n"; // Обработка ошибок выполнения
                }
            }
            if(tmp.equals("Recurse!")){
                allRes = allRes + "Recurse!\n\n"; // Обработка рекурсивного вызова
            }
        }

        // Возвращение результирующего объекта с текстом всех выполненных команд
        return new ObjectResAns(allRes, true, user);
    }

    @Override
    public String des(){
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
