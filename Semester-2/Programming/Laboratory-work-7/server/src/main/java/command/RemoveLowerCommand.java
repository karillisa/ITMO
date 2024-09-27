package command;

import bd.BdMng;
import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeSet;

public class RemoveLowerCommand extends AbsCommand{
    public RemoveLowerCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user, Connection cnt){
        String[] idS = args.split(" ");
        long id = -111111;
        try {
            id = Long.parseLong(idS[1]);
        }catch (Exception e){
            return new ObjectResAns("Id should be a number!", true, user);
        }

        System.out.println(CommandManager.getRes(user + " " + id));
        if(CommandManager.getRes(user + " " + id) != null && CommandManager.getRes(user + " " + id)){
            return new ObjectResAns("Object is an editing, wait editing!", false, user);
        }

        String allRes = "";
        for(SpaceMarine s: mySet){
            if(s.getId() < id){
                int idd = s.getId().intValue();

                if (user != null) {
                    boolean b = false;
                    try {
                        if (idd >= 0) {
                            String sqlReUserId = "SELECT id FROM users WHERE login = ?";
                            PreparedStatement st1 = cnt.prepareStatement(sqlReUserId);
                            st1.setString(1, user);
                            ResultSet re = st1.executeQuery();

                            int userId = -1;
                            if(re.next()){
                                userId = re.getInt(1);
                            }

                            String sql = "DELETE FROM labBd WHERE id = ?";
                            PreparedStatement statement = cnt.prepareStatement(sql);
                            statement.setInt(1, (int) idd);
                            statement.executeUpdate();

                            statement.close();
                            cnt.close();

                            allRes = allRes + "Object is deleted!\n";
                        } else {
                            allRes = allRes + "Not find object!\n";
                        }
                    } catch (Exception e) {
                        allRes = allRes + "Format Error!\n";
                    }
                }else{
                    allRes = allRes + "Access Error\n";
                }
            }
        }
        return new ObjectResAns(allRes, true, user);
    }

    @Override
    public String des(){
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
