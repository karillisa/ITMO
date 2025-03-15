package mymoves;

import ru.ifmo.se.pokemon.*;

public class SampleMove extends SpecialMove {
    public SampleMove(double pow, double acc){
        super(Type.NORMAL, pow, acc);

    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        super.applyOppDamage(def, damage);
    }

    @Override //переопределяем метод describe
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
