package Bot;

import Bot.Commands.CommandManager;
import Bot.Riot.HttpDownloader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static JDA jda;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static Map<Long, String> champions = new HashMap<>();

    public static void main(String[] args) throws LoginException, IOException, InterruptedException, ParseException {
        new Main();
    }

    private Main() throws LoginException, InterruptedException, IOException, ParseException {
        jda = JDABuilder.createDefault(Config.get("token")).addEventListeners(new Listener(), new CommandManager()).build().awaitReady();
        championUpdate();
        CommandManager.CommandManager(jda);
    }

    private void championUpdate() throws IOException, ParseException {
        FileManager fileManager = new FileManager("Champions.json");
        fileManager.write(HttpDownloader.download(new URL("https://ddragon.leagueoflegends.com/cdn/12.4.1/data/ko_KR/champion.json")), true);
        JSONObject jsonObject = (JSONObject) ((JSONObject) new JSONParser().parse(fileManager.read())).get("data");
        jsonObject.forEach((a, b) -> champions.put(
                Long.valueOf(((JSONObject) b).get("key").toString()),
                ((JSONObject) b).get("name").toString()
        ));
    }
}
