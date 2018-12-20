import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class LlegirHtml {
    public LlegirHtml() {
    }
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
