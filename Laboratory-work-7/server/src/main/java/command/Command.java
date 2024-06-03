package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.TreeSet;

public interface Command {
    ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user) throws SQLException, NoSuchAlgorithmException, IOException;
    String des();
    String getName();
}