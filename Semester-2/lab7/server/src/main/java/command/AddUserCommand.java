package command;

import bd.BdMng;
import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.TreeSet;

public class AddUserCommand extends AbsCommand{

    public String name = "addUser";

    public AddUserCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) throws SQLException, NoSuchAlgorithmException, IOException {

        // Разделяем входную строку на аргументы
        String[] arg = s.split(" ");
        if(arg.length >= 3) {
            String name = arg[1]; // Логин пользоавтеля
            String psswd = arg[2]; // Пароль пользователя

            try {
                // Проверка, существует ли уже пользователь с таким логином
                String sqlStr = "SELECT COUNT(*) FROM users WHERE login = ?";
                PreparedStatement statement = cnt.prepareStatement(sqlStr);
                statement.setString(1, name);
                ResultSet res = statement.executeQuery();

                // Если пользователь не найден, добавляем его
                if (res.next() && res.getInt(1) == 0) {
                    String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
                    statement = cnt.prepareStatement(sql);
                    statement.setString(1, name);

                    // Хэшируем пароль алгоритмом MD5
                    String passwordToHash = psswd;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] passwordBytes = passwordToHash.getBytes();
                    byte[] hash = md.digest(passwordBytes);
                    String passwordHash = bytesToHex(hash);

                    statement.setString(2, passwordHash);  // Устанавливаем хэшированный пароль в SQL-запрос
                    statement.executeUpdate();

                    // Возвращаем успешный результат
                    return new ObjectResAns("User " + name + " added!\n", true, user);
                } else {
                    // Возвращаем сообщение, если пользователь уже существует
                    return new ObjectResAns("User " + name + " already added!\n", false, user);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Возвращаем сообщение об ошибке подключения к базе данных
                return new ObjectResAns("Bd Connection Error", false, user);
            }
        }else{
            // Возвращаем сообщение, если логин или пароль не указаны
            return new ObjectResAns("Username or password is not typing!\n", false, user);
        }
    }

    @Override
    public String des() {
        return "addUser: adding new user";
    }

    @Override
    public String getName() {
        return this.name;
    }

    // Метод для преобразования байтов в шестнадцатеричную строку
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}