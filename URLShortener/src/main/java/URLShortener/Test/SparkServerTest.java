package URLShortener.Test;

import URLShortener.SparkServer.SparkServer;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 20/08/2015.
 */
public class SparkServerTest extends TestCase {


    String[] casi ={"http://www.google.com", "http://www.amazon.com","http://www.facebook.com" };
    String[] casi1 = {"www.sht.com/DxSoc", "www.sht.com/SocDx", "www.sht.com/XdSoc"};
    SparkServer prova = new SparkServer();


    /**
     * @throws Exception
     */
    @Test
    public void testConvertToShortUrl() throws Exception {
        for (int i=0; i<casi.length; i++) {
            JSONObject result = prova.convertToShortUrl(casi[i]);
            assertTrue(result != null);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSaveShort() throws Exception {

        for (int i=0; i<casi.length; i++) {
            JSONObject result = prova.saveShort(casi1[i], casi[i]);
            assertTrue(result != null);
        }


    }

    /**
     * @throws Exception
     */
    @Test
    public void testViewWindow() throws Exception {
        for (int i=0; i<casi1.length; i++) {
            JSONObject result = prova.viewWindow(casi1[i]);
            assertTrue(result != null);
        }
    }


    @Test
    public void testGetGraphPage() throws Exception {
        for (int i=0; i<casi1.length; i++) {
            JSONObject result = prova.getGraphPage(casi1[i]);
            assertTrue(result != null);
        }
    }
}
