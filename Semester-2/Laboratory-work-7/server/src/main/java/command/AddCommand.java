package command;


import bd.BdMng;
import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.sql.*;
import java.util.TreeSet;

public class AddCommand extends AbsCommand{
    public AddCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user) {
        ObjectCreator objectCreator = new ObjectCreator();
        SpaceMarine obj = objectCreator.createObjectFromConsole(s);

        BdMng bd = new BdMng();
        Integer id = null;

        try {
            id = getUserIdByLogin(bd.cnt(), user);
        }catch (Exception e){
            System.out.println("Id error");
        }

        obj.setUserID(id);

        String sql = "INSERT INTO labBd (name, x, y, creation_date, health, loyal, weapon_type, melee_weapon, world, user_id, chapterName) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = bd.cnt();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Устанавливаем параметры запроса
            pstmt.setString(1, obj.getName());
            pstmt.setDouble(2, obj.getCoordinates().getX());
            pstmt.setLong(3, obj.getCoordinates().getY());
            pstmt.setObject(4, obj.getCreationDate()); // Для LocalDateTime используем setObject
            pstmt.setInt(5, obj.getHealth());
            pstmt.setBoolean(6, obj.isLoyal());
            pstmt.setObject(7, obj.getWeaponType().toString(), Types.OTHER); // Преобразуем enum в строку
            pstmt.setObject(8, obj.getMeleeWeapon().toString(), Types.OTHER); // Преобразуем enum в строку
            pstmt.setString(9, obj.getChapter().getWorld());
            pstmt.setInt(10, obj.getUserID());
            pstmt.setString(11, obj.getChapter().getName());

            // Выполняем запрос
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection Error!");
            return new ObjectResAns("Connection or Access Error", true, user);
        }

        return new ObjectResAns("Object " + obj.getName() + " added!", true, user);
    }

    @Override
    public String des(){
        return "add {element} : добавить новый элемент в коллекцию";
    }

    public static Integer getUserIdByLogin(Connection conn, String login) throws SQLException {
        // SQL-запрос для получения ID пользователя по логину
        String sql = "SELECT id FROM users WHERE login = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Устанавливаем параметр запроса
            pstmt.setString(1, login);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Проверяем, есть ли результат запроса
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return null; // Пользователь не найден
                }
            }
        }
    }
}
