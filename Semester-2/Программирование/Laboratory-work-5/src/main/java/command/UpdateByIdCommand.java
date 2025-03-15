package command;


import classes.*;

import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

public class UpdateByIdCommand extends AbsCommand{
    public UpdateByIdCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String s, TreeSet<SpaceMarine> mySet){
        Long objectId = Long.parseLong(s.split(" ")[1]);
        SpaceMarine spcUpdate = null;

        for(SpaceMarine spcTmp: mySet){
            if(Objects.equals(spcTmp.getId(), objectId)){
                spcUpdate = spcTmp;
            }
        }

        if(spcUpdate == null){
            System.out.println("Object with id " + objectId + " not Found!");
            return false;
        }

        if(s.split(" ").length >= 3){
            String[] all = s.split(" ");


            try {
                String name = all[2];
                Double x = Double.parseDouble(all[3]);
                Long y = Long.parseLong(all[4]);
                Integer health = Integer.parseInt(all[5]);
                Boolean loyal = Boolean.parseBoolean(all[6]);
                Weapon weaponType = Weapon.valueOf(all[7]);
                MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(all[8]);
                String chapterName = all[9];
                String chapterWorld = all[10];

                System.out.println();
                spcUpdate.setName(name);
                spcUpdate.setCoordinates(new Coordinates(x, y));
                spcUpdate.setHealth(health);
                spcUpdate.setLoyal(loyal);
                spcUpdate.setWeaponType(weaponType);
                spcUpdate.setMeleeWeapon(meleeWeapon);
                spcUpdate.setChapter(new Chapter(chapterName, chapterWorld));

                System.out.println("Object id " + objectId + " updated!");
                return true;

            }catch (Exception e){
                System.out.println("Data's Error");
                return false;
            }
        }

        String name = null;
        ObjectCreator objectCreator = new ObjectCreator();
        SpaceMarine sNew = objectCreator.createObjectFromConsole(s);

        System.out.println();
        spcUpdate.setName(sNew.getName());
        spcUpdate.setCoordinates(sNew.getCoordinates());
        spcUpdate.setHealth(sNew.getHealth());
        spcUpdate.setLoyal(sNew.isLoyal());
        spcUpdate.setWeaponType(sNew.getWeaponType());
        spcUpdate.setMeleeWeapon(sNew.getMeleeWeapon());
        spcUpdate.setChapter(sNew.getChapter());

        System.out.println("Object id " + objectId + " updated!");
        return true;
    }

    @Override
    public String des(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
