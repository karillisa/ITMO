package objects;

import java.util.Objects;

public class FlowerCity extends City{
    private final String name;
    private Float square;
    private Integer population;

    public FlowerCity(String name, Float square, Integer population) {
        super(name, square, population);
        this.name = name;
        this.square = square;
        this.population = population;
    }

    @Override
    public Float getSquare() {
        return square;
    }

    @Override
    public void setSquare(Float square) {
        this.square = square;
    }

    @Override
    public Integer getPopulation() {
        return population;
    }

    @Override
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
        if (!super.equals(o)) return false;
        FlowerCity that = (FlowerCity) o;
        return Objects.equals(name, that.name) && Objects.equals(square, that.square) && Objects.equals(population, that.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, square, population);
    }
}
