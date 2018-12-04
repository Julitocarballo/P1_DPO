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
            /*Careguem el fitxer series.json a partir de la llibrria gson*/
            /*
            reader = new JsonReader(new FileReader(POKEMONS));
            Pokemon[] info1 = gson.fromJson(reader, Pokemon[].class);
            */
            reader = new JsonReader(new FileReader(POKEBALLS));
            Pokeball[] info2 = gson.fromJson(reader, Pokeball[].class);
            /*
            reader = new JsonReader(new FileReader(LLEGENDARI));
            Legendary[] info3 = gson.fromJson(reader, Legendary[].class);

            reader = new JsonReader(new FileReader(LLEGENDARI));
            Mythical[] info4 = gson.fromJson(reader, Mythical[].class);

            */

            do {

                do {

                    menu.mostraMenu();
                } while (menu.demanaOpcio());
                /*S'exeecuta l'opcio demanada anteriorment sob la inf*/
                extreuDades.execute(menu.getOpcio(), user);
            } while (menu.continua());

        } catch (FileNotFoundException e) {

            System.out.println("No he pogut trobar el fitxer.");
        }
    }
}
