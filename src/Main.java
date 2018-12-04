import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;


public final class Main {

    private static final String POKEMONS = "poke.json";

    private static final String POKEBALLS = "balls.json";

    private static final String LLEGENDARI = "legends.json";

    public static void main(String[] args) {
        Pokemons[] info1 = new Pokemons();
        Pokeballs[] info2 = new Pokeballs();
        Legendary[] info3 = new Legendary();
        Mythical[] info4 = new Mythical();
        Menu menu = new Menu();
        Gson gson = new Gson();
        JsonReader reader;

        try {
            /*Careguem el fitxer series.json a partir de la llibrria gson*/
            reader = new JsonReader(new FileReader(POKEMONS));
            info1 = gson.fromJson(reader, Pokemons.class);
            /*icoicn*/
            reader = new JsonReader(new FileReader(POKEBALLS));
            info2 = gson.fromJson(reader, Pokeballs.class);
            reader = new JsonReader(new FileReader(LLEGENDARI));
            info3 = gson.fromJson(reader, Legendary.class);
            reader = new JsonReader(new FileReader(LLEGENDARI));
            info4 = gson.fromJson(reader, Mythical.class);

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
