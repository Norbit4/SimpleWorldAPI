package pl.norbit.simpleworldapi.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.SimpleWorldAPI;

public class PluginConfigManager {

    public static void load(){
        JavaPlugin javaPlugin = SimpleWorldAPI.getInstance();
        FileConfiguration config = javaPlugin.getConfig();
        config.options().copyDefaults();
        javaPlugin.saveDefaultConfig();

        PluginConfig.GENERATE_STRUCTURES = config.getBoolean("generate-structures");
        PluginConfig.SIMPLE_WORLD_TYPE = config.getString("world-type");
        PluginConfig.DIFFICULTY = config.getString("difficulty");
        PluginConfig.PVP = config.getBoolean("pvp");
        PluginConfig.TIME = config.getBoolean("time");
        PluginConfig.WEATHER = config.getBoolean("weather");
        PluginConfig.SPAWN_ENTITIES = config.getBoolean("spawn-entities");
        PluginConfig.EXPLOSION_BREAK = config.getBoolean("explosion-break");
        PluginConfig.CENTER_BLOCK = config.getBoolean("center-block");
        PluginConfig.PERMISSION_MESSAGE = config.getString("permission-message");
        PluginConfig.WRONG_ARGS_PREFIX = config.getString("wrong-args-prefix");
    }
}
