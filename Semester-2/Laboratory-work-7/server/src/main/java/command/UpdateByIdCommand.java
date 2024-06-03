package command;


import bd.BdMng;
import classes.*;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Objects;
import java.util.TreeSet;

public class UpdateByIdCommand extends AbsCommand{
    public UpdateByIdCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user){
        ObjectResAns res = null;
        String allRes = "";
        boolean b = false;

        Long objectId = Long.parseLong(s.split(" ")[1]);
        SpaceMarine spcUpdate = null;

        if(mySet.stream().filter(p -> p.getId() == objectId).count() >= 1) {
            if (mySet.stream().filter(p -> p.getId() == objectId).count() == 1) {
                b = true;
            }
        }else{
            return new ObjectResAns("Doesnt find objet with id " + objectId + "!", false, user);
        }

        if(b) {
            for (SpaceMarine spcTmp : mySet) {
                if (Objects.equals(spcTmp.getId(), objectId)) {
                    spcUpdate = spcTmp;
                }
            }

            if (s.split(" ").length >= 3) {
                String[] all = s.split(" ");

                try {
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

                    BdMng bd = new BdMng();
                    Connection cnt = bd.cnt();
                    String sqlReUserId = "SELECT COUNT(*) FROM labBd WHERE id = ?";
                    PreparedStatement st1 = cnt.prepareStatement(sqlReUserId);
                    st1.setInt(1, Integer.parseInt(String.valueOf(objectId.intValue())));
                    ResultSet re = st1.executeQuery();

                    if(re.next() && re.getInt(1) > 0) {
                        String sUserId = "SELECT user_id FROM labBd WHERE id = ?";
                        PreparedStatement stt1 = cnt.prepareStatement(sUserId);
                        stt1.setInt(1, Integer.parseInt(String.valueOf(objectId.intValue())));
                        ResultSet re2 = stt1.executeQuery();
                        int userId = -1;
                        if(re2.next()){userId = re2.getInt(1);}

                        String userName = "SELECT login FROM users WHERE id = ?";
                        PreparedStatement sttUN = cnt.prepareStatement(userName);
                        sttUN.setInt(1, userId);
                        ResultSet reUN = sttUN.executeQuery();
                        String userN = null;
                        if(reUN.next()) {
                            userN = reUN.getString(1);
                        }
                        if(user.equals(userN)) {
                            String sql = "UPDATE labBd SET name = ?, x = ?, y = ?, health = ?, loyal = ?, weapon_type = ?, melee_weapon = ?, world = ?, chapterName = ? WHERE id = ?";
                            PreparedStatement statement = cnt.prepareStatement(sql);
                            statement.setString(1, spcUpdate.getName());
                            statement.setDouble(2, spcUpdate.getCoordinates().getX());
                            statement.setLong(3, spcUpdate.getCoordinates().getY());
                            statement.setInt(4, spcUpdate.getHealth());
                            statement.setBoolean(5, spcUpdate.isLoyal());
                            statement.setObject(6, spcUpdate.getWeaponType().toString(), Types.OTHER); // Преобразуем enum в строку
                            statement.setObject(7, spcUpdate.getMeleeWeapon().toString(), Types.OTHER); // Преобразуем enum в строку
                            statement.setString(8, spcUpdate.getChapter().getWorld());
                            statement.setString(9, spcUpdate.getChapter().getName());
                            statement.setInt(10, objectId.intValue());

                            int rowsUpdated = statement.executeUpdate();

                            if (rowsUpdated > 0) {

                                return new ObjectResAns("Организация " + spcUpdate.getName() + " изменена!\n", true, user);
                            } else {
                                return new ObjectResAns("Wrong Response!\n", false, user);
                            }
                        }else{
                            return new ObjectResAns("У вас нет доступа!\n", false, user);
                        }
                    }else {
                        return new ObjectResAns("Нету такого элемента!\n", true, user);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ObjectResAns("Data's Error", true, user);
                }
            }
            else{
                return new ObjectResAns("Data's Error!", true, user);
            }
        }else{
            return new ObjectResAns("Data's Error!", true, user);
        }
    }

    @Override
    public String des(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
