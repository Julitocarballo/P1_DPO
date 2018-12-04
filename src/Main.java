import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;


public final class Main {

    private static final String POKEMONS = "poke.json";

    private static final String POKEBALLS = "balls.json";

    private static final String LLEGENDARI = "legends.json";

    public static void main(String args[]) {

        User user = new User();
        Menu menu = new Menu();
        Gson gson = new Gson();
        JsonReader reader;
        ExtreureDades extreuDades = new ExtreureDades();


        try {
            JsonReader pkmnReader = new JsonReader(new FileReader(POKEMONS));
            JsonReader legendsReader = new JsonReader(new FileReader(LLEGENDARI));
            JsonParser legendsParser = new JsonParser();

            JsonArray legends = legendsParser.parse(legendsReader).getAsJsonArray();

            Pokemon pokemons[] = gson.fromJson(pkmnReader, Pokemon[].class);

            int id = legends.get(0).getAsJsonObject().get("id").getAsInt();
            String kind = legends.get(0).getAsJsonObject().get("kind").getAsString();

            if (kind.equals("legendary")) {
                Legendary aux = (Legendary) pokemons[id - 1];
                //aux.setGymFromJsonObject(legends.get(0).getAsJsonObject().get("gym"));
                pokemons[id - 1] = aux;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        int error=0;
            /*Careguem el fitxer series.json a partir de la llibrria gson*/

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
