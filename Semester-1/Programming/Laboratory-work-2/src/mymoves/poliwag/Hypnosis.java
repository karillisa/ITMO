package mymoves.poliwag;

import ru.ifmo.se.pokemon.*;

public class Hypnosis extends StatusMove {
    public Hypnosis(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.SPEED, -3);
        Effect.sleep(p);

    }

    @Override //переопределяем метод describe
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
