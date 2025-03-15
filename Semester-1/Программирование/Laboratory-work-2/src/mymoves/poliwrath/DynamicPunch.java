package mymoves.poliwrath;

import ru.ifmo.se.pokemon.*;

import java.util.Random;

public class DynamicPunch extends PhysicalMove {
    public DynamicPunch(double pow, double acc) {

        super(Type.FIGHTING, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.confuse(p);
        Effect e = new Effect().chance(0.5).stat(Stat.ATTACK, 1);
        p.addEffect(e);
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
