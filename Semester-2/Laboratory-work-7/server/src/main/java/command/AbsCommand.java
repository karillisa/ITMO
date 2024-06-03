package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.TreeSet;

public class AbsCommand implements Command {
    private String name;

    public AbsCommand(String name){
        this.name = name;
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user) throws SQLException, NoSuchAlgorithmException, IOException {
        return new ObjectResAns("", true, null);
    }

    @Override
    public String des() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
