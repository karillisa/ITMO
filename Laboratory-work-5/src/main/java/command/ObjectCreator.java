package command;

import classes.*;

import java.util.Scanner;

public class ObjectCreator {
    public SpaceMarine createObjectFromConsole(String s) {

        if(s.split(" ").length >= 3){
            String[] all = s.split(" ");

            try {
                String name = all[1];
                Double x = Double.parseDouble(all[2]);
                Long y = Long.parseLong(all[3]);
                Integer health = Integer.parseInt(all[4]);
                Boolean loyal = Boolean.parseBoolean(all[5]);
                Weapon weaponType = Weapon.valueOf(all[6]);
                MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(all[7]);
                String chapterName = all[8];
                String chapterWorld = all[9];

                SpaceMarine sTmp = new SpaceMarine(name, new Coordinates(x, y), health, loyal, weaponType, meleeWeapon, new Chapter(chapterName, chapterWorld));

                System.out.println();
                System.out.println("Object " + name + " added!");
                return sTmp;

            }catch (Exception e){
                System.out.println("Data's Error");
                return null;
            }
        }

        String name = null;

        Scanner line = new Scanner(System.in);
        System.out.println("Введите название SpaceMarine:");
        while(name == null){
            System.out.print(">>> ");
            String tmpName = line.nextLine();
            if(tmpName.length() > 0 && !tmpName.contains("\"") && !tmpName.contains(" ")){
                name = tmpName;
                System.out.println("Название добавлено!");
            }else{
                System.out.println("Название не корректно!\nВведите заново!");
            }
        }

        Double x = null;
        while (x == null){
            System.out.println("Координаты x:");
            System.out.print(">>> ");
            String xY = line.nextLine();
            try {
                Double tmpX = Double.parseDouble(xY);
                if(tmpX >= -267){
                    x = tmpX;
                    System.out.println("Координаты х  добавлены!");
                }else{
                    System.out.println("Значение поля должно быть больше -267");
                }
            } catch (Exception e) {
                System.out.println("Ошибка формата!");
            }
        }

        Long y = null;
        while (y == null){
            System.out.println("Координаты y:");
            System.out.print(">>> ");
            String xY = line.nextLine();
            try {
                Long tmpX = Long.parseLong(xY);
                if(tmpX <= 803){
                    y = tmpX;
                    System.out.println("Координаты y  добавлены!");
                }else{
                    System.out.println("Максимальное значение поля: 803");
                }
            } catch (Exception e) {
                System.out.println("Ошибка формата!");
            }
        }

        Integer health = null;
        while (health == null){
            System.out.println("Health:");
            System.out.print(">>> ");
            String xY = line.nextLine();
            try {
                Integer tmpX = Integer.parseInt(xY);
                if(tmpX > 0){
                    health = tmpX;
                    System.out.println("Health добавлена!");
                }else{
                    System.out.println("Значение поля должно быть больше 0");
                }
            } catch (Exception e) {
                System.out.println("Ошибка формата!");
            }
        }

        Boolean loyal = null;
        while (loyal == null){
            System.out.println("Loyal:\n1-true\n0-false");
            System.out.println(">>> ");
            String tmp = line.nextLine();
            try {
                if(tmp.equals("1")){
                    System.out.println("loyal is True!");
                    loyal = true;
                }
                if(tmp.equals("0")){
                    System.out.println("loyal is False!");
                    loyal = false;
                }
            }catch (Exception e){
                System.out.println("Ошибка формата!");
            }
        }

        Weapon weapon = null;
        while (weapon == null){
            System.out.println("WeaponType:\nBOLTGUN\nCOMBI_PLASMA_GUN\nFLAMER\nINFERNO_PISTOL");
            System.out.println(">>> ");
            String tmp = line.nextLine();

            try {
                weapon = Weapon.valueOf(tmp.toUpperCase());
                System.out.println("WeaponType " + weapon.name() + " added!");
            }catch (Exception e){
                System.out.println("Ошибка формата!");
            }
        }

        MeleeWeapon meleeWeapon = null;
        while (meleeWeapon == null){
            System.out.println("MeleeWeapon:\nPOWER_SWORD\nMANREAPER\nLIGHTING_CLAW\nPOWER_BLADE");
            System.out.println(">>> ");
            String tmp = line.nextLine();

            try {
                meleeWeapon = MeleeWeapon.valueOf(tmp.toUpperCase());
                System.out.println("MeleeWeapon " + meleeWeapon.name() + " added!");
            }catch (Exception e){
                System.out.println("Ошибка формата!");
            }
        }

        System.out.println("Chapter Adding: ");
        String chapterName = null;
        while (chapterName == null){
            System.out.println("Chapter Name:");
            System.out.println(">>> ");
            String tmp = line.nextLine();

            if(tmp.length() > 0 && !tmp.contains("\"") && !tmp.contains(" ")){
                chapterName = tmp;
                System.out.println("Название добавлено!");
            }else{
                System.out.println("Название не корректно!\nВведите заново!");
            }
        }

        String chapterWorld = null;
        while (chapterWorld == null){
            System.out.println("Chapter World:");
            System.out.println(">>> ");
            String tmp = line.nextLine();

            if(tmp.length() > 0 && !tmp.contains("\"") && !tmp.contains(" ")){
                chapterWorld = tmp;
                System.out.println("Название World добавлено!");
            }else{
                System.out.println("Название не корректно!\nВведите заново!");
            }
        }

        System.out.println();
        SpaceMarine sNew = new SpaceMarine(name, new Coordinates(x, y), health, loyal, weapon, meleeWeapon, new Chapter(chapterName, chapterWorld));
        return sNew;

    }
}
