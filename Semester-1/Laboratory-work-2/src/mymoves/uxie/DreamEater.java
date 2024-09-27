package mymoves.uxie;

import ru.ifmo.se.pokemon.*;

public class DreamEater extends SpecialMove {
    int HealthDrain;
    public DreamEater(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        if(def.getCondition().equals(Status.SLEEP)){
            def.setMod(Stat.HP, (int) Math.round(damage));
            this.HealthDrain = (int) Math.round(0.5 * damage);
        }
        else{
            def.setMod(Stat.HP, 0);
            this.HealthDrain = 0;
        }
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.HP, -HealthDrain);
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
