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
        Pokeball[] pokeballs;
        Pokemon[] pokemons;
        JsonReader reader;
        ExtreureDades extreuDades = new ExtreureDades();


        int z = 0;
        pokeballs = json.getPokeball();
        pokemons = json.extreurePokemon();
        JsonArray jsonllegenmitics = json.extreureArray();
        for (int i = 0; i < jsonllegenmitics.size(); i++) {
            int id = json.extreureid(jsonllegenmitics, i);
            boolean kind = json.llegendariMitic(jsonllegenmitics, i);
            if (kind) {
                //Legendary aux = pokemons[i].legendary;
                //aux = json.extreureLlegendari(aux, id, jsonllegenmitics, i);
                //aux.setGymFromJsonObject(jsonllegenmitics.get(0).getAsJsonObject().get("gym"));
                //pokemons[id]
            } else {
                //Mythical mit[i] = pokemons[i].mythical;
                //9mit = json.extreureMitic(mit, id, jsonllegenmitics, i);

            }
        }
        if (pokeballs == null || pokemons == null || jsonllegenmitics == null) {
            System.out.println("Error al llegir algun dels fitxers");
        } else {

            //Legendary aux = (Legendary) pokemons[id - 1];
            //aux.setGymFromJsonObject(legends.get(0).getAsJsonObject().get("gym"));
            //pokemons[id - 1] = aux;
            //comprovem Null


            do {

                do {

                    menu.mostraMenu();
                } while (menu.demanaOpcio());
                /*S'exeecuta l'opcio demanada anteriorment sob la inf*/
                extreuDades.execute(menu.getOpcio(), user);
                /*info.execute(menu.getOpcio());*/
            } while (menu.continua());

        }
    }
}
