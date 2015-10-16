package URLShortener.Test;

import URLShortener.Utility.JsonValues;
import URLShortener.Utility.ShortURLData;
import URLShortener.Utility.URLShortener;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 20/08/2015.
 */
public class ShortURLDataTest extends TestCase {

    int i=0;
    int j=0;
    String[] casoPrimo = {"www.sht.com/kWpcv","www.sht.com/4nlzR","www.sht.com/WIPmq","www.sht.com/5fDj0","www.sht.com/1RnpB"};
    String[] casoSecondo = {"www.caccapupu.com","www.gooogle.it!","www.amazon.sl","www.uniba.com","www.shazam.sc"};

    String[] cases1 = {"broJG","SQhAJ","GHoHp","q8xTB","vlynY","IRosu","NuUr8","cxmDH"};
    String[] cases2 = {"www.google.it","www.amazon.com","www.basididatiavanzati.de",
        "https://www.focus.it","www.ciaosic.sl","www.happycasa.org","www.ultimoesameesto.ciao","www.tempesta.civediamo"};

    /**
     * @throws Exception
     */
    @Test
    public void testSetJsonString() throws Exception {
        for(int k=0; k<cases1.length;k++){
            ShortURLData test = new ShortURLData(cases1[k],cases2[k]);
            JsonValues result0 = test.setJsonString();
            Assert.assertTrue(result0 != null);
        }

    }

    /**
     * @throws Exception
     */
    @Test
    public void testAddNewClick() throws Exception {
        for (int k=0; k<cases1.length;k++) {
            ShortURLData test = new ShortURLData(cases1[k],cases2[k]);
            JsonValues result1 = test.setJsonString();
            Assert.assertTrue(result1 != null);
        }

    }

    /**
     * @throws Exception
     */
    @Test


    public void testSaveShortLongURL() throws Exception {
        for(i=0;i<casoPrimo.length;i++){
            for(j=0;j<casoSecondo.length;j++){
                ShortURLData.saveShortLongURL(casoPrimo[i], casoSecondo[i]);
                boolean result = ShortURLData.saveShortLongURL(casoPrimo[i], casoSecondo[j]);
                assertTrue(result==false);
            }
        }

    }


}
