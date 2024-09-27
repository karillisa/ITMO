package collection;

import classes.SpaceMarine;
import datas.DataParseManager;

import java.util.TreeSet;

public class CollectionManager {

    public TreeSet<SpaceMarine> getAllData(String filename){
        TreeSet<SpaceMarine> mySet = new TreeSet<>();

        DataParseManager dbManager = new DataParseManager();

        /*SpaceMarine si = new SpaceMarine("nwwq", new Coordinates(1d, 27), 4, true, Weapon.BOLTGUN, MeleeWeapon.LIGHTING_CLAW, new Chapter("FDg", "Fdg"));
        mySet.add(si);
        SpaceMarine sTmp = new SpaceMarine("y54", new Coordinates(12d, 7), 4, true, Weapon.BOLTGUN, MeleeWeapon.LIGHTING_CLAW, new Chapter("FDg", "Fdg"));
        mySet.add(sTmp);
        dbManager.writeCsv(mySet, filename);*/

        mySet = dbManager.readCsv(filename);

        System.out.println("The collection has been successfully filled from the file!");

        System.out.print(">>> ");

        return mySet;

    }
}
