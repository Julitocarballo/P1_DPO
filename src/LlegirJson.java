import com.google.gson.stream.JsonReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class LlegirJson {

    private static final String POKEMONS = "poke.json";

    private static final String POKEBALLS = "balls.json";

    private static final String LLEGENDARI = "legends.json";

    public LlegirJson() {
    }

    public Pokemon[] extreurePokemon() {
        Gson gson = new Gson();
        JsonReader reader;

        try {
            JsonReader pkmnReader = new JsonReader(new FileReader(POKEMONS));
            Pokemon pokemons[] = gson.fromJson(pkmnReader, Pokemon[].class);
            return pokemons;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
