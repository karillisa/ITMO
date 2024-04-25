package people;

import objects.Building;

public interface Speakable {
    String tell (String speech);

    String invite (Person[] who, Building where);

    String agree();
}
