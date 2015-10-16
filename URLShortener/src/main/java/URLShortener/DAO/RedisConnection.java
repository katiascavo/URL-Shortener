package URLShortener.DAO;


import URLShortener.Utility.JsonValues;
import URLShortener.Utility.ShortURLData;
import redis.clients.jedis.Jedis;

import java.util.Set;
public class RedisConnection {

    private Jedis redis = new Jedis("127.0.0.1",6379);

    private static final RedisConnection REDIS = new RedisConnection();


    public static RedisConnection getIstance(){
        return REDIS;
    }

    public boolean setKeyData (ShortURLData url){
        if(!isExists(url.getShortUrl())){
            return false;
        }
        REDIS.setValue(url.getShortUrl(), url.setJsonString());
        return true;
    }

    public void setValue(String shortUrl, JsonValues json){
        redis.set(shortUrl, json.getJsonString());
    }

    public boolean isExists(String shortUrl){
        return (REDIS.getValue(shortUrl)==null);
    }

    public JsonValues getValue(String shortUrl){
        redis.connect();
        String response=redis.get(shortUrl);
        if(response == null){
            return null;
        }
        JsonValues json = new JsonValues(response);
        redis.close();
        return json;
    }


    public void update(ShortURLData url){

        REDIS.setValue(url.getShortUrl(), url.setJsonString());

    }


    public ShortURLData getKeyData (String shortUrl){
        JsonValues json = REDIS.getValue(shortUrl);
        if(json == null){
            return null;
        }
        return new ShortURLData(shortUrl, json);
    }

    public Set<String> getAllShort(){
        redis.connect();
        Set<String> response;
        response = redis.keys("*");
        if(response == null){
            return null;
        }

        redis.close();
        return response;
    }
}
