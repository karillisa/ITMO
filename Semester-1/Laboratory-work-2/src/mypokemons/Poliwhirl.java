package mypokemons;

import mymoves.poliwag.Hypnosis;
import mymoves.poliwag.Scald;
import mymoves.poliwhirl.WakeUpSlap;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Poliwhirl extends Pokemon {
    public Poliwhirl(String name, int level) {
        super(name, level);

        super.setType(Type.WATER);
        super.setStats(65, 65, 65, 50, 50, 90);

        Hypnosis hypnosis = new Hypnosis(0,60);
        Scald scald = new Scald(80, 100);
        WakeUpSlap wakeUpSlap = new WakeUpSlap(70,100);

        super.setMove(hypnosis, scald, wakeUpSlap);
    }
}
