package Bot.Data.TFTDownloader;

import Bot.FileManager;
import Bot.Riot.V1.TFTMatchV1;
import Bot.Riot.V1.TFTSummonerV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TFTMatchListDownloader {

    final private static Logger logger = LoggerFactory.getLogger(TFTMatchListDownloader.class);

    public static void start() {
        final List<String> accounts = Arrays.asList(new FileManager("Accounts.txt").read().split(" "));
        FileManager fileManager = new FileManager(new File("D:\\BigData\\Teamfight Tactics\\ListLoaded.txt"));

        ArrayList<String> gameIds = new ArrayList<>();
        for (String s : Arrays.asList(fileManager.read().replaceAll("\n", " ").trim().split(" "))) {
            gameIds.add(s);
        }
        for (String account : accounts) {
            account = account.replaceAll("-", "%20");
                logger.info("loading {}/{}", accounts.indexOf(account)+1, accounts.size());
                final String puuid = TFTSummonerV1.getSummonerByName(account).get("puuid").toString();
                TFTMatchV1.getListOfMatchByPUUID(puuid, 100).stream().forEach(id -> {
                    if (!gameIds.contains(id)) gameIds.add((String) id);
                });
        }

        StringBuilder stringBuilder = new StringBuilder();
        gameIds.stream().sorted().forEach(id -> stringBuilder.append(id+"\n"));

        fileManager.write(stringBuilder.toString(), true);

    }
}
