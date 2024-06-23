package datas;

import bd.BdMng;
import classes.*;
import org.gradle.internal.impldep.org.apache.maven.model.Organization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

public class BdReader {
    public TreeSet<SpaceMarine> getAllData(Connection cnt) throws IOException, SQLException {
        TreeSet<SpaceMarine> mySet = new TreeSet<>();
        BdMng bd = new BdMng();
        ResultSet rs = bd.giveResOfQuery(cnt, "SELECT * FROM labbd");
        while (rs.next()){
            Long id = (long) rs.getInt("id");
            String name = rs.getString("name");
            double x = rs.getDouble("x");
            long y = rs.getLong("y");
            LocalDateTime creationDate = rs.getTimestamp("creation_date").toLocalDateTime();
            int health = rs.getInt("health");
            boolean loyal = rs.getBoolean("loyal");
            Weapon weaponType = rs.getString("weapon_type") != null ? Weapon.valueOf(rs.getString("weapon_type")) : null;
            MeleeWeapon meleeWeapon = rs.getString("melee_weapon") != null ? MeleeWeapon.valueOf(rs.getString("melee_weapon")) : null;
            String capterName = rs.getString("chapterName");
            String world = rs.getString("world");
            int userId = rs.getInt("user_id");
            SpaceMarine tmp = new SpaceMarine(id, name, new Coordinates(x, y), creationDate, health, loyal, weaponType, meleeWeapon, new Chapter(capterName, world), userId);
            mySet.add(tmp);
        }


        return mySet;
    }
}

/*
CREATE TABLE dblab (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    coordinates POINT NOT NULL,
    creationdate TIMESTAMP NOT NULL,
    annualturnover FLOAT,
    type VARCHAR(50) NOT NULL,
    postaladdressstreet VARCHAR(255),
    postaladdresszipcoder VARCHAR(20),
    userid INT NOT NULL
);

 */


/*
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

*/
