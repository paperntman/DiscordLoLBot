package Bot.Riot.V1;

import Bot.Riot.RiotAPI;
import org.json.simple.JSONObject;

public class TFTSummonerV1 extends RiotAPI {

    static final String startURL = "/tft/summoner/v1/";
    static final String serverURL = "kr";

    public static JSONObject getSummonerByName(String summonerName){
        return toJsonObject(get(startURL+"summoners/by-name/"+summonerName, serverURL));
    }
}
