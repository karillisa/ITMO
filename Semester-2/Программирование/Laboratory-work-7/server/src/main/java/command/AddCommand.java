package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;
import java.sql.*;
import java.util.TreeSet;

public class AddCommand extends AbsCommand{
    public AddCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection conn) {
        ObjectCreator objectCreator = new ObjectCreator();
        SpaceMarine obj = objectCreator.createObjectFromConsole(s);

        Integer id = null;

        try {
            // Получение Id пользователя по логину
            id = getUserIdByLogin(conn, user);
        } catch (Exception e) {
            System.out.println("Id error");
        }

        // Установка Id пользователя в объект
        obj.setUserID(id);

        // SQL-запрос для вставки нового объекта в базу данных
        String sql = "INSERT INTO labBd (name, x, y, creation_date, health, loyal, weapon_type, melee_weapon, world, user_id, chapterName) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Установка параметров для PreparedStatement
            pstmt.setString(1, obj.getName());
            pstmt.setDouble(2, obj.getCoordinates().getX());
            pstmt.setLong(3, obj.getCoordinates().getY());
            pstmt.setObject(4, obj.getCreationDate());
            pstmt.setInt(5, obj.getHealth());
            pstmt.setBoolean(6, obj.isLoyal());
            pstmt.setObject(7, obj.getWeaponType().toString(), Types.OTHER);
            pstmt.setObject(8, obj.getMeleeWeapon().toString(), Types.OTHER);
            pstmt.setString(9, obj.getChapter().getWorld());
            pstmt.setInt(10, obj.getUserID());
            pstmt.setString(11, obj.getChapter().getName());

            // Выполнение SQL-запроса
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error!");
            return new ObjectResAns("Connection or Access Error", true, user);
        }
        // Возвращение результата добавления объекта
        return new ObjectResAns("Object " + obj.getName() + " added!", true, user);
    }

    @Override
    public String des(){
        return "add {element} : добавить новый элемент в коллекцию";
    }

    // Статический метод для получения Id пользователя по логину
    public static Integer getUserIdByLogin(Connection conn, String login) throws SQLException {

        String sql = "SELECT id FROM users WHERE login = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Установка логина в PreparedStatement
            pstmt.setString(1, login);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Возвращение Id получателя, если он найден
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return null;
                }
            }
        }
    }
}
