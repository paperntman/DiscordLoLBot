package Bot.Riot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RiotAPI {
    public static Object get(String url, String ServerURL){
        try {
            return new JSONParser().parse(HttpDownloader.download(url, ServerURL));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if(e.getMessage().startsWith("Server returned HTTP response code: 502")){
                return get(url, ServerURL);
            }
        }
        return null;
    }

    public static Object get(String url, String ServerURL, String something){
        try {
            return new JSONParser().parse(HttpDownloader.download(url,ServerURL, something));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject toJsonObject(Object o){
        return (JSONObject) o;
    }

    public static JSONArray toJsonArray(Object o){
        return (JSONArray) o;
    }
}
