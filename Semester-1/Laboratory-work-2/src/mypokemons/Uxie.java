package mypokemons;

import mymoves.uxie.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Uxie extends Pokemon {
    public Uxie(String name, int level){
        super(name, level);

        super.setType(Type.PSYCHIC);
        super.setStats(75, 75, 130, 75, 130, 95);

        Swift swift = new Swift(60, 100);
        ShadowBall shadowBall = new ShadowBall(80, 100);
        Swagger swagger = new Swagger(0,85);
        DreamEater dreamEater = new DreamEater(100, 100);

        super.setMove(swift, shadowBall, swagger, dreamEater);

    }
}
