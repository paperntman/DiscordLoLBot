package Bot.HttpDownloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {
    private static Logger logger = LoggerFactory.getLogger(HttpDownloader.class);

    public String Download(URL url){
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(connection.getResponseCode() == 429) {
                logger.info("Rate limit exceeded. Waiting ..");
                while(connection.getResponseCode() == 429){
                    connection = (HttpURLConnection) url.openConnection();
                }
                logger.info("Done");
            }
            return new String(connection.getInputStream().readAllBytes());
        } catch (IOException e) {
            return "{}";
        }
    }
}
