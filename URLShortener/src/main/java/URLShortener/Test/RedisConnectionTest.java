package URLShortener.Test;

import URLShortener.DAO.RedisConnection;
import URLShortener.Utility.ShortURLData;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 18/08/2015.
 */
public class RedisConnectionTest extends TestCase{

    String []longURL = {"www.google.com", "www.amazon.it", "www.ciao.it", "www.uniba.it", "http://stackoverflow.com/"};
    String []shortURL = {"google","amazon", "ciao", "www.sht.com/uniba", "stackOver"};
    String [] casi  ={"google","amazon", "ciao", "www.sht.com/uniba", "stackOver", "www.amazon.sl"};
    boolean [] aspetto  ={false, false, false, false, false, true};


    /**
     * @throws Exception
     */
   @Test
    public void testIsExists() throws Exception {

       for (int i = 0; i < casi.length; i++) {
           for(int j = 0; j < longURL.length; j++) {
               ShortURLData.saveShortLongURL(shortURL[j], longURL[j]);
               boolean c = RedisConnection.getIstance().isExists(casi[i]);

               assertTrue(c == aspetto[i]);
           }
       }

    }
}
