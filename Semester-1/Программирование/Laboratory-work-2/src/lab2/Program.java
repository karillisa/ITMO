package lab2;
import mypokemons.*;
import ru.ifmo.se.pokemon.Battle;

// https://pokemondb.net/pokedex/uxie
// https://pokemondb.net/pokedex/pineco
// https://pokemondb.net/pokedex/forretress
// https://pokemondb.net/pokedex/poliwag
// https://pokemondb.net/pokedex/poliwhirl
// https://pokemondb.net/pokedex/poliwrath

public class Program {
    public static void main(String[] args) {
        Battle b = new Battle();

        Poliwrath a1 = new Poliwrath("Tamanno",1);
        Poliwag a2 = new Poliwag("Marhabo", 1);
        Pineco a3 = new Pineco("Malika", 1);

        Poliwhirl f1 = new Poliwhirl("Anisa", 1);
        Uxie f2 = new Uxie("Shahnoza", 1);
        Forretress f3 = new Forretress("Zebo", 1);

        b.addAlly(a1);
        b.addAlly(a2);
        b.addAlly(a3);

        b.addFoe(f1);
        b.addFoe(f2);
        b.addFoe(f3);

        b.go();
    }
    public static boolean chance(double d){

        return d > Math.random();
    }
}
