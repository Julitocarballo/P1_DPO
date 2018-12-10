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
    public Legendary extreureLlegendari(Legendary aux,int id,JsonArray llegenmitics, int i){
        aux.setId(id);
        aux.setKind(llegenmitics.get(i).getAsJsonObject().get("kind").getAsString());
        JsonElement jelement = new JsonParser().parse(LLEGENDARI);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("gym");
        aux.getGym().setName(jobject.get("name").getAsString());
        aux.getGym().getLocation().setLongitude(jobject.get("longitude").getAsFloat());
        aux.getGym().getLocation().setLatitude(jobject.get("latitude").getAsFloat());
        return aux;
    }
    public Mythical extreureMitic(Mythical mit,int id,JsonArray llegenmitics, int i){
        mit.setId(id);
        mit.setKind(llegenmitics.get(i).getAsJsonObject().get("kind").getAsString());
        JsonElement jelement = new JsonParser().parse(LLEGENDARI);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("special_research");
        mit.getSpecial_research().setName(jobject.get("name").getAsString());
        JsonArray arr = jobject.getAsJsonArray("quests");
        for(int j=0;j<arr.size();j++){
            mit.getSpecial_research().getQuests()[j].setTarget(jobject.get("target").getAsInt());
            mit.getSpecial_research().getQuests()[j].setQuantity(jobject.get("quantity").getAsInt());
        }

        return mit;
    }

}
