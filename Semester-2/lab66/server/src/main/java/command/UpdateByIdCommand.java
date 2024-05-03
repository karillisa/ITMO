package command;


import classes.*;
import objectResAns.ObjectResAns;

import java.util.Objects;
import java.util.TreeSet;

public class UpdateByIdCommand extends AbsCommand{
    public UpdateByIdCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String s, TreeSet<SpaceMarine> mySet){
        ObjectResAns res = null;
        String allRes = "";
        boolean b = false;

        Long objectId = Long.parseLong(s.split(" ")[1]);
        SpaceMarine spcUpdate = null;

        if(mySet.stream().filter(p -> p.getId() == objectId).count() >= 1) {
            if (mySet.stream().filter(p -> p.getId() == objectId).count() == 1) {
                b = true;
            }
        }else{
            return new ObjectResAns("Doesnt find objet with id " + objectId + "!", false);
        }

        if(b) {
            for (SpaceMarine spcTmp : mySet) {
                if (Objects.equals(spcTmp.getId(), objectId)) {
                    spcUpdate = spcTmp;
                }
            }

            if (s.split(" ").length >= 3) {
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
                    return new ObjectResAns("Object id " + objectId + " updated!", true);

                } catch (Exception e) {
                    System.out.println("Data's Error");
                    return new ObjectResAns("Data's Error", true);
                }
            }
            else{
                return new ObjectResAns("Data's Error!", true);
            }
        }else{
            return new ObjectResAns("Data's Error!", true);
        }
    }

    @Override
    public String des(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
