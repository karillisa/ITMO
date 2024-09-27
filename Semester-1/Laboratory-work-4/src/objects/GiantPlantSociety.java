package objects;

import params.Size;

public class GiantPlantSociety extends Building {
    private final String name;
    private Size size;
    private City city;

    public GiantPlantSociety(String name, Size size, City city, Boolean isClosed) {
        super(name, size, city, isClosed);
        this.name = name;
        this.size = size;
        this.city = city;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return size + " " + name;
    }
}
