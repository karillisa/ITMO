package mypokemons;

import mymoves.poliwag.Hypnosis;
import mymoves.poliwag.Scald;
import mymoves.poliwhirl.WakeUpSlap;
import mymoves.poliwrath.DynamicPunch;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Poliwrath extends Pokemon {
    public Poliwrath(String name, int level) {
        super(name, level);

        super.setType(Type.WATER, Type.FIGHTING);
        super.setStats(90, 95, 95, 70, 90, 70);

        Hypnosis hypnosis = new Hypnosis(0,60);
        Scald scald = new Scald(80, 100);
        WakeUpSlap wakeUpSlap = new WakeUpSlap(70,100);
        DynamicPunch dynamicPunch = new DynamicPunch(100,50);

        super.setMove(hypnosis, scald, wakeUpSlap, dynamicPunch);
    }
}
