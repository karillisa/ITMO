package mypokemons;

import mymoves.pineco.Confide;
import mymoves.pineco.Facade;
import mymoves.pineco.IronDefense;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Pineco extends Pokemon {
    public Pineco(String name, int level) {
        super(name, level);

        super.setType(Type.BUG);
        super.setStats(50, 65, 90, 35, 35, 15);

        Confide confide = new Confide(0,0);
        Facade facade = new Facade(70,100);
        IronDefense ironDefense = new IronDefense(0,0);

        super.setMove(confide, facade, ironDefense);

    }
}
