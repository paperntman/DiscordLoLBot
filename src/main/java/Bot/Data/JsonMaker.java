package Bot.Data;

import Bot.FileManager;
import Bot.Riot.V5.MatchV5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonMaker {

    final private static Logger logger = LoggerFactory.getLogger(JsonMaker.class);

    public static void check(){
        FileManager manager = new FileManager(new File("D:\\BigData\\ListLoaded.txt"));
        final List<String> split = new ArrayList<>();
        split.addAll(Arrays.stream(manager.read().split("\n")).toList());
        final List<File> files = Arrays.stream(new File(manager.getFile().getPath().replace(manager.getFile().getName(), "")).listFiles()).toList();
        final List<String> fileNames = new ArrayList<>();
        files.forEach(file -> fileNames.add(file.getName().replace("D:\\BigData\\", "")));


        split.stream().filter(id -> (!fileNames.contains(id))).toList().forEach(id ->{
            Thread thread = new Thread(() -> {
                FileManager fileManager = new FileManager(new File("D:\\BigData\\"+id));
                logger.info("downloading "+ fileManager.getFile().getName());
                fileManager.write(MatchV5.getMatchByMatchID(id).toJSONString(), true);
            });
            thread.setPriority(7);
            thread.run();
        });
    }

    public static void main(String[] args) {
        check();
        logger.info("Finished Downloading");
        return;
    }
}
