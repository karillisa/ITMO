package classes;

import java.time.LocalDateTime;

public class SpaceMarine implements Comparable<SpaceMarine> {

    public SpaceMarine() {

    }

    @Override
    public int compareTo(SpaceMarine other) {
        return Long.compare(this.id, other.id);
    }

    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Integer health;
    private boolean loyal;
    private Weapon weaponType;
    private MeleeWeapon meleeWeapon;
    private Chapter chapter;


    public SpaceMarine(String name, Coordinates coordinates, Integer health, boolean loyal, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.setName(name);
        this.setCoordinates(coordinates);
        this.creationDate = LocalDateTime.now();
        this.setHealth(health);
        this.loyal = loyal;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.setChapter(chapter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isEmpty()){
            this.name = name;
        } else{
            System.out.println("The field cannot be null, the string cannot be empty");
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates != null) {
            this.coordinates = coordinates;
        }else{
            System.out.println("The field cannot be null");
        }
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        if(health > 0) {
            this.health = health;
        }else{
            System.out.println(" health not be null, > 0");
        }
    }

    public boolean isLoyal() {
        return loyal;
    }

    public void setLoyal(boolean loyal) {
        this.loyal = loyal;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        if(chapter != null) {
            this.chapter = chapter;
        }else{
            System.out.println("Поле Chapter не может быть null");
        }
    }

    @Override
    public String toString() {
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=(" + coordinates.getX() + "," + coordinates.getY() + ")" +
                ", creationDate=" + creationDate +
                ", health=" + health +
                ", loyal=" + loyal +
                ", weaponType=" + weaponType +
                ", meleeWeapon=" + meleeWeapon +
                ", chapter={name:" + chapter.getName() + "; world: " + chapter.getWorld() + "}" +
                '}';
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}