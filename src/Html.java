import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Html {

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

            escritura.println("\t</body>");
            escritura.println("</html>");
            filewriter.close();
            System.out.println("se ha leido el fuchero");
            return filewriter;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("no se ha leido el fichero");
            return null;
        }
    }
}
