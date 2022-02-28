package Bot.Data.LoLDownloader;

import Bot.FileManager;
import Bot.Riot.V5.MatchV5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoLJsonDownloader {

    final private static Logger logger = LoggerFactory.getLogger(LoLJsonDownloader.class);

    public void check(){
        LoLMatchListDownloader.start();
        FileManager manager = new FileManager(new File("D:\\BigData\\League of Legends\\ListLoaded.txt"));
        final List<String> split = new ArrayList<>();
        split.addAll(Arrays.stream(manager.read().split("\n")).toList());
        final List<File> files = Arrays.stream(new File(manager.getFile().getPath().replace(manager.getFile().getName(), "")).listFiles()).toList();
        final List<String> fileNames = new ArrayList<>();
        files.forEach(file -> fileNames.add(file.getName().replace("D:\\BigData\\League of Legends\\", "").replace(".json", "")));


        split.stream().filter(id -> (!fileNames.contains(id))).toList().forEach(id ->{
                FileManager fileManager = new FileManager(new File("D:\\BigData\\League of Legends\\"+id+".json"));
                logger.info("downloading "+ fileManager.getFile().getName());
                fileManager.write(MatchV5.getMatchByMatchID(id).toJSONString(), true);
        });
        logger.info("finished downloading");
    }
}
