package people;

import objects.Building;
import objects.City;
import params.Job;

import java.util.Objects;

public class Sproots extends Person{
    private String name;
    private City from;
    private Job job;

    public Sproots(String name, City from, Job job) {
        super(name, from, job);
        this.name = name;
        this.from = from;
        this.job = job;
    }

    @Override
    public String interested(String thing) {
        return null;
    }

    @Override
    public String inquire(String thing, Person[] who) {
        return null;
    }

    @Override
    public String tell(String speech) {
        return null;
    }

    @Override
    public String invite(Person[] who, Building where) {
        return null;
    }

    @Override
    public String agree() {
        return null;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
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
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sproots sproots = (Sproots) o;
        return Objects.equals(name, sproots.name) && Objects.equals(from, sproots.from) && job == sproots.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, from, job);
    }
}
