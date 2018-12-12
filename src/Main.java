import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;


public final class Main {

    private static final String POKEMONS = "poke.json";

    private static final String POKEBALLS = "balls.json";

    private static final String LLEGENDARI = "legends.json";



    public static void main(String args[]) {
        LlegirJson json = new LlegirJson();
        User user = new User();
        Menu menu = new Menu();
        JsonReader reader;
        ExtreureDades extreuDades = new ExtreureDades();


        extreuDades.setPokemons();
        extreuDades.setLegend();
        extreuDades.setPokeballs();
        JsonArray jsonllegenmitics = extreuDades.getLegend();
        for (int i = 0; i < jsonllegenmitics.size(); i++) {
            int id = json.extreureid(jsonllegenmitics, i);
            boolean kind = json.llegendariMitic(jsonllegenmitics, i);
            if (kind) {
                extreuDades.afegirLlegendari(i, id);

            } else {
                extreuDades.afegirMitic(i, id);

            }
        }
        /*for(int i=0;i<extreuDades.getPokemons().length ; i++){
           if (extreuDades.getPokemons()[i] instanceof Legendary){
               Legendary l= (Legendary) extreuDades.getPokemons()[i];
               System.out.println(l.getGym().getName());
           }
        }*/


        do {

            do {

                menu.mostraMenu();
            } while (menu.demanaOpcio());
            /*S'exeecuta l'opcio demanada anteriorment sob la inf*/
            extreuDades.execute(menu, user);
            /*info.execute(menu.getOpcio());*/
        } while (menu.continua());

    }

}
