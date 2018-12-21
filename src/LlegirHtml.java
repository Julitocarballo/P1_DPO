import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//Classe que s’encarrega exclusivament de extreure la informació de la URL que rep per enviar-la a la classe Html.
public class LlegirHtml {
    //Constructor que ens permetrà crear objectes d’aquesta classe.
    public LlegirHtml() {
    }
    //Mètode que s’encarrega de retornar la informació de la URL que rep en forma de BufferedReader per així poder-la tractar en la opció 8.
    public BufferedReader llegirHtml(String address) throws Exception{
        HttpURLConnection c = null;
        URL u = new URL(address);
        c = (HttpURLConnection) u.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        BufferedReader br = new BufferedReader((new InputStreamReader(c.getInputStream())));
        return br;
    }
}
