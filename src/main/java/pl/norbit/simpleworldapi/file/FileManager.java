package pl.norbit.simpleworldapi.file;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {

    public static void clearTempFile() throws IOException {
        File dir = new File(Bukkit.getServer().getWorldContainer().getCanonicalPath() + "\\temp");

        if(dir.exists()){
            FileUtils.deleteDirectory(dir);
        }
        Files.createDirectory(dir.toPath());
    }
}
