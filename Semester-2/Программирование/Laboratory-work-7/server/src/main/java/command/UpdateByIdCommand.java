package command;

import classes.*;
import mainProgram.Server;
import objectResAns.ObjectResAns;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UpdateByIdCommand extends AbsCommand {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public UpdateByIdCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) throws SQLException {
        ObjectResAns res = null;
        boolean found = false;

        Long objectId = Long.parseLong(s.split(" ")[1]);
        SpaceMarine spcUpdate = null;

        boolean assesss = false;

        // Поиск объекта для обновления
        for (SpaceMarine spcTmp : mySet) {
            if (Objects.equals(spcTmp.getId(), objectId)) {
                spcUpdate = spcTmp;
                found = true;
                break;
            }
        }
        if(found){
            String ress = "SELECT id FROM users WHERE login = ?";
            PreparedStatement st1User = cnt.prepareStatement(ress);
            st1User.setString(1, user);
            ResultSet resUser = st1User.executeQuery();

            int userIdif = -1;
            if(resUser.next()){
                userIdif = resUser.getInt(1);
            }

            for(SpaceMarine i: mySet){
                if(Objects.equals(i.getId(), objectId) && i.getUserID() == userIdif){
                    assesss = true;
                }
            }

            if(!assesss){
                return new ObjectResAns("User Access Error!", false, user);
            }
        }
        if (!found) {
            return new ObjectResAns("Object with id " + objectId + " not found!", false, user);
        }

if (assesss && found) {

        CommandManager.addingMap(user + " " + objectId, true);

    if (s.split(" ").length >= 3) {
        String[] all = s.split(" ");
        try {

            String sqlReUserId = "SELECT id FROM users WHERE login = ?";
            PreparedStatement st1 = cnt.prepareStatement(sqlReUserId);
            st1.setString(1, user);
            ResultSet re = st1.executeQuery();

            int userId = -1;
            if (re.next()) {
                userId = re.getInt(1);
            }

            System.out.println(userId);

            boolean assess = false;
            for (SpaceMarine i : mySet) {
                if (Objects.equals(i.getId(), objectId) && i.getUserID() == userId) {
                    assess = true;
                }
            }

            System.out.println(assess);

            if (assess) {
                cnt.setAutoCommit(false);
                cnt.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                lock.writeLock().lock();

                // Блокируем строку с нужным ID
                String lockSql = "SELECT * FROM labBd WHERE id = ? FOR UPDATE";
                PreparedStatement lockStmt = cnt.prepareStatement(lockSql);
                lockStmt.setLong(1, objectId);
                ResultSet lockedRow = lockStmt.executeQuery();

                if (!lockedRow.next()) {
                    cnt.rollback(); // Откатываем транзакцию, если строка не найдена
                    CommandManager.removeMap(user + " " + objectId);
                    return new ObjectResAns("No such element!\n", true, user);
                }

                synchronized (mySet) {
                    // Обновляем объект
                    String name = all[2];
                    Double x = Double.parseDouble(all[3]);
                    Long y = Long.parseLong(all[4]);
                    Integer health = Integer.parseInt(all[5]);
                    Boolean loyal = Boolean.parseBoolean(all[6]);
                    Weapon weaponType = Weapon.valueOf(all[7]);
                    MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(all[8]);
                    String chapterName = all[9];
                    String chapterWorld = all[10];

                    spcUpdate.setName(name);
                    spcUpdate.setCoordinates(new Coordinates(x, y));
                    spcUpdate.setHealth(health);
                    spcUpdate.setLoyal(loyal);
                    spcUpdate.setWeaponType(weaponType);
                    spcUpdate.setMeleeWeapon(meleeWeapon);
                    spcUpdate.setChapter(new Chapter(chapterName, chapterWorld));

                    // Выполняем обновление в базе данных
                    String updateSql = "UPDATE labBd SET name = ?, x = ?, y = ?, health = ?, loyal = ?, weapon_type = ?, melee_weapon = ?, chapterName = ?, world = ? WHERE id = ? and user_id = ?";
                    PreparedStatement updateStmt = cnt.prepareStatement(updateSql);
                    updateStmt.setString(1, spcUpdate.getName());
                    updateStmt.setDouble(2, spcUpdate.getCoordinates().getX());
                    updateStmt.setLong(3, spcUpdate.getCoordinates().getY());
                    updateStmt.setInt(4, spcUpdate.getHealth());
                    updateStmt.setBoolean(5, spcUpdate.isLoyal());
                    updateStmt.setObject(6, spcUpdate.getWeaponType().toString(), Types.OTHER);
                    updateStmt.setObject(7, spcUpdate.getMeleeWeapon().toString(), Types.OTHER);
                    updateStmt.setString(8, spcUpdate.getChapter().getName());
                    updateStmt.setString(9, spcUpdate.getChapter().getWorld());
                    updateStmt.setLong(10, objectId);
                    updateStmt.setDouble(11, userId);

                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        // Подтверждаем транзакцию
                        cnt.commit();
                        res = new ObjectResAns("Объект обновлен", true, user);
                    } else {
                        cnt.rollback();
                        res = new ObjectResAns("Ошибка обновления объекта", false, user);
                    }
                }
            } else {
                CommandManager.removeMap(user + " " + objectId);
                return new ObjectResAns("Permision Error!", false, user);
            }
        } catch (SQLException e) {
            // Откат транзакции в случае ошибки
            cnt.rollback();
            res = new ObjectResAns("Ошибка обновления объекта", false, user);
            e.printStackTrace();
        } finally {
            // Возврат к обычному режиму работы с базой данных
            lock.writeLock().unlock();
            cnt.setAutoCommit(true);
        }
    }
    CommandManager.removeMap(user + " " + objectId);
}

    return res;
    }

    @Override
    public String des() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
