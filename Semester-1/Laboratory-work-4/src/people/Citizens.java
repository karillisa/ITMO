package people;

import objects.Building;
import objects.City;
import params.Job;
import params.Quality;

import java.util.Arrays;
import java.util.Objects;

public class Citizens extends Person{
    private String name;
    private City from;
    private Job job;
    private Quality[] qualities;

    public Citizens(String name, City from, Job job, Quality[] qualities) {
        super(name, from, job);
        this.name = name;
        this.from = from;
        this.job = job;
        this.qualities = qualities;
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

    public String stringQualities() {
        return Arrays.toString(qualities).replace("[", "").replace("]", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Quality[] getQualities() {
        return qualities;
    }

    public void setQualities(Quality[] qualities) {
        this.qualities = qualities;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizens citizens = (Citizens) o;
        return Objects.equals(name, citizens.name) && Objects.equals(from, citizens.from) && job == citizens.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, from, job);
    }
}
