package URLShortener.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by katia on 25/08/2015.
 */
public class GetInternetIP {
    private static final GetInternetIP istance = new GetInternetIP();

    public static GetInternetIP getIstance(){
        return istance;
    }

    public String getInternetIP() throws IOException {

        URL url = new URL("http://andbin.altervista.org/tools/client_ip.php?mode=raw");
        URLConnection urlconn = url.openConnection();

        InputStream is = urlconn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "US-ASCII");
        BufferedReader br = new BufferedReader(isr);

        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }
}
