package Bot.Riot.V5;

import Bot.Riot.RiotAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MatchV5 extends RiotAPI {
    static final String startURL = "/lol/match/v5/matches/";
    static final String serverURL = "asia";

    public static JSONArray getListOfMatchByPUUID(String PUUID, int count){
        return toJsonArray(get(startURL+"by-puuid/"+PUUID+"/ids", serverURL, "&start=0&count="+String.valueOf(count)));
    }

    public static JSONObject getMatchByMatchID(String matchID){
        return toJsonObject(get(startURL+matchID, serverURL));
    }
    public static JSONObject getMatchTimelineByMatchID(String matchID){
        return toJsonObject(get(startURL+matchID+"/timeline", serverURL));
    }
}
