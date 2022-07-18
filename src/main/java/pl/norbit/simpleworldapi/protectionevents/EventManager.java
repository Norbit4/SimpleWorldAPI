package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.SimpleWorldAPI;
import pl.norbit.simpleworldapi.gui.OnInvClick;

public class EventManager {

    public static void registerEvents(){
        JavaPlugin javaPlugin = SimpleWorldAPI.getInstance();
        PluginManager pluginManager = javaPlugin.getServer().getPluginManager();

        pluginManager.registerEvents(new Explosion(), javaPlugin);
        //pluginManager.registerEvents(new EntitySpawn(), javaPlugin);
        pluginManager.registerEvents(new Weather(), javaPlugin);
        pluginManager.registerEvents(new DrownDamage(), javaPlugin);
        pluginManager.registerEvents(new FallDamage(), javaPlugin);
        pluginManager.registerEvents(new FireDamage(), javaPlugin);
        pluginManager.registerEvents(new OnInvClick(), javaPlugin);
        pluginManager.registerEvents(new BreakBlock(), javaPlugin);
        pluginManager.registerEvents(new PlaceBlock(), javaPlugin);
        pluginManager.registerEvents(new Gravity(), javaPlugin);
    }
}
