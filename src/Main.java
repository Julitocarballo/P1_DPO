import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;


public final class Main {

    private static final String POKEMONS = "poke.json";

    private static final String POKEBALLS = "balls.json";

    private static final String LLEGENDARI = "legends.json";

    public static void main(String[] args) {
        ExtreureDades info= new ExtreureDades();
        Menu menu = new Menu();
        Gson gson = new Gson();
        JsonReader reader;

        /*JSONArray jsonarray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String name= jsonobject.getString("name");
            String url = jsonobject.getString("url");
        }*/

        try {
            /*Careguem el fitxer series.json a partir de la llibrria gson*/

            do {

                do {

                    menu.mostraMenu();
                } while (menu.demanaOpcio());
                /*S'exeecuta l'opcio demanada anteriorment sob la inf*/
                info.execute(menu.getOpcio());
            } while (menu.continua());

        } catch (FileNotFoundException e) {

            System.err.println("No he pogut trobar el fitxer.");
        }
    }
}
