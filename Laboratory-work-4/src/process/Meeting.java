package process;

import objects.Building;
import people.Person;

import java.util.Objects;

public class Meeting {
    private String name;
    private Status status;
    private Person with;

    private Building place;

    public Meeting(String name, Status status, Person with, Building place) {
        this.name = name;
        this.status = status;
        this.with = with;
        this.place = place;
    }

    @Override
    public String toString() {
        return name + " with " + with + " had " + status + " in the " + place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(name, meeting.name) && status == meeting.status && Objects.equals(with, meeting.with) && Objects.equals(place, meeting.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, with, place);
    }
}
