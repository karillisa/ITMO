package mymoves.poliwhirl;

import ru.ifmo.se.pokemon.*;

public class WakeUpSlap extends PhysicalMove {
    public WakeUpSlap(double pow, double acc){
        super(Type.FIGHTING, pow, acc);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        super.applyOppDamage(def, damage);
    }

    @Override
    protected double calcBaseDamage(Pokemon att, Pokemon def) {
        if (att.getCondition() == Status.SLEEP) {
            power = power * 2;
        }
        Effect e = new Effect().condition(Status.SLEEP).turns(0);
        def.setCondition(e);
        return super.calcBaseDamage(att,def);
    }


    @Override //переопределяем метод describe
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
