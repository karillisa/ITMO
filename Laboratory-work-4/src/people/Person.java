package people;

import exception.BuildingIsClosedException;
import objects.Building;
import objects.City;
import objects.Praisable;
import params.Job;

import java.util.Objects;

public abstract class Person implements Speakable, Interested, Praisable {
    private final String name;
    private City from;
    private Job job;

    protected Person (String name, City from, Job job) {
        this.name = name;
        this.from = from;
        this.job = job;
    }

    public String go (Building place) throws BuildingIsClosedException {
        if (place.isClosed()) {
            throw new BuildingIsClosedException("BuildingIsClosedException: the " + place.getName() + " is closed and inaccessible for some time.");
        } else {
            return name + " went to " + place;
        }
    }

    public String sit (Building place, String extra) {
        return name + " was sitting " + extra + " in a " + place.toString();
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

    @Override
    public String toString() {
        return name + " from " + from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(from, person.from) && job == person.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, from, job);
    }
}
