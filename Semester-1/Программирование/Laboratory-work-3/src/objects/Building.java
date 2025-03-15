package objects;

import params.Size;

import java.util.Objects;

public abstract class Building implements Praisable{
    private final String name;
    private Size size;
    private City city;

    protected Building(String name, Size size, City city) {
        this.name = name;
        this.size = size;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return size + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(name, building.name) && size == building.size && Objects.equals(city, building.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, city);
    }
}
