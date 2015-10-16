package URLShortener.Test;

import URLShortener.Utility.URLShortener;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 */
public class URLShortenerTest extends TestCase {

    String[] casi= {"http://www.google.com", "https://www.google.com", "www.google.com/"};
    String[] aspetto= {"www.google.com", "www.google.com", "www.google.com"};
    Boolean[] aspetto1={true, true, true};

    String[] casi1= {"www.aa.it", "www.tart.it", "www.casa.it"};
    String[] aspetto2={"http://fkt.in/ciao", "http://fkt.in/cart", "http://fkt.in/matc"};

    URLShortener prova;

    /**
     *
     */
    public URLShortenerTest() {
        prova = new URLShortener();
    }

    /**
     * @throws Exception
     */
    @Test
    public void testShortenURL() throws Exception {
        for (int i = 0; i < casi1.length; i++) {
            String c = prova.shortenURL(casi1[i]);
            assertTrue(c!=aspetto2[i]);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void testValidateURL() throws Exception {
        for (int i = 0; i<casi.length; i++){
            Boolean d = prova.validateURL(casi[i]);
            assertTrue(d == aspetto1[i]);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSanitizeURL() throws Exception {
        for (int i = 0; i < casi.length; i++) {
            String c = prova.sanitizeURL(casi[i]);
            assertEquals(c, aspetto[i]);
        }
    }
}


