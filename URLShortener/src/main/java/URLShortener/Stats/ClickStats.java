package URLShortener.Stats;

import org.json.JSONObject;

/**
 *
 */
public class ClickStats {

    public static final String DATE="Date";
    private String date;

    /**
     * @param date
     */
    public ClickStats(String date){
        this.date=date;
    }

    /**
     * @return
     */
    public String getDate(){
        return date;
    }

    /**
     * @param json
     */
    public ClickStats(JSONObject json){
        this.date=json.getString(DATE);
    }

    /**
     * @return
     */
    public JSONObject toJson(){
        JSONObject jdate=new JSONObject();
        jdate.put(DATE, getDate());
        return jdate;
    }
}
