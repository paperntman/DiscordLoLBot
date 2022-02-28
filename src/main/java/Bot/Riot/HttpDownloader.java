package Bot.Riot;

import Bot.Config;
import net.dv8tion.jda.api.exceptions.HttpException;

import java.io.IOException;
import java.net.URL;

public class HttpDownloader{

    public static String download(String URLRaw, String ServerURL) throws IOException, HttpException {
        String apikey = "";

        if(URLRaw.contains("/lol/")) apikey= Config.get("league");
        if(URLRaw.contains("/tft/")) apikey= Config.get("tft");
        if(URLRaw.contains("/lor/")) apikey= Config.get("runeterra");

        URL url = new URL("https://"+ServerURL+".api.riotgames.com"+URLRaw+"?api_key="+apikey);
        return new Bot.HttpDownloader.HttpDownloader().Download(url);
    }

    public static String download(String URLRaw, String ServerURL, String something) throws IOException, HttpException {
        String apikey = "";

        if(URLRaw.contains("/lol/")) apikey= Config.get("league");
        if(URLRaw.contains("/tft/")) apikey= Config.get("tft");
        if(URLRaw.contains("/lor/")) apikey= Config.get("runeterra");

        URL url = new URL("https://"+ServerURL+".api.riotgames.com"+URLRaw+"?api_key="+apikey+something);
        return new Bot.HttpDownloader.HttpDownloader().Download(url);
    }

    public static String download(URL url) {
        return new Bot.HttpDownloader.HttpDownloader().Download(url);
    }
}
