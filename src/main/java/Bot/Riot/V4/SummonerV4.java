package Bot.Riot.V4;

import Bot.Riot.RiotAPI;
import org.json.simple.JSONObject;

public class SummonerV4 extends RiotAPI {
    static final String startURL = "/lol/summoner/v4/summoners/";
    static final String serverURL = "kr";

    public static JSONObject getSummonerByAccountID(String encryptedAccountID){
        return toJsonObject(get(startURL+"by-account/"+encryptedAccountID, serverURL));
    }
    public static JSONObject getSummonerByName(String summonerName){
        return toJsonObject(get(startURL+"by-name/"+summonerName, serverURL));
    }
    public static JSONObject getSummonerByPUUID(String encryptedPUUID){
        return toJsonObject(get(startURL+"by-puuid/"+encryptedPUUID, serverURL));
    }
    public static JSONObject getSummonerBySummonerID(String encryptedSummonerID){
        return toJsonObject(get(startURL+encryptedSummonerID, serverURL));
    }
}
