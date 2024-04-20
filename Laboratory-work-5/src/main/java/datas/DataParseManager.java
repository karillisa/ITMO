package datas;

import classes.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class DataParseManager {
    public TreeSet<SpaceMarine> readCsv(String filePath)  {
        TreeSet<SpaceMarine> spaceMarines = new TreeSet<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));

            String[] header = reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null) {
                SpaceMarine marine = new SpaceMarine();
                marine.setId(Long.parseLong(line[0]));
                marine.setName(line[1]);
                marine.setCoordinates(new Coordinates(Double.valueOf(line[2]), Long.parseLong(line[3])));
                marine.setCreationDate(LocalDateTime.parse(line[4]));
                marine.setHealth(Integer.parseInt(line[5]));
                marine.setLoyal(Boolean.getBoolean(line[6]));
                marine.setWeaponType(Weapon.valueOf(line[7]));
                marine.setMeleeWeapon(MeleeWeapon.valueOf(line[8]));
                marine.setChapter(new Chapter(line[9], line[10]));
                spaceMarines.add(marine);
            }
        }catch (Exception e){
            System.out.println("Error read CSV file: " + e.getMessage());
        }

        return spaceMarines;
    }

    public void writeCsv(TreeSet<SpaceMarine> spaceMarines, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"id", "name", "coordinates.x", "coordinates.y", "creationDate", "health", "loyal", "weaponType", "meleeWeapon", "chapter.name", "chapter.world"});

            for (SpaceMarine marine : spaceMarines) {
                writer.writeNext(new String[]{
                        String.valueOf(marine.getId()),
                        marine.getName(),
                        marine.getCoordinates().getX().toString(),
                        String.valueOf(marine.getCoordinates().getY()),
                        marine.getCreationDate().toString(),
                        String.valueOf(marine.getHealth()),
                        String.valueOf(marine.isLoyal()),
                        marine.getWeaponType().toString(),
                        marine.getMeleeWeapon().toString(),
                        marine.getChapter().getName(),
                        marine.getChapter().getWorld()
                });
            }
        }catch (Exception e){
            System.out.println("Error Writing to CSV file!");
        }
    }
}

