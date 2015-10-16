package URLShortener.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe per la lettura delle stringhe di messaggio
 *
 * @author Caterina Scavo
 *
 */
public class StringMessageManager {

    public static final StringMessageManager message = new StringMessageManager();

    /**
     * @return
     */
    public static StringMessageManager getIstance(){
        return message;
    }

    /**
     * @param key
     * @return
     */
    public String getMessage (String key) {
        Properties props = new Properties();
        InputStream stream;
        try {
            stream = new FileInputStream(
                "src/main/java/utilities/stringMessage.properties");
            props.load(stream);
        } catch (FileNotFoundException e) {

        }
        catch (IOException e) {

        }
        return props.getProperty(key);
    }
}
