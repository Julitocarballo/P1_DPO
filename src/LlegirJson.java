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

    Gson gson = new Gson();
    JsonReader reader;

    public LlegirJson() {

    }

    public Pokemon[] extreurePokemon() {

        try {
            JsonReader pkmnReader = new JsonReader(new FileReader(POKEMONS));
            Pokemon pokemons[] = gson.fromJson(pkmnReader, Pokemon[].class);
            return pokemons;
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public Pokeball[] extreurePokeball() {

        try {
            JsonReader pkbllReader = new JsonReader(new FileReader(POKEBALLS));
            Pokeball pokeballs[] = gson.fromJson(pkbllReader, Pokeball[].class);
            return pokeballs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonArray extreureArray(){
        try {
            JsonReader legendsReader = new JsonReader(new FileReader(LLEGENDARI));
            JsonParser legendsParser = new JsonParser();
            JsonArray legends = legendsParser.parse(legendsReader).getAsJsonArray();
            return legends;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
    public int extreureid(JsonArray llegenmitics, int i){
        int id = llegenmitics.get(i).getAsJsonObject().get("id").getAsInt();
        return id;
    }
    public boolean llegendariMitic(JsonArray llegenmitics, int i){
        String kind = llegenmitics.get(i).getAsJsonObject().get("kind").getAsString();
        return (kind.equals("legendary"))? true : false;
    }
    public Legendary extreureLlegendari(JsonArray llegenmitics, int i){

    }

}
