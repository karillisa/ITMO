package mypokemons;

import mymoves.forretress.ZapCannon;
import mymoves.pineco.Confide;
import mymoves.pineco.Facade;
import mymoves.pineco.IronDefense;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Forretress extends Pokemon {
    public Forretress(String name, int level) {
        super(name, level);

        super.setType(Type.BUG, Type.STEEL);
        super.setStats(75, 90, 140, 60, 60, 40);

        Confide confide = new Confide(0,0);
        Facade facade = new Facade(70,100);
        IronDefense ironDefense = new IronDefense(0,0);
        ZapCannon zapCannon = new ZapCannon(120,50);

        super.setMove(confide, facade, ironDefense, zapCannon);


    }
}
