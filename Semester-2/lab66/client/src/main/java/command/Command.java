package command;

import classes.SpaceMarine;

import java.util.LinkedHashSet;
import java.util.TreeSet;

public interface Command {
    boolean doo(String args, TreeSet<SpaceMarine> mySet);
    String des();
    String getName();
}