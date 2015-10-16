package URLShortener.Utility;

import URLShortener.DAO.RedisConnection;
import URLShortener.Stats.ClickStats;
import URLShortener.Stats.GeoLocation;

import java.text.DateFormatSymbols;

import URLShortener.Stats.GetInternetIP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 *
 */
public class ShortURLData {

    private static final String SHORTURL="shortUrl";
    private static final String LONGURL="longUrl";
    private static final String NAME = "name";
    private static final String VALUE = "value";
    private static final String DATE = "data";
    private static final String MONTH = "currentmonth";
    private static final String KEY = "key";
    private static final String VALUE1 = "value1";
    private static final String NUMBER_OF_CLICK = "numeroclick";
    private static final String CLICKS ="clicks";
    private static final int FIRST_TEN = 10;
    private GregorianCalendar gc = new GregorianCalendar();
    private GeoLocation geoLoc = GeoLocation.getIstance();
    private GetInternetIP ipAdd = GetInternetIP.getIstance();
    private static String shortUrl;
    private String longUrl;
    private static DateFormatSymbols formatOfMonth = new DateFormatSymbols(Locale.ENGLISH);


    private static RedisConnection RedisConnDAO=RedisConnection.getIstance();
    private static StringMessageManager message = StringMessageManager.getIstance();

    private ArrayList<ClickStats> ClicksNumber;

    public JsonValues setJsonString(){
        JSONObject UrlAssociationJson=new JSONObject();
        JSONArray clickJsonArray=new JSONArray();
        UrlAssociationJson.put(LONGURL, longUrl);
        UrlAssociationJson.put(SHORTURL, shortUrl);

        for(ClickStats c: ClicksNumber){
            clickJsonArray.put(c.toJson());
        }

        UrlAssociationJson.put(CLICKS, clickJsonArray);
        return new JsonValues(UrlAssociationJson.toString());
    }

    public ShortURLData(String shortUrl,String longUrl){
        ClicksNumber = new ArrayList<ClickStats>();
        longUrl=longUrl.replace("http://", "");
        longUrl=longUrl.replace("https://", "");
        this.shortUrl=shortUrl;
        this.longUrl = "http://"+ longUrl;

    }

    public ShortURLData(String shortUrl, JsonValues json){
        ClicksNumber = new ArrayList<ClickStats>();
        JSONObject values = new JSONObject(json.getJsonString());
        this.shortUrl = shortUrl;
        this.longUrl = values.getString(LONGURL);
        this.shortUrl = values.getString(SHORTURL);
        JSONArray clickJArray = values.getJSONArray(CLICKS);

        for(int i = 0; i < clickJArray.length(); i++){
            ClicksNumber.add(new ClickStats(clickJArray.getJSONObject(i)));
        }

    }


    public void addNewClick(){


        String year =  (gc.get(Calendar.YEAR))+"";
        String  month = (gc.get(Calendar.MONTH) + 1) +"";
        int date = gc.get(Calendar.DATE);
        String day;
        if(date< FIRST_TEN){
            day="0"+ date;
        }else{
            day=""+ date;
        }
        String  today= day+" " + month+ " " + year;

        try {
            ClicksNumber.add(new ClickStats(today));
        }catch(Exception e){
            System.out.println(e.getMessage()+e.getCause());
        }
        RedisConnDAO.update(this);
    }


    public static boolean saveShortLongURL (String shortUrl,String longUrl){
        ShortURLData sud=new ShortURLData(shortUrl,longUrl);
        return RedisConnDAO.setKeyData(sud);
    }


    public static ShortURLData getURLData(String shortUrl) {
        return RedisConnDAO.getKeyData(shortUrl);
    }


    public static String getShortUrl() {
        return shortUrl;
    }

    public JSONArray getStats() {
        return createStats();
    }

    public  JSONArray createStats() {


        String [] maxLongUrl = longMaxClick();

        int countClickOfDay=0;
        int countClicksOfMonth = 0;
        int countClicksOfYear = 0;


        int day = gc.get(Calendar.DAY_OF_MONTH);
        int month = gc.get(Calendar.MONTH)+1;
        int year = gc.get(Calendar.YEAR);

        //conteggio dei clicks giornalieri, mensili e annuali
        for(ClickStats s : ClicksNumber){
            if(day== Integer.parseInt(s.getDate().substring(0,2))){
                if(month == Integer.parseInt(s.getDate().substring(3,4))){
                    if(year == Integer.parseInt(s.getDate().substring(5, s.getDate().length()))){
                        countClickOfDay++;
                        countClicksOfMonth++;
                        countClicksOfYear++;
                    }
                }
            }else if(month == Integer.parseInt(s.getDate().substring(3,4))){
                if(year == Integer.parseInt(s.getDate().substring(5, s.getDate().length()))){
                    countClicksOfMonth++;
                    countClicksOfYear++;
                }
            }else if(year == Integer.parseInt(s.getDate().substring(5, s.getDate().length()))){
                countClicksOfYear++;
            }
        }


        JSONArray statsJsonArray=new JSONArray();


        JSONObject numClickJson=new JSONObject();
        numClickJson.put(NAME, message.getMessage("TOTAL_CLICKS"));
        numClickJson.put(VALUE, ClicksNumber.size());
        statsJsonArray.put(numClickJson);

        JSONObject todayClickJson=new JSONObject();
        todayClickJson.put(NAME, message.getMessage("CLICKS_OF_DAY"));
        todayClickJson.put(VALUE, countClickOfDay);
        statsJsonArray.put(todayClickJson);

        JSONObject monthClickJson=new JSONObject();
        monthClickJson.put(NAME, message.getMessage("CLICKS_OF_MONTH"));
        monthClickJson.put(VALUE, countClicksOfMonth);
        statsJsonArray.put(monthClickJson);

        JSONObject yearClickJson=new JSONObject();
        yearClickJson.put(NAME, message.getMessage("CLICKS_OF_YEAR"));
        yearClickJson.put(VALUE, countClicksOfYear);
        statsJsonArray.put(yearClickJson);

        JSONObject longClick = new JSONObject();
        longClick.put(NAME, message.getMessage("MOST_CLICKED"));
        longClick.put(VALUE, maxLongUrl[0]);
        statsJsonArray.put(longClick);

        JSONObject maxClick = new JSONObject();
        maxClick.put(NAME, "Total clicks for "+ maxLongUrl[0]+ ":");
        maxClick.put(VALUE, maxLongUrl[1]);
        statsJsonArray.put(maxClick);


        return statsJsonArray;
    }

    public static String [] longMaxClick(){

        Set<String> shortUrls =RedisConnDAO.getAllShort();
        String [] maxVal = new String [2];

        ArrayList<String> longString = new ArrayList<String>();
        ArrayList<Integer> intCount = new ArrayList<Integer>();
        Iterator<String> iter = shortUrls.iterator();

        while(iter.hasNext()){
            String s = iter.next().toString();
            ShortURLData association = RedisConnDAO.getKeyData(s);
            int countClick = association.ClicksNumber.size();
            String longUrlAssociate = association.getLongUrl();
            longString.add(longUrlAssociate);
            intCount.add(countClick);

        }
        String longUrl = null;
        int maxValue= intCount.get(0);
        //calcola il longUrl con il massimo di click
        for(int i =0 ; i<intCount.size(); i++){
            if(intCount.get(i)> maxValue){
                maxValue = intCount.get(i);
                longUrl = longString.get(i);
            }
        }
        String max = String.valueOf(maxValue);
        maxVal[0] = longUrl;
        maxVal[1] = max;
        return maxVal;
    }

    public String getLongUrl(){
        return longUrl;
    }

    /**
     * @return
     */
    public JSONArray getGraph(){
        return createGraph();
    }

    public JSONArray getRequestFrom(){
        return request();
    }

    public JSONArray request(){

        int day = gc.get(Calendar.DAY_OF_MONTH);
        int month = gc.get(Calendar.MONTH)+1;
        int year = gc.get(Calendar.YEAR);

        JSONArray requestJsonArray = new JSONArray();
        String[] monthNames = formatOfMonth.getMonths();

        JSONObject addJson=new JSONObject();
        addJson.put(KEY, message.getMessage("DAY"));
        addJson.put(VALUE1, monthNames[month-1]+" "+day+", "+year);
        requestJsonArray.put(addJson);

        JSONObject countryJson = new JSONObject();
        countryJson.put(KEY, message.getMessage("COUNTRY"));
        try{
            countryJson.put(VALUE1, geoLoc.getLocationCountry(ipAdd.getInternetIP()));
        }catch(Exception e){
            e.getMessage();
        }
        requestJsonArray.put(countryJson);

        JSONObject cityJson = new JSONObject();
        cityJson.put(KEY, message.getMessage("CITY"));
        try{
            cityJson.put(VALUE1, geoLoc.getLocationCity(ipAdd.getInternetIP()));
        }catch(Exception e)
        {
            e.getMessage();
        }
        requestJsonArray.put(cityJson);

        return requestJsonArray;
    }

    public JSONArray createGraph(){
        JSONArray graphJsonArray = new JSONArray();
        //int month = gc.get(Calendar.MONTH)+1; //inserire il mese per il grafico
        int data = 0;
        int countClickInDay = 0;
        int dayMaximum= gc.getActualMaximum(Calendar.DAY_OF_MONTH);


        int click[]=new int[dayMaximum+1];

        for(int j=0; j<ClicksNumber.size();j++){
            countClickInDay=1;
            ClickStats c=ClicksNumber.get(j);

            data = Integer.parseInt(c.getDate().substring(0, 2));

            for(int i=j+1; i< ClicksNumber.size();i++){
                ClickStats c1=ClicksNumber.get(i);
                if(data==Integer.parseInt(c1.getDate().substring(0,2))){
                    countClickInDay++;
                }

            }

            click[data]= countClickInDay;
            j=j+countClickInDay-1;
        }

        JSONObject click1=new JSONObject();
        click1.put(DATE,1 );
        //click1.put(MONTH, month); //mese per grafico
        click1.put(NUMBER_OF_CLICK,click[1] );
        graphJsonArray.put(click1);

        JSONObject click2=new JSONObject();
        click2.put(DATE,2 );
        click2.put(NUMBER_OF_CLICK,click[2] );
        graphJsonArray.put(click2);


        JSONObject click3=new JSONObject();
        click3.put(DATE,3 );
        click3.put(NUMBER_OF_CLICK,click[3] );
        graphJsonArray.put(click3);

        JSONObject click4=new JSONObject();
        click4.put(DATE,4 );
        click4.put(NUMBER_OF_CLICK,click[4] );
        graphJsonArray.put(click4);

        JSONObject click5=new JSONObject();
        click5.put(DATE,5 );
        click5.put(NUMBER_OF_CLICK,click[5] );
        graphJsonArray.put(click5);

        JSONObject click6=new JSONObject();
        click6.put(DATE,6 );
        click6.put(NUMBER_OF_CLICK,click[6] );
        graphJsonArray.put(click6);

        JSONObject click7=new JSONObject();
        click7.put(DATE,7 );
        click7.put(NUMBER_OF_CLICK,click[7] );
        graphJsonArray.put(click7);

        JSONObject click8=new JSONObject();
        click8.put(DATE,8 );
        click8.put(NUMBER_OF_CLICK,click[8] );
        graphJsonArray.put(click8);

        JSONObject click9=new JSONObject();
        click9.put(DATE,9 );
        click9.put(NUMBER_OF_CLICK,click[9] );
        graphJsonArray.put(click9);

        JSONObject click10=new JSONObject();
        click10.put(DATE,10 );
        click10.put(NUMBER_OF_CLICK,click[10] );
        graphJsonArray.put(click10);

        JSONObject click11=new JSONObject();
        click11.put(DATE,11 );
        click11.put(NUMBER_OF_CLICK,click[11] );
        graphJsonArray.put(click11);

        JSONObject click12=new JSONObject();
        click12.put(DATE,12 );
        click12.put(NUMBER_OF_CLICK,click[12] );
        graphJsonArray.put(click12);

        JSONObject click13=new JSONObject();
        click13.put(DATE,13 );
        click13.put(NUMBER_OF_CLICK,click[13] );
        graphJsonArray.put(click13);

        JSONObject click14=new JSONObject();
        click14.put(DATE,14 );
        click14.put(NUMBER_OF_CLICK,click[14] );
        graphJsonArray.put(click14);

        JSONObject click15=new JSONObject();
        click15.put(DATE,15 );
        click15.put(NUMBER_OF_CLICK,click[15] );
        graphJsonArray.put(click15);

        JSONObject click16=new JSONObject();
        click16.put(DATE,16 );
        click16.put(NUMBER_OF_CLICK,click[16] );
        graphJsonArray.put(click16);

        JSONObject click17=new JSONObject();
        click17.put(DATE,17 );
        click17.put(NUMBER_OF_CLICK,click[17] );
        graphJsonArray.put(click17);

        JSONObject click18=new JSONObject();
        click18.put(DATE,18 );
        click18.put(NUMBER_OF_CLICK,click[18] );
        graphJsonArray.put(click18);

        JSONObject click19=new JSONObject();
        click19.put(DATE,19 );
        click19.put(NUMBER_OF_CLICK,click[19] );
        graphJsonArray.put(click19);

        JSONObject click20=new JSONObject();
        click20.put(DATE,20 );
        click20.put(NUMBER_OF_CLICK,click[20] );
        graphJsonArray.put(click20);


        JSONObject click21=new JSONObject();
        click21.put(DATE,21 );
        click21.put(NUMBER_OF_CLICK,click[21] );
        graphJsonArray.put(click21);

        JSONObject click22=new JSONObject();
        click22.put(DATE,22 );
        click22.put(NUMBER_OF_CLICK,click[22] );
        graphJsonArray.put(click22);

        JSONObject click23=new JSONObject();
        click23.put(DATE,23 );
        click23.put(NUMBER_OF_CLICK,click[23] );
        graphJsonArray.put(click23);

        JSONObject click24=new JSONObject();
        click24.put(DATE,24 );
        click24.put(NUMBER_OF_CLICK,click[24] );
        graphJsonArray.put(click24);

        JSONObject click25=new JSONObject();
        click25.put(DATE,25 );
        click25.put(NUMBER_OF_CLICK,click[25] );
        graphJsonArray.put(click25);

        JSONObject click26=new JSONObject();
        click26.put(DATE,26 );
        click26.put(NUMBER_OF_CLICK,click[26] );
        graphJsonArray.put(click26);

        JSONObject click27=new JSONObject();
        click27.put(DATE,27 );
        click27.put(NUMBER_OF_CLICK,click[27] );
        graphJsonArray.put(click27);

        JSONObject click28=new JSONObject();
        click28.put(DATE,28 );
        click28.put(NUMBER_OF_CLICK,click[28] );
        graphJsonArray.put(click28);

        if(dayMaximum>=29) {
            JSONObject click29 = new JSONObject();
            click29.put(DATE, 29);
            click29.put(NUMBER_OF_CLICK, click[29]);
            graphJsonArray.put(click29);
            if(dayMaximum>=30){
                JSONObject click30=new JSONObject();
                click30.put(DATE,30 );
                click30.put(NUMBER_OF_CLICK,click[30] );
                graphJsonArray.put(click30);
                if(dayMaximum==31){
                    JSONObject click31=new JSONObject();
                    click31.put(DATE,31 );
                    click31.put(NUMBER_OF_CLICK,click[31] );
                    graphJsonArray.put(click31);
                }
            }
        }

        return graphJsonArray;
    }
}
