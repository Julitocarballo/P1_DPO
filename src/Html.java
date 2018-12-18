import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Html {
    private LlegirHtml lh = new LlegirHtml();
    public Html() {
    }

    public FileWriter fitxerCapturats(int pokemonscapturats){
        try{
            FileWriter filewriter = new FileWriter("capturats.html");
            PrintWriter escritura = new PrintWriter(filewriter);

            escritura.println("<!doctype html>");
            escritura.println("<html lang= \"es\">");
            escritura.println("\t<head>");
            escritura.println("\t\t<meta charset=\"UTF-8\">");
            escritura.println("\t\t<h1>Pokémons Capturats: "+pokemonscapturats+"</h1>");
            escritura.println("\t\t<meta name=\"author\" content = \"Julio Carballo López - julio.carballo Arnaud Arens - arnaud.arens\">");
            escritura.println("\t</head>");
            escritura.println("\t<body>");

            //String jsonAPI= lh.llegirHtml("https://pokeapi.co/api/v2/pokemon/id.png");
            escritura.println("<img src= \"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/474.png\"\n\talt=\"Penis\"/>");

            /*System.out.println(jsonAPI);
            JsonParser parser = new JsonParser();
            JsonElement foto = parser.parse(jsonAPI);
            JsonObject jsonObject = foto.getAsJsonObject();
            JsonObject fotopoke = jsonObject.get("sprites").getAsJsonObject();
            String URL = fotopoke.get("front_default").getAsString();
            System.out.println(URL);*/
            escritura.println("\t</body>");
            escritura.println("</html>");
            filewriter.close();
            return filewriter;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
