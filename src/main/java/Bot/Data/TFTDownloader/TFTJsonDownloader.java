package Bot.Data.TFTDownloader;

import Bot.FileManager;
import Bot.Riot.V1.TFTMatchV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TFTJsonDownloader {
    final private static Logger logger = LoggerFactory.getLogger(TFTJsonDownloader.class);

    public void check(){
        TFTMatchListDownloader.start();
        FileManager manager = new FileManager(new File("D:\\BigData\\Teamfight Tactics\\ListLoaded.txt"));
        final List<String> split = new ArrayList<>();
        split.addAll(Arrays.stream(manager.read().split("\n")).toList());
        final List<File> files = Arrays.stream(new File(manager.getFile().getPath().replace(manager.getFile().getName(), "")).listFiles()).toList();
        final List<String> fileNames = new ArrayList<>();
        files.forEach(file -> fileNames.add(file.getName().replace("D:\\BigData\\Teamfight Tactics\\", "").replace(".json", "")));


        split.stream().filter(id -> (!fileNames.contains(id))).toList().forEach(id ->{
                FileManager fileManager = new FileManager(new File("D:\\BigData\\Teamfight Tactics\\"+id+".json"));
                logger.info("downloading "+ fileManager.getFile().getName());
                fileManager.write(TFTMatchV1.getMatchByMatchID(id).toJSONString(), true);
        });
        logger.info("finished downloading");
    }
}
