import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


//Classe que s’encarrega d’extreure tota la informació dels fitxers json i emmagatzemar-la en la seva corresponent variable.
public class LlegirJson {
    //Emmagatzema el json del fitxer poke.json.
    private String POKEMONS = "poke.json";
    //Emmagatzema el json del fitxer balls.json.
    private String POKEBALLS = "balls.json";
    //Emmagatzema el json del fitxer legends.json.
    private String LLEGENDARI = "legends.json";

    //Ens permet crear un objecte de la classe Gson que ens permetrà utilitzar funcions de la llibreria gson per poder manipular els fitxers.
    Gson gson = new Gson();
    //Constructor que ens permetrà crear objectes d’aquesta classe.
    public LlegirJson() {
    }
    //Mètode que retorna el array de pokemons extret del fitxer poke.json mitjançant funcions de la llibreria gson.
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
    //Mètode que retorna el array de pokéballs extret del fitxer balls.json mitjançant funcions de la llibreria gson.
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
    //Mètode que retorna la informació del fitxer legends.json en forma de array mitjançant funcions de la llibreria gson.
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
    //Mètode que retorna la id del pokémon llegendari amb el que s’està treballant.
    public int extreureid(JsonArray llegenmitics, int i){
        int id = llegenmitics.get(i).getAsJsonObject().get("id").getAsInt();
        return id;
    }
    //Mètode que diferencia si un pokémon es llegendari o mític mirant el seu “kind”.
    public boolean llegendariMitic(JsonArray llegenmitics, int i){
        String kind = llegenmitics.get(i).getAsJsonObject().get("kind").getAsString();
        return (kind.equals("legendary"))? true : false;
    }
    //Mètode que retorna tota la informació de un pokémon llegendari, extreta del array obtingut a partir del json, que serà introduïda en el array de pokemons, en el pokémon que li correspongui.
    public Legendary getLlegendari(int id, int i, Pokemon pokemon, JsonArray llegenmitics){

        String kind = llegenmitics.get(i).getAsJsonObject().get("kind").getAsString();
        JsonObject gym = llegenmitics.get(i).getAsJsonObject().get("gym").getAsJsonObject();
        JsonObject jsonLocation =  gym.get("location").getAsJsonObject();

        Location location = new Location(jsonLocation.get("latitude").getAsFloat(), jsonLocation.get("longitude").getAsFloat());
        Gym g = new Gym (gym.get("name").getAsString(), location);
        Legendary legend = new Legendary(id, pokemon.getName(), pokemon.getCapture_rate(), g, kind);
        return legend;
    }
    //Mètode que retorna tota la informació de un pokémon mític, extreta del array obtingut a partir del json, que serà introduïda en el array de pokemons, en el pokémon que li correspongui.
    public Mythical getMitic(int id, int i, Pokemon pokemon, JsonArray llegenmitics){
        String kind = llegenmitics.get(i).getAsJsonObject().get("kind").getAsString();
        JsonObject special_research = llegenmitics.get(i).getAsJsonObject().get("special_research").getAsJsonObject();
        Recerca sr = new Recerca (special_research.get("name").getAsString(), special_research.get("quests").getAsJsonArray(),false);
        Mythical mitic = new Mythical(id, kind, sr, pokemon.getCapture_rate(), pokemon.getName());
        return mitic;
    }


}
