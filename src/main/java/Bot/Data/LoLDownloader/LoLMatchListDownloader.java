package Bot.Data.LoLDownloader;

import Bot.FileManager;
import Bot.Riot.V4.SummonerV4;
import Bot.Riot.V5.MatchV5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoLMatchListDownloader {

    final private static Logger logger = LoggerFactory.getLogger(LoLMatchListDownloader.class);

    public static void start() {
        final List<String> accounts = Arrays.asList(new FileManager("Accounts.txt").read().split(" "));
        FileManager fileManager = new FileManager(new File("D:\\BigData\\League of Legends\\ListLoaded.txt"));

        ArrayList<String> gameIds = new ArrayList<>();
        for (String s : Arrays.asList(fileManager.read().replaceAll("\n", " ").trim().split(" "))) {
            gameIds.add(s);
        }
        for (String account : accounts) {
            logger.info("loading {}/{}", accounts.indexOf(account)+1, accounts.size());
            account = account.replaceAll("-", "%20");
            String finalAccount = account;
            final String puuid = SummonerV4.getSummonerByName(finalAccount).get("puuid").toString();
            MatchV5.getListOfMatchByPUUID(puuid, 100).stream().forEach(id -> {if (!gameIds.contains(id)) gameIds.add((String) id);});
        }

        StringBuilder stringBuilder = new StringBuilder();
        gameIds.stream().sorted().forEach(id -> stringBuilder.append(id+"\n"));

        fileManager.write(stringBuilder.toString(), true);

    }


}
