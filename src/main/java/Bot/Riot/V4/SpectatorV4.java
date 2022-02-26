package Bot.Riot.V4;

import Bot.Riot.RiotAPI;
import org.json.simple.JSONObject;

public class SpectatorV4 extends RiotAPI {
    static final String startURL = "/lol/spectator/v4/";
    static final String serverURL = "kr";

    public static JSONObject getActiveGamesBySummoner(String encryptedSummonerID){
        return toJsonObject(get(startURL+"active-games/by-summoner/"+encryptedSummonerID, serverURL));
    }
    public static JSONObject getFeaturedGames(){
        return toJsonObject(get(startURL+"featured-games", serverURL));
    }
}
