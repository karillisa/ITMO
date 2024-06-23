package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.awt.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.TreeSet;

public class LogOut extends AbsCommand{
    String name = "logout";

    public LogOut(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) throws IOException, SQLException, NoSuchAlgorithmException {
        if(user!=null){
            return new ObjectResAns("User: "  + user + " logouted!", true, null);
        }else{
            return new ObjectResAns("Not login!", true, user);
        }
    }

    @Override
    public String des() {
        return "logout: Logout Username Password";
    }

    @Override
    public String getName() {
        return this.name;
    }
}