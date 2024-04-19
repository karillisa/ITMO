package mymoves.pineco;

import ru.ifmo.se.pokemon.*;

public class IronDefense extends StatusMove {
    public IronDefense(double pow, double acc){
        super(Type.STEEL, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        if (Math.random() < 0.2){
            Effect.flinch(p);
        }
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }

}
