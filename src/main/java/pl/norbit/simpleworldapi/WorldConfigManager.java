package pl.norbit.simpleworldapi;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class WorldConfigManager {
    private static Gson gson;
    private static String worldFolderName, path;
    @Getter
    private final static HashMap<String, WorldConfig> configHashMap;

    static {
        configHashMap = new HashMap<>();
    }

    protected static void init(JavaPlugin javaPlugin){

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();

        worldFolderName = "worlds";
        path = javaPlugin.getDataFolder().getAbsolutePath() +"/" + worldFolderName;

        File file = new File(path);

        if(!file.exists()){
            try {
                Files.createDirectory(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            reloadConfigList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static HashMap<String, WorldConfig> reloadConfigList() throws IOException {
        File file = WorldConfigManager.getConfigDirectory();

        for (File configFile : Objects.requireNonNull(file.listFiles())) {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(configFile.getAbsolutePath()));

            WorldConfig config = gson.fromJson(bufferedReader, WorldConfig.class);
            String[] split = configFile.getName().split("\\.");
            String worldName = split[0];

            configHashMap.put(worldName, config);
        }
        return configHashMap;
    }

    protected static WorldConfig loadTempWorldConfig(String world, String cloneWorld){

        if(configHashMap.containsKey(world)) {
            WorldConfig config = configHashMap.get(world);

            configHashMap.put(cloneWorld, config);

            return config;
        }

        configHashMap.forEach((key, value) -> {
            System.out.println(key);
        });

        return null;
    }

    protected static WorldConfig createWorldConfig(String copyWorldName, String newWorld) throws IOException {
        String worldPath = path + "/" + newWorld + ".json";

        if(configHashMap.containsKey(copyWorldName)) {

            PrintWriter writer = new PrintWriter(new FileWriter(worldPath));
            WorldConfig config = configHashMap.get(copyWorldName);

            writer.write(gson.toJson(config));
            writer.flush();
            writer.close();

            configHashMap.put(newWorld, config);

            return config;
        }
        return createWorldConfig(SimpleWorld.builder().worldName(newWorld).build(), false);
    }

    public static WorldConfig createWorldConfig(SimpleWorld simpleWorld, boolean tempWorld) throws IOException {
        WorldConfig config = new WorldConfig(
                simpleWorld.isPvp(),
                simpleWorld.isLoadOnStart(),
                simpleWorld.isSpawnMonsters(),
                simpleWorld.isSpawnAnimals(),
                simpleWorld.isExplosionsBreak(),
                simpleWorld.isWeather(),
                simpleWorld.isTime(),
                simpleWorld.isFireDamage(),
                simpleWorld.isFireSpread(),
                simpleWorld.isFallDamage(),
                simpleWorld.isBreakBlocks(),
                simpleWorld.isPlaceBlocks(),
                simpleWorld.isDrownDamage(),
                simpleWorld.isGravity(),
                simpleWorld.isTemplateWorld(),
                simpleWorld.getDifficulty().name()
        );

        String worldName = "/temp/" + simpleWorld.getWorldName();

        if(!tempWorld) {
            String worldPath = path + "/" + simpleWorld.getWorldName() + ".json";

            PrintWriter writer = new PrintWriter(new FileWriter(worldPath));

            writer.write(gson.toJson(config));
            writer.flush();
            writer.close();
            worldName = simpleWorld.getWorldName();
        }
        configHashMap.put(worldName, config);

        return config;
    }

    public static File getConfigDirectory(){
        return new File(path);
    }
}
