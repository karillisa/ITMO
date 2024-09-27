package mymoves.poliwag;

import ru.ifmo.se.pokemon.*;

public class Scald extends SpecialMove {
    public Scald(double pow, double acc){
        super(Type.WATER, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);

        p.addEffect(new Effect().chance(0.3).condition(Status.BURN));

    }

    @Override //переопределяем метод describe
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
