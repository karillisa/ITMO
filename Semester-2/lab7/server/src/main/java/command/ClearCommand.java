package command;

import classes.*;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.TreeSet;
public class ClearCommand extends AbsCommand {
    public ClearCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) {
        boolean userHasElements = false;
        Iterator<SpaceMarine> iterator = mySet.iterator();

        // Проверка коллекции на наличие изменений
        for (SpaceMarine spc : mySet){
            Long objetId = spc.getId();
            if (CommandManager.getRes(user + " " + objetId) != null && CommandManager.getRes(user + " " + objetId)) {
                return new ObjectResAns("Changes are being made to the collection, try again later =)", false, user);
            }
        }
        // Проходим по коллекции SpaceMarine
        while (iterator.hasNext()) {
            SpaceMarine spc = iterator.next();
            Long objectId = spc.getId();

            try {
                // Получаем user_id по id объекта
                String sqlReUserId = "SELECT user_id FROM labBd WHERE id = ?";
                PreparedStatement st1 = cnt.prepareStatement(sqlReUserId);
                st1.setInt(1, objectId.intValue());
                ResultSet re = st1.executeQuery();
                if (re.next()) {
                    int userId = re.getInt(1);

                    // Получаем логин пользователя по user_id
                    String userName = "SELECT login FROM users WHERE id = ?";
                    PreparedStatement sttUN = cnt.prepareStatement(userName);
                    sttUN.setInt(1, userId);
                    ResultSet reUN = sttUN.executeQuery();
                    if (reUN.next()) {
                        String userN = reUN.getString(1);
                        if (user.equals(userN)) {
                            // Удаляем объект из коллекции и базы данных
                            iterator.remove();
                            String delRecordSql = "DELETE FROM labBd WHERE id = ?";          // кроме последней
                            PreparedStatement deleteStmt = cnt.prepareStatement(delRecordSql);
                            deleteStmt.setInt(1, objectId.intValue());
                            deleteStmt.executeUpdate();
                            userHasElements = true;
                        }
                    }
                }
            } catch (Exception e) {
                return new ObjectResAns("Error while checking user ownership.", false, user);
            }
        }

        // Возвращаем результат в зависимости от наличия элементов у пользователя
        if (userHasElements) {
            return new ObjectResAns("Collection is cleaned for user: " + user, true, user);
        } else {
            return new ObjectResAns("No elements found for user: " + user, false, user);
        }
    }

    @Override
    public String des() {
        return "clear : очистить коллекцию для текущего пользователя";
    }
}
