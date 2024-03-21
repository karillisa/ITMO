package classes;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String world; //Поле не может быть null

    public Chapter(String name, String world) {
       this.setName(name);
       this.setWorld(world);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isEmpty()) {
            this.name = name;
        }else{
            System.out.println("Поле не может быть null, Строка не может быть пустой");
        }
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        if(world != null) {
            this.world = world;
        }else{
            System.out.println("Поле не может быть null");
        }
    }
}