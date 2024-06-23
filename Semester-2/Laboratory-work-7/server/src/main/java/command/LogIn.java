package command;

import bd.BdMng;
import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.TreeSet;

public class LogIn extends AbsCommand{

    String name = "login";

    public LogIn(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user, Connection cnt) throws IOException, SQLException, NoSuchAlgorithmException {
        String[] arg = s.split(" ");
        if(arg.length >= 3) {
            String name = arg[1];
            String psswd = arg[2];;

            String sqlStr = "SELECT COUNT(*) FROM users WHERE login = ?";
            PreparedStatement statement = cnt.prepareStatement(sqlStr);
            statement.setString(1, name);
            ResultSet res = statement.executeQuery();

            if (res.next() && res.getInt(1) == 1) {
                String sql = "SELECT password FROM users WHERE login = ?";
                statement = cnt.prepareStatement(sql);
                statement.setString(1, name);

                // хэшируем пароль алгоритмом MD5
                String passwordToHash = psswd;
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] passwordBytes = passwordToHash.getBytes();
                byte[] hash = md.digest(passwordBytes);
                String passwordHash = AddUserCommand.bytesToHex(hash);

                ResultSet resPass = statement.executeQuery();
                if (resPass.next()) {
                    if (resPass.getString("password").equals(passwordHash)) {
                        return new ObjectResAns("User " + name + " logined!\n", true, name);
                    } else {
                        return new ObjectResAns("User or password is wrong!\n", false, user);
                    }
                }
            } else {
                return new ObjectResAns("User " + name + " not found!\n", false, user);
            }
        }else {
            return new ObjectResAns("Username or password is not typing!\n", false, user);
        }
        return new ObjectResAns("done!\n", false, user);
    }

    @Override
    public String des() {
        return "login: Login Username Password";
    }

    @Override
    public String getName() {
        return this.name;
    }
}