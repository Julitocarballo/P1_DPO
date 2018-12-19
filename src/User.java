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

    public ArrayList<Pokemon> getPokemonsCapturats() {
        return pokemonsCapturats;
    }

   /* public int[] retornapokeRepetits(Pokemon[] pokemons, int i){
        int[] contadorIndex = new int[2];
        boolean trobat = false;
        for(i = i; i < pokemons.length && !trobat; i++){
            for(int j = 0; j < pokemonsCapturats.size(); j++){
                if(pokemons[i].getName().equals(pokemonsCapturats.get(j).getName())){
                    trobat = true;
                    contadorIndex[0]++;
                    contadorIndex[1] = pokemons[i].getId();
                }
            }
        }
        return contadorIndex;
    }
*/
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
