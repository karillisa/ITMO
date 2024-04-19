package mymoves.forretress;

import ru.ifmo.se.pokemon.*;

public class ZapCannon extends SpecialMove {
    public ZapCannon(double pow, double acc){
        super(Type.ELECTRIC, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect.paralyze(p);
        Effect e = new Effect().chance(0.25).stat(Stat.SPEED, -1);
        p.addEffect(e);
    }

    @Override //переопределяем метод describe
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}

