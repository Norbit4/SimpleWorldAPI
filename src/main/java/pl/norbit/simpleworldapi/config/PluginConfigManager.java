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

        //settings
        PluginConfig.GENERATE_STRUCTURES = config.getBoolean("generate-structures");
        PluginConfig.SIMPLE_WORLD_TYPE = config.getString("world-type");
        PluginConfig.DIFFICULTY = config.getString("difficulty");
        PluginConfig.PVP = config.getBoolean("pvp");
        PluginConfig.TIME = config.getBoolean("time");
        PluginConfig.WEATHER = config.getBoolean("weather");
        PluginConfig.SPAWN_ENTITIES = config.getBoolean("spawn-entities");
        PluginConfig.EXPLOSION_BREAK = config.getBoolean("explosion-break");
        PluginConfig.CENTER_BLOCK = config.getBoolean("center-block");

        //messages
        PluginConfig.PERMISSION_MESSAGE = config.getString("permission-message");
        PluginConfig.WRONG_ARGS_PREFIX = config.getString("wrong-args-prefix");
        PluginConfig.WORLD_LOAD_MESSAGE = config.getString("world-load-message");
        PluginConfig.WORLD_CREATE_MESSAGE = config.getString("world-create-message");
        PluginConfig.WORLD_CLONE_MESSAGE = config.getString("world-clone-message");
        PluginConfig.TP_MESSAGE = config.getString("tp-message");
        PluginConfig.UNLOADED_WORLD = config.getString("unloaded-world");
        PluginConfig.NON_EXISTENT_WORLD= config.getString("non-existent-world");
    }
}
