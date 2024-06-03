package command;

import bd.BdMng;
import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeSet;

public class RemoveIdCommand extends AbsCommand{
    public RemoveIdCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user){
        String[] idS = args.split(" ");
        long id = -111111;
        try {
            id = Integer.parseInt(idS[1]);
        }catch (Exception e){
            return new ObjectResAns("Id should be a number!",  true, user);
        }

        if (user != null) {
            boolean b = false;
            try {
                if (id >= 0) {
                    BdMng bd = new BdMng();
                    Connection cnt = bd.cnt();
                    String sqlReUserId = "SELECT id FROM users WHERE login = ?";
                    PreparedStatement st1 = cnt.prepareStatement(sqlReUserId);
                    st1.setString(1, user);
                    ResultSet re = st1.executeQuery();

                    int userId = -1;
                    if(re.next()){
                        userId = re.getInt(1);
                    }

                    for(SpaceMarine o: mySet){
                        if(o.getId() == id){
                            if(o.getUserID() == userId){
                                b = true;
                            }else{
                                return new ObjectResAns("Access Error!\n", false, user);
                            }
                        }
                    }
                }
                if (b == true) {
                    BdMng bd = new BdMng();
                    Connection cnt = bd.cnt();
                    String sql = "DELETE FROM labBd WHERE id = ?";
                    PreparedStatement statement = cnt.prepareStatement(sql);
                    statement.setInt(1, (int) id);
                    statement.executeUpdate();

                    statement.close();
                    cnt.close();

                    return new ObjectResAns("Object is deleted!\n", true, user);
                } else {
                    return new ObjectResAns("Not find object!\n", false, user);
                }
            } catch (Exception e) {
                return new ObjectResAns("Format Error!\n", true, user);
            }
        }else{
            return new ObjectResAns("Access Error\n", true, user);
        }
    }

    @Override
    public String des(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
