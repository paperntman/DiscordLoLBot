package Bot.HttpDownloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class HttpDownloader {
    static int per2Minute = 100;
    static int perSecond = 20;
    private static Logger logger = LoggerFactory.getLogger(HttpDownloader.class);

    public static void Start(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                perSecond = 20;
            }
        }, 0, 1000);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                per2Minute = 100;
            }
        }, 0, 120000);}

    public static synchronized String Download(URL url){
        while(perSecond == 0 || per2Minute == 0);
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
