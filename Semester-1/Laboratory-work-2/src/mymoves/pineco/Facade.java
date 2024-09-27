package mymoves.pineco;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade(double pow, double acc){
        super(Type.NORMAL, pow, acc);

    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        if (def.getCondition().equals(Status.PARALYZE) || def.getCondition().equals(Status.BURN) || def.getCondition().equals(Status.POISON)){
            damage = damage*2;
        }
        def.setMod(Stat.HP, (int) damage);
    }


    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}