package Bot;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager{
    @NotNull File file;

    public FileManager(String fileName) {
        file = new File(new File(".").getAbsolutePath() + fileName);
    }

    public FileManager(File file) {
        this.file = file;
    }

    public String read(){
        try {
            return new String(Files.readAllBytes(Path.of(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void write(String s, boolean reset){
        try {
            if(reset)
                Files.write(file.toPath(), s.getBytes());
            else
                Files.write(file.toPath(), (read()+s).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
}
