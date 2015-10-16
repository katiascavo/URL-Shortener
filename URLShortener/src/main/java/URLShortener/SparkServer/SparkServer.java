package URLShortener.SparkServer;

import static spark.Spark.*;

import URLShortener.Utility.*;

import URLShortener.util.*;
import static spark.Spark.get;
import org.json.JSONObject;

public class SparkServer {

    private static final String JSON = "responseData";
    private  static final String RESULT = "result";
    private  static final String OKAY = "okay";
    private static final String SHORTURL = "shortUrl";
    private  static final String STATS = "stats";
    private static final String REQUEST_FROM = "ipAddress";
    private  static final String LONGURL = "longUrl";
    private static final String GRAPH = "graph";
    private static StringMessageManager message = StringMessageManager.getIstance();
    static String sht;


    public static JSONObject convertToShortUrl(String longUrl) {
        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();
        URLShortener u = new URLShortener(4, "www.sht.com/");

        String shortUrl = u.shortenURL(longUrl);

        if (ShortURLData.saveShortLongURL(shortUrl,
            longUrl)) {
            data.put(RESULT, OKAY);
            data.put(SHORTURL, shortUrl);
        } else {
            data.put(RESULT, message.getMessage("CONVERT_ERROR"));
        }

        response.put(JSON, data);

        return response;
    }

    public static JSONObject saveShort(String shortUrl, String longUrl){

        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();

        if (!UndesiderableWords.checkUndesiderableWords(shortUrl)) {
            data.put(RESULT, message.getMessage("UNDESIDERABLE_WORDS"));
        } else {
            if (ShortURLData.saveShortLongURL(shortUrl,
                longUrl)) {
                data.put(RESULT, OKAY);
            } else {
                data.put(RESULT, message.getMessage("USED_SHORTURL"));
            }
        }

        response.put(JSON, data);

        return response;
    }

    public static JSONObject viewWindow(String shortUrl) {
        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();

        ShortURLData url = ShortURLData
            .getURLData(shortUrl);
        if (url != null) {
            url.addNewClick();
            data.put(RESULT, OKAY);
            data.put(LONGURL, url.getLongUrl());
        } else {
            data.put(RESULT, message.getMessage("KEY_NOT_FOUND"));
        }

        response.put(JSON, data);
        return response;
    }

    public static JSONObject getGraph() {
        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();

        ShortURLData url = ShortURLData
            .getURLData(sht);
        if (url == null) {
            data.put(RESULT, message.getMessage("KEY_NOT_FOUND"));
        } else {
            data.put(RESULT, OKAY);
            data.put(GRAPH, url.getGraph());
        }

        response.put(JSON, data);

        return response;
    }

    public static JSONObject getGraphPage(String shortUrl) {
        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();
        sht = shortUrl;

        ShortURLData url = ShortURLData
            .getURLData(shortUrl);
        if (url == null) {
            data.put(RESULT, message.getMessage("KEY_NOT_FOUND"));
        } else {
            data.put(RESULT, OKAY);
        }

        response.put(JSON, data);

        return response;
    }

    public static JSONObject getStats(String shortUrl) {
        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();


        ShortURLData url = ShortURLData
            .getURLData(shortUrl);
        if (url == null) {
            data.put(RESULT, message.getMessage("KEY_NOT_FOUND"));
        } else {
            data.put(RESULT, OKAY);
            data.put(STATS, url.getStats());
            data.put(REQUEST_FROM, url.getRequestFrom());
        }

        response.put(JSON, data);

        return response;
    }

    public static void main(final String[] args) {
        port(8080);
        externalStaticFileLocation("public"); // Static files


        get("/", (request, response) -> {
            response.redirect("/index.html");
            return "";
        });

        get("/convertToShortUrl", (request, response) -> {
            String longUrl = request.queryParams(LONGURL);
            return convertToShortUrl(longUrl).toString();
        });

        get("/saveShort", (request, response) -> {
            String shortUrl = request.queryParams(SHORTURL);
            String longUrl = request.queryParams(LONGURL);
            return saveShort(shortUrl, longUrl);
        });

        get("/viewWindow", (request, response) -> {
            String shortUrl = request.queryParams(SHORTURL);
            return viewWindow(shortUrl);
        });

        get("/getGraph", (request, response) -> {
            return getGraph();
        });

        get("/getGraphPage", (request, response) -> {
            String shortUrl = request.queryParams(SHORTURL);
            return getGraphPage(shortUrl);
        });

        get("/getStats", (request, response) -> {
            String shortUrl = request.queryParams(SHORTURL);
            return getStats(shortUrl);
        });

        before((request, response) -> {
            HibernateUtil.getSession().beginTransaction();
        });


        after((request, response) -> {
            HibernateUtil.getSession().getTransaction().commit();
            HibernateUtil.closeSession();
        });

        exception(Exception.class, (e, request, response) -> {
            HibernateUtil.getSession().getTransaction().rollback();
            HibernateUtil.closeSession();
            response.status(500);
        });

    }

}
