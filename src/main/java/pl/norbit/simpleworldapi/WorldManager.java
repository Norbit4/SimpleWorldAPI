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

        return Creator.normal(simpleWorld);
    }

    public static World clone(String worldName, String cloneWoldName, boolean tempWorld){

        World template = SimpleWorldAPI.getInstance().getServer().getWorld(worldName);
        Bukkit.getServer().unloadWorld(template, true);

        String worldsPath;

        worldsPath = Bukkit.getWorldContainer().getPath();

        File file = new File(worldsPath + "/" + worldName);
        System.out.println(worldsPath);
        String newWorldPath;

        if(tempWorld) {
            newWorldPath = "/temp/" + cloneWoldName;
        }else{
            newWorldPath = cloneWoldName;
        }
        File file2 = new File(worldsPath + "/" + newWorldPath);

        try {
            FileUtils.copyDirectory(file, file2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File uidFile = new File(worldsPath + "/" + newWorldPath + "/uid.dat");

        if(uidFile.exists()){
            uidFile.delete();
        }

        System.out.println(newWorldPath);

        WorldCreator creator = new WorldCreator(newWorldPath);

        World world = creator.createWorld();

        WorldConfig config;

        if(tempWorld) {
            config = WorldConfigManager.loadTempWorldConfig(worldName, "/temp/" + cloneWoldName);
        }else{
            try {
                config = WorldConfigManager.createWorldConfig(worldName, cloneWoldName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(!config.isTemplate()){
            new WorldCreator(worldName).createWorld();
        }

        world.setPVP(config.isPvp());
        world.setDifficulty(Difficulty.valueOf(config.getDifficulty()));

        return world;
    }

    protected static void loadConfigWorlds() throws IOException {

        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        configHashMap.forEach((worldName, config) -> {

            if(config.isLoadOnStart()){
                File file = new File(worldName);

                if(file.exists()) {
                    World world = new WorldCreator(worldName).createWorld();

                    world.setDifficulty(Difficulty.valueOf(config.getDifficulty().toUpperCase()));
                    world.setPVP(config.isPvp());

                    loadedWorldList.add(world);
                }else{
                    System.out.println("World " + worldName +" does not exist!");
                }
            }
        });
    }

    public static World loadWorld(String worldName){

        File file = new File(Bukkit.getWorldContainer().getPath() + worldName);
        if(file.exists()) {
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