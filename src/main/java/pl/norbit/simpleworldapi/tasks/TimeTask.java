package pl.norbit.simpleworldapi.tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.SimpleWorldAPI;
import pl.norbit.simpleworldapi.worldconfig.WorldConfigManager;

public class TimeTask {

    public static void run(){

        JavaPlugin javaPlugin = SimpleWorldAPI.getInstance();

        javaPlugin.getServer().getScheduler().runTaskTimer(javaPlugin, () ->{
            WorldConfigManager.getConfigHashMap().forEach((worldName, config) -> {
                if(!config.isTime()){
                    World world = Bukkit.getWorld(worldName);
                    if(world != null){
                        world.setTime(0L);
                    }
                }
            });

        },1,20 * 10);

    }
}
