package objects;

import java.util.Objects;

public class City implements Praisable{
    private final String name;
    private Float square;
    private Integer population;

    public City(String name, Float square, Integer population) {
        this.name = name;
        this.square = square;
        this.population = population;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) && Objects.equals(square, city.square) && Objects.equals(population, city.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, square, population);
    }
}
