package collection;

import classes.SpaceMarine;

import java.util.TreeSet;

public class CollectionManager {

    public TreeSet<SpaceMarine> getAllData(String filename){
        TreeSet<SpaceMarine> mySet = new TreeSet<>();

        /*SpaceMarine si = new SpaceMarine("nwwq", new Coordinates(1d, 27), 4, true, Weapon.BOLTGUN, MeleeWeapon.LIGHTING_CLAW, new Chapter("FDg", "Fdg"));
        mySet.add(si);
        SpaceMarine sTmp = new SpaceMarine("y54", new Coordinates(12d, 7), 4, true, Weapon.BOLTGUN, MeleeWeapon.LIGHTING_CLAW, new Chapter("FDg", "Fdg"));
        mySet.add(sTmp);
        dbManager.writeCsv(mySet, filename);*/

        System.out.println("The collection has been successfully filled from the file!");

        System.out.print(">>> ");

        return mySet;

    }
}
