package pl.norbit.simpleworldapi.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.SimpleWorldAPI;
import pl.norbit.simpleworldapi.worldconfig.ConfigManager;

public class TimeTask {

    public static void run(){

        JavaPlugin javaPlugin = SimpleWorldAPI.getInstance();

        javaPlugin.getServer().getScheduler().runTaskTimer(javaPlugin, () ->{
            ConfigManager.getConfigHashMap().forEach((worldName, config) -> {
                if(!config.isDayCycle()){
                    Bukkit.getWorld(worldName).setTime(0L);
                }
            });

        },1,20 * 10);

    }
}
