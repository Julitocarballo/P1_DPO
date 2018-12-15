import java.util.ArrayList;
public class User {

    private int monedes;
    private int[] inventari;
    private ArrayList<Pokemon> pokemonsCapturats;

    public User(int mida) {
        monedes = 1000;
        inventari = new int[mida];
        inventari[0] = 3;
        pokemonsCapturats = new ArrayList<Pokemon>();
    }

    public void afegirPokemonCapturat(Pokemon pokemon){
        pokemonsCapturats.add(pokemon);
    }

    public int[] getInventari() {
        return inventari;
    }

    public void setcomprarInventari(int unitats, int i) {
        inventari[i] += unitats;
    }

    public int getMonedes() {
        return monedes;
    }

    public void setsumarMonedes(int monedes) {
        this.monedes += monedes;
    }

    public void setrestarMonedes(int monedes){this.monedes -= monedes;}

    public int getNumPokeballs(){
        int numpokeballs = 0;
        for(int i = 0; i < inventari.length; i++){
            numpokeballs += inventari[i];
        }
        return numpokeballs;
    }

    public void gastarPokeball(int i){
        inventari[i]--;
    }

}
