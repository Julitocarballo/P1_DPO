import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;


public final class Main {

    private static final String pokemons = "poke.json";

    private static final String pokeballs = "balls.json";

    private static final String llegendari = "legends.json";

    public static void main(String[] args) {
        ExtreuDades info = new ExtreuDades();
        Menu menu = new Menu();
        Gson gson = new Gson();
        JsonReader reader;

        try {
            /*Careguem el fitxer series.json a partir de la llibrria gson*/
            reader = new JsonReader(new FileReader(pokemons));
            info = gson.fromJson(reader, ExtreuDades.class);
            reader = new JsonReader(new FileReader(pokeballs));
            info = gson.fromJson(reader, ExtreuDades.class);
            reader = new JsonReader(new FileReader(llegendari));
            info = gson.fromJson(reader, ExtreuDades.class);

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
