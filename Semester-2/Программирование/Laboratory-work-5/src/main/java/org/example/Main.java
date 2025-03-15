package org.example;

import classes.*;
import collection.CollectionManager;
import command.CommandManager;
import statics.ConsoleManager;

import java.io.IOException;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        //String filename = "Datas/baza.csv";
        String filename = System.getenv("BD"); //(BD=Datas/baza.csv) environment variable  if in IntelliJ*/
        CollectionManager collectionManager = new CollectionManager();
        CommandManager cmd = new CommandManager();
        ConsoleManager console = new ConsoleManager();

        TreeSet<SpaceMarine> mySet = new TreeSet<>();
        mySet = collectionManager.getAllData(filename);

        console.start(cmd, filename, mySet);
    }
}