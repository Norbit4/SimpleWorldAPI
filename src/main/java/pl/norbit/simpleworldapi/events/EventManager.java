package pl.norbit.simpleworldapi.events;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.SimpleWorldAPI;

public class EventManager {

    public static void registerEvents(){
        JavaPlugin javaPlugin = SimpleWorldAPI.getInstance();
        PluginManager pluginManager = javaPlugin.getServer().getPluginManager();

        pluginManager.registerEvents(new Explosion(), javaPlugin);
        pluginManager.registerEvents(new EntitySpawn(), javaPlugin);
        pluginManager.registerEvents(new Weather(), javaPlugin);
    }
}
