package Bot.Riot.V1;

import Bot.Riot.RiotAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TFTMatchV1 extends RiotAPI {
    static final String startURL = "/tft/match/v1/matches/";
    static final String serverURL = "asia";

    public static JSONArray getListOfMatchByPUUID(String PUUID, int count){
        return toJsonArray(get(startURL+"by-puuid/"+PUUID+"/ids", serverURL, "&count="+ count));
    }

    public static JSONObject getMatchByMatchID(String matchID){
        return toJsonObject(get(startURL+matchID, serverURL));
    }
}
