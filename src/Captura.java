import java.util.Random;

public class Captura {

 private int intents;

    public Captura(){
        this.intents = 5;
    }

    public int getIntents() {
        return intents;
    }

    public void restaIntents() {
        this.intents--;
    }

    public void setIntents() {
        this.intents = 5;
    }

    public boolean capturaPokeSalvatge(int pm, int pb){
        boolean capturat = false;
        double probabilitat;
        double randomnumber = Math.random();

        System.out.println("numero random = " + randomnumber);
        probabilitat = (double)(pb/256) + (double)(pm/2048);

       if(randomnumber < probabilitat){
        capturat = true;
        }
        return capturat;
    }

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

    public boolean capturaPokeMitic(int pm, int pb){
        return true;
    }
}
