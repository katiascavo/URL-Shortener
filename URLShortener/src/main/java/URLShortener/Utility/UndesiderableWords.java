package URLShortener.Utility;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 */
public class UndesiderableWords {

    /**
     * @param s
     * @return
     */
    public static boolean checkUndesiderableWords(String s) {
        boolean check = true;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            String path = System.getProperty("user.dir")
                + "\\src\\main\\java\\utilities\\";
            fileReader = new FileReader(path + "UndesiderableWords.txt"); //REMEMBER: add UndesiderableWords.txt!

            bufferedReader = new BufferedReader(fileReader);

            String wordGetted;

            while ((wordGetted = bufferedReader.readLine()) != null) {
                if (s.equalsIgnoreCase(wordGetted)) {
                    bufferedReader.close();
                    return false;

                }
            }

            bufferedReader.close();
            return true;
        } catch (Exception e) {
            System.out.println("arrivo in errore" + e.getMessage());
        }
        return check;

    }

}
