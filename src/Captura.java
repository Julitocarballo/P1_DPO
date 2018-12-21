

//Classe encarregada de gestionar tots els processos encarregats de la captura, ja sigui manipular el nombre de intents com aplicar les formules de captura segons el tipus de pokémon.
public class Captura {
 //Atribut utilitzat per indicar el nombre de intents restants que té el usuari per capturar el pokémon.
 private int intents;
    //Constructor que inicialitza el atribut intents a 5 quan es crea la classe.
    public Captura(){
        this.intents = 5;
    }
    //Mètode que retorna el nombre de intents actuals del usuari.
    public int getIntents() {
        return intents;
    }
    //Mètode que decrementa el nombre de intents del usuari.
    public void restaIntents() {
        this.intents--;
    }
    //Mètode que actualitza el valor del atribut intents a 5.
    public void setIntents() {
        this.intents = 5;
    }
    //Mètode encarregat de aplicar la formula de capturar pokémons salvatges que retorna un valor booleà que indica si el pokémon s’ha capturat o no.
    public boolean capturaPokeSalvatge(int pm, int pb){
        boolean capturat = false;
        double probabilitat;
        double randomnumber = Math.random();

        probabilitat = (double)(pb/256) + (double)(pm/2048);

       if(randomnumber < probabilitat){
        capturat = true;
        }
        return capturat;
    }
    //Mètode encarregat de aplicar la formula de capturar pokémons llegendaris que retorna un valor booleà que indica si el pokémon s’ha capturat o no.
    public boolean capturaPokeLegendary(int pm, int pb){
        boolean capturat = false;
        double probabilitat;
        double randomnumber = Math.random();

        probabilitat = (Math.pow(pb, 1.5) + Math.pow(pm, Math.PI))/4096;

        if(randomnumber < probabilitat){
            capturat = true;
        }
        return capturat;
    }
    //Mètode que retorna true ja que la formula per capturar sempre dona que el pokémon ha estat capturat.
    public boolean capturaPokeMitic(int pm, int pb){
        return true;
    }
}
