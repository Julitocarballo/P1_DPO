import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class LlegirHtml {
    public LlegirHtml() {
    }
    public String llegirHtml(String address) throws Exception{

        URL page = new URL(address);
        StringBuffer text = new StringBuffer();
        HttpURLConnection conn = (HttpURLConnection) page.openConnection();
        conn.connect();
        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
        BufferedReader buff = new BufferedReader(in);
        String line;
        do {
            line = buff.readLine();
            text.append(line + "\n");
        } while (line != null);
        System.out.println(text.toString());
        return text.toString();
    }
}
