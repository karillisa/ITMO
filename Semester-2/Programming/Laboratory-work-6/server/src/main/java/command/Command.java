package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.TreeSet;

public interface Command {
    ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet);
    String des();
    String getName();
}