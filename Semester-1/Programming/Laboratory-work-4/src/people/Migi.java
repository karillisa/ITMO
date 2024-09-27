package people;

import objects.Building;
import objects.City;
import params.Job;

import java.util.Objects;

public class Migi extends Person{
    private String name;
    private City from;
    private Job job;
    public Migi(String name, City from, Job job) {
        super(name, from, job);
        this.name = name;
        this.from = from;
        this.job = job;
    }
    @Override
    public String interested(String thing) {
        return name + " was interested " + thing;
    }

    @Override
    public String inquire(String thing, Person[] who) {
        return null;
    }

    @Override
    public String tell(String speech) {
        return name + " said: \"" + speech + "\"";
    }

    @Override
    public String invite(Person[] who, Building where) {
        return null;
    }

    @Override
    public String agree() {
        return name + " agreed";
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return name + " from " + from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Migi migi = (Migi) o;
        return Objects.equals(name, migi.name) && Objects.equals(from, migi.from) && job == migi.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, from, job);
    }
}
