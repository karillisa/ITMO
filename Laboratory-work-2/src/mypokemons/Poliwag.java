package mypokemons;

import mymoves.poliwag.Hypnosis;
import mymoves.poliwag.Scald;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Poliwag extends Pokemon {
    public Poliwag(String name, int level) {
        super(name, level);

        super.setType(Type.WATER);
        super.setStats(40, 50, 40, 40, 40, 90);

        Hypnosis hypnosis = new Hypnosis(0,60);
        Scald scald = new Scald(80, 100);

        super.setMove(hypnosis, scald);
    }
}
