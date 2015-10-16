package URLShortener.Test;

import URLShortener.Stats.ClickStats;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClickStatsTest extends TestCase {

    String[] cases = {"2015/08/20","2015/08/21","2015/08/20", "2015/08/19"};

    /**
     * @throws Exception
     */
    @Test
    public void testGetDate() throws Exception {
        for (int i=0; i<cases.length; i++){
            ClickStats prova = new ClickStats(cases[i]);
            String result = prova.getDate();
            assertTrue(result != null);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void testToJson() throws Exception {
        for (int i=0; i < cases.length; i++){
            ClickStats prova = new ClickStats(cases[i]);
            JSONObject result = prova.toJson();
            assertTrue(result != null);
        }
    }
}
