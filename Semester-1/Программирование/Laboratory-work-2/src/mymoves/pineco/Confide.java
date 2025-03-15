package mymoves.pineco;

import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {
    public Confide(double pow, double acc){
        super(Type.NORMAL, pow, acc);
    }

    protected void applyOppDamage(Pokemon def, double damage){
        Effect e = new Effect();
        e.stat(Stat.SPECIAL_ATTACK, -1);
        def.addEffect(e);
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}

