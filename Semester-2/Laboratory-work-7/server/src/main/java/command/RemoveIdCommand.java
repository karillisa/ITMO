package command;

import classes.*;
import mainProgram.Server;
import objectResAns.ObjectResAns;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;
import java.sql.*;

public class RemoveIdCommand extends AbsCommand {
    public RemoveIdCommand(String name) {
        super(name);
    }


    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) throws SQLException {
        ObjectResAns res = null;
        boolean found = false;

        Long objectId = Long.parseLong(s.split(" ")[1]);

        if(CommandManager.getRes(user + " " + objectId) != null && CommandManager.getRes(user + " " + objectId)){
            return new ObjectResAns("Changes are being made to the collection, try again later =)", false, user);
        }

        try {
            // Начало транзакции
            cnt.setAutoCommit(false);

            // Блокируем строку с нужным ID
            String lockSql = "SELECT * FROM labBd WHERE id = ? FOR UPDATE";
            PreparedStatement lockStmt = cnt.prepareStatement(lockSql);
            lockStmt.setLong(1, objectId);
            ResultSet lockedRow = lockStmt.executeQuery();

            if (!lockedRow.next()) {
                cnt.rollback(); // Откат транзакции, если строка не найдена
                return new ObjectResAns("No such element!\n", true, user);
            }


            int ownerId = lockedRow.getInt("user_id");
            if (ownerId != getUserId(user, cnt)) {
                cnt.rollback();
                return new ObjectResAns("You don't have permission to delete this object!\n", false, user);
            }

            synchronized (mySet) {
                // Поиск объекта для удаления
                for (SpaceMarine spcTmp : mySet) {
                    if (Objects.equals(spcTmp.getId(), objectId)) {
                        found = true;
                        mySet.remove(spcTmp); // Удаляем объект из коллекции
                        break;
                    }
                }

                if (!found) {
                    cnt.rollback();
                    return new ObjectResAns("Object with id " + objectId + " not found!", false, user);
                }

                // Удаляем объект из базы данных
                String deleteSql = "DELETE FROM labBd WHERE id = ?";
                PreparedStatement deleteStmt = cnt.prepareStatement(deleteSql);
                deleteStmt.setLong(1, objectId);
                int rowsDeleted = deleteStmt.executeUpdate();

                if (rowsDeleted > 0) {
                    cnt.commit(); // Подтверждаем транзакцию
                    res = new ObjectResAns("Object deleted!", true, user);
                } else {
                    cnt.rollback();
                    res = new ObjectResAns("No such element!\n", true, user);
                }
            }
        } catch (SQLException e) {
            // Откат транзакции в случае ошибки
            cnt.rollback();
            res = new ObjectResAns("Error deleting object", false, user);
            e.printStackTrace();
        } finally {
            // Возврат к обычному режиму работы с базой данных
            cnt.setAutoCommit(true);
        }

        return res;

    }
    private int getUserId(String username, Connection cnt) throws SQLException {
        String sql = "SELECT id FROM users WHERE login = ?";
        PreparedStatement stmt = cnt.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1; // В случае, если пользователь не найден
    }

    @Override
    public String des() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
