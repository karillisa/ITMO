package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.TreeSet;

public class User extends AbsCommand{
    String name = "user";

    public User(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) throws IOException, SQLException, NoSuchAlgorithmException {
        if(user!=null){
            return new ObjectResAns("User: "  + user, true, user);
        }else{
            return new ObjectResAns("Not login!", true, user);
        }
    }

    @Override
    public String des() {
        return "User: Now User";
    }

    @Override
    public String getName() {
        return this.name;
    }
}