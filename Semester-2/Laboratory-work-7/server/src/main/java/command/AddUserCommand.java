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

public class AddUserCommand extends AbsCommand{

    public String name = "addUser";

    public AddUserCommand(String name) {
        super(name);
    }

    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet, String user) throws SQLException, NoSuchAlgorithmException, IOException {
        String[] arg = s.split(" ");
        if(arg.length >= 3) {
            String name = arg[1];
            String psswd = arg[2];
            BdMng bd = new BdMng();
            Connection cnt = bd.cnt();

            try {
                String sqlStr = "SELECT COUNT(*) FROM users WHERE login = ?";
                PreparedStatement statement = cnt.prepareStatement(sqlStr);
                statement.setString(1, name);
                ResultSet res = statement.executeQuery();

                if (res.next() && res.getInt(1) == 0) {
                    String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
                    statement = cnt.prepareStatement(sql);
                    statement.setString(1, name);

                    // хэшируем пароль алгоритмом MD5
                    String passwordToHash = psswd;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] passwordBytes = passwordToHash.getBytes();
                    byte[] hash = md.digest(passwordBytes);
                    String passwordHash = bytesToHex(hash);

                    statement.setString(2, passwordHash);
                    statement.executeUpdate();

                    return new ObjectResAns("User " + name + " added!\n", true, user);
                } else {
                    return new ObjectResAns("User " + name + " already added!\n", false, user);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ObjectResAns("Bd Connection Error", false, user);
            }
        }else{
            return new ObjectResAns("Username or password is not typing!\n", false, user);
        }
    }

    @Override
    public String des() {
        return "addUser: adding new user";
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
