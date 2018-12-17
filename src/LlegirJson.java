import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class LlegirJson {

    private String POKEMONS = "poke.json";

    private String POKEBALLS = "balls.json";

    private String LLEGENDARI = "legends.json";


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

    public Pokeball[] getPokeball() {

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

    public Legendary getLlegendari(int id, int i, Pokemon pokemon, JsonArray llegenmitics){

        String kind = llegenmitics.get(i).getAsJsonObject().get("kind").getAsString();
        JsonObject gym = llegenmitics.get(i).getAsJsonObject().get("gym").getAsJsonObject();
        JsonObject jsonLocation =  gym.get("location").getAsJsonObject();

        Location location = new Location(jsonLocation.get("latitude").getAsFloat(), jsonLocation.get("longitude").getAsFloat());
        Gym g = new Gym (gym.get("name").getAsString(), location);
        Legendary legend = new Legendary(id, pokemon.getName(), pokemon.getCapture_rate(), g, kind);
        return legend;
    }
    public Mythical getMitic(int id, int i, Pokemon pokemon, JsonArray llegenmitics){
        String kind = llegenmitics.get(i).getAsJsonObject().get("kind").getAsString();
        JsonObject special_research = llegenmitics.get(i).getAsJsonObject().get("special_research").getAsJsonObject();
        Recerca sr = new Recerca (special_research.get("name").getAsString(), special_research.get("quests").getAsJsonArray(),false);
        Mythical mitic = new Mythical(id, kind, sr, pokemon.getCapture_rate(), pokemon.getName());
        return mitic;
    }


}
