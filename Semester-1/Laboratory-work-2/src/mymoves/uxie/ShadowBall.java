package mymoves.uxie;

import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove {
    public ShadowBall(double pow, double acc) {
        super(Type.GHOST, pow, acc);

    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        Effect e = new Effect().chance(0.2).stat(Stat.ACCURACY,-6);
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length - 1];
    }
}
