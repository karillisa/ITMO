package mymoves.uxie;

import ru.ifmo.se.pokemon.*;

public class Swift extends SpecialMove {
    public Swift(double pow, double acc){
        super(Type.NORMAL, pow, acc);

    }

    @Override
    protected void applySelfEffects(Pokemon p){
        Effect e = new Effect().turns(0).stat(Stat.ACCURACY, 100);
        p.addEffect(e);
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}