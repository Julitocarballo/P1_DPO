import java.io.FileWriter;
import java.io.PrintWriter;
import com.google.gson.*;
import java.util.ArrayList;
//Classe que s’encarrega de generar els fitxers html de les opcions 7 i 8.
public class Html {
    //Atribut que serveix per crear un nou objecte de la classe LlegirHtml.
    private LlegirHtml lh = new LlegirHtml();
    //Atribut que ens servirà per passar el array de pokemons de la classe ExtreureDades a aquesta.
    private Pokemon[] pokemons;
    //Atribut que ens servirà per passar la llista de pokemons capturats de la classe User a aquesta.
    private ArrayList<Pokemon> pokemonsCapturats;
    //Constructor que ens permetrà crear un objecte d’aquesta classe.
    public Html() {
    }
    //Mètode que genera un fitxer html a partir de la informació obtinguda de una URL. El fitxer mostra el total de pokemons capturats i per cada pokémon capturat, quantes vegades està capturat.
    public void fitxerCapturats(int pokemonscapturats){

        try{
            FileWriter filewriter = new FileWriter("capturats.html");
            PrintWriter escritura = new PrintWriter(filewriter);

            escritura.println("<!doctype html>");
            escritura.println("<html lang= \"es\">");
            escritura.println("\t<head>");
            escritura.println("\t\t<meta charset=\"UTF-8\">");
            escritura.println("\t\t<h1>Pokémons capturats: "+pokemonscapturats+"</h1>");
            escritura.println("\t\t<meta name=\"author\" content = \"Julio Carballo López - julio.carballo Arnaud Arens - arnaud.arens\">");
            escritura.println("\t</head>");
            escritura.println("\t<body>");

            for(int i = 0; i< pokemons.length;i++) {
                int repetit = retornapokeRepetits(pokemons[i], pokemonsCapturats);
                if (repetit != 0) {
                    escritura.println("<table border = \"0\">");
                    escritura.println("<tr>");
                    escritura.println("<td><img src= \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemons[i].getId()+".png\"\n\talt=\"Text per si no podem carregar la imatge i per lectors de pantalles\"/td>");
                    escritura.print("<td><b><font style=\"text-transform: capitalize;\">"+pokemons[i].getName()+"</font></b> x "+repetit+"</td>");
                    escritura.println("</tr>");
                    escritura.println("</table>");
                }
            }

            escritura.println("\t</body>");
            escritura.println("</html>");
            filewriter.close();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
    //Mètode que genera un fitxer html a partir de la informació obtinguda de una URL. El fitxer mostra informació detallada de un pokémon. Nom, id, petita descripció, pes, alçada i experiència base.
    public void fitxerDetalls(Pokemon pokemons){
        try{
            FileWriter filewriter = new FileWriter("detalls.html");
            PrintWriter escritura = new PrintWriter(filewriter);

            escritura.println("<!doctype html>");
            escritura.println("<html lang= \"es\">");
            escritura.println("\t<head>");
            escritura.println("\t\t<meta charset=\"UTF-8\">");
            escritura.println("\t\t<h1><font style=\"text-transform: capitalize;\">"+pokemons.getName()+"</font> ("+pokemons.getId()+")</h1>");
            escritura.println("\t\t<meta name=\"author\" content = \"Julio Carballo López - julio.carballo Arnaud Arens - arnaud.arens\">");
            escritura.println("\t</head>");
            escritura.println("\t<body>");

            JsonParser parser = new JsonParser();
            JsonElement je = parser.parse(lh.llegirHtml("https://pokeapi.co/api/v2/pokemon/"+pokemons.getId()+"/"));
            JsonElement je2 = parser.parse(lh.llegirHtml("https://pokeapi.co/api/v2/pokemon-species/"+pokemons.getId()+"/"));
            JsonObject jsonObject = je.getAsJsonObject();
            JsonObject jsonObject2 = je2.getAsJsonObject();
            escritura.println("<p><img src= \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemons.getId()+".png\" width=\"250px\" height=\"250px\"\n\talt=\"No s'ha pogut trobar cap foto\"/></p>");

            JsonArray jsonArray =jsonObject2.get("flavor_text_entries").getAsJsonArray();
            String descripcio = jsonArray.get(3).getAsJsonObject().get("flavor_text").getAsString();
            float altura= jsonObject.get("height").getAsFloat();
            float pes = jsonObject.get("weight").getAsFloat();
            int xp = jsonObject.get("base_experience").getAsInt();
            escritura.println("<p>"+descripcio+"</p>");
            escritura.println("<ul>");
            escritura.println("\t<li> "+altura/10+" m.</li>");
            escritura.println("\t<li> "+pes/100+" kg.</li>");
            escritura.println("\t<li> "+xp+" xp.</li>");
            escritura.println("</ul>");
            escritura.println("\t</body>");
            escritura.println("</html>");
            filewriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    //Mètode que agafa el array de pokemons de la classe ExtreureDades i l’emmagatzema en el atribut d’aquesta classe.
    public void getPokemons(Pokemon[] pokemons){
        this.pokemons=pokemons;
    }
    //Mètode que agafa la llista de pokemons capturats de la classe User i l’emmagatzema en el atribut d’aquesta classe.
    public void getPokemonsCapturats(ArrayList<Pokemon> poke){
        this.pokemonsCapturats=poke;
    }
    //Mètode que retorna les vegades que ha estat capturat un pókemon.
    public int retornapokeRepetits(Pokemon pokemons, ArrayList<Pokemon> pokemonsCapturats){
        int num=0;

        if(pokemonsCapturats.isEmpty()){
            return 0;
        }else{
            for (int i =0;i<pokemonsCapturats.size();i++){
                if(pokemonsCapturats.get(i).getId() == pokemons.getId()){
                    num++;
                }
            }
            return num;
        }
    }
}
