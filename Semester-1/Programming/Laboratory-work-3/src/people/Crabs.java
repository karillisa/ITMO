package people;

import objects.Building;
import objects.City;
import objects.Praisable;
import params.Job;

import java.util.Arrays;
import java.util.Objects;

public class Crabs extends Person{
    private String name;
    private City from;
    private Job job;

    public Crabs(String name, City from, Job job) {
        super(name, from, job);
        this.name = name;
        this.from = from;
        this.job = job;
    }

    public String startConversation (String extra) {
        return name + " started conversation " + extra;
    }

    public String praise (Praisable what) {
        return name + " was praising the " + what;
    }

    @Override
    public String tell(String speech) {
        return name + " said: \"" + speech + "\"";
    }

    @Override
    public String invite(Person[] who, Building where) {
        return name + " invited " + Arrays.toString(who).replace("[", "").replace("]", "").replace(",", " and") + " to " + where.toString();
    }

    @Override
    public String agree() {
        return name + " agreed";
    }

    @Override
    public String interested(String thing) {
        return name + " had interested " + thing;
    }

    @Override
    public String inquire(String thing, Person[] who) {
        return name + " had inquired if " + Arrays.toString(who).replace("[", "").replace("]", "").replace(",", " and") + thing;
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
        return name + " from " + from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crabs crabs = (Crabs) o;
        return Objects.equals(name, crabs.name) && Objects.equals(from, crabs.from) && job == crabs.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, from, job);
    }
}
