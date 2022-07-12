package pl.norbit.simpleworldapi;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;
import pl.norbit.simpleworldapi.worldconfig.WorldConfig;
import pl.norbit.simpleworldapi.worldconfig.WorldConfigManager;
import pl.norbit.simpleworldapi.worldcreator.Creator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldManager {
    @Getter
    private final static List<World> loadedWorldList;

    static {
        loadedWorldList = new ArrayList<>();
    }

    public static World createWorld(SimpleWorld simpleWorld){

        try {
            WorldConfigManager.reloadConfigList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Creator.normal(simpleWorld);
    }

    public static World clone(String worldName, String cloneWoldName, boolean tempWorld){

        World template = SimpleWorldAPI.getInstance().getServer().getWorld(worldName);
        template.save();

        String worldsPath;
        worldsPath = Bukkit.getWorldContainer().getPath();

        File file = new File(worldsPath + "/" + worldName);
        String newWorldPath;

        if(tempWorld) {
            newWorldPath = worldsPath + "/temp/" + cloneWoldName;
        }else{
            newWorldPath = worldsPath + cloneWoldName;
        }
        File file2 = new File(newWorldPath);

        try {
            FileUtils.copyDirectory(file, file2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File uidFile = new File(newWorldPath + "/uid.dat");

        if(uidFile.exists()){
            uidFile.delete();
        }

        WorldCreator creator = new WorldCreator(newWorldPath);

        World world = creator.createWorld();
        WorldConfig config;
        if(tempWorld){
            config = WorldConfigManager.loadTempWorldConfig(worldName, "./temp/" + cloneWoldName);
        }else{
            try {
                config = WorldConfigManager.createWorldConfig(worldName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        world.setPVP(config.isPvp());
        world.setDifficulty(Difficulty.valueOf(config.getDifficulty()));

        return world;
    }

    protected static void loadConfigWorlds() throws IOException {

        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        configHashMap.forEach((worldName, config) -> {

            if(config.isLoadOnStart()){
                World world = new WorldCreator(worldName).createWorld();

                world.setDifficulty(Difficulty.valueOf(config.getDifficulty().toUpperCase()));
                world.setPVP(config.isPvp());

                loadedWorldList.add(world);
            }
        });
    }

    public static World loadWorld(String worldName){

        World world1 = Bukkit.getWorld(worldName);

        if(world1 != null) {
            World world = new WorldCreator(worldName).createWorld();
            loadSettings(world);

            loadedWorldList.add(world);
            return world;
        }
        return null;
    }
    private static void loadSettings(World world){

        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();
        String worldName = world.getName();

        if(configHashMap.containsKey(worldName)){
            WorldConfig config = WorldConfigManager.getConfigHashMap().get(worldName);
            world.setDifficulty(Difficulty.valueOf(config.getDifficulty()));
            world.setPVP(config.isPvp());
        }
    }
}