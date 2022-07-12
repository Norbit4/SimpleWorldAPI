package pl.norbit.simpleworldapi.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import pl.norbit.simpleworldapi.worldconfig.Config;
import pl.norbit.simpleworldapi.worldconfig.ConfigManager;

import java.util.HashMap;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onEvent(EntitySpawnEvent e){

        Location loc = e.getLocation();
        String worldName = loc.getWorld().getName();
        HashMap<String, Config> configHashMap = ConfigManager.getConfigHashMap();

        if(configHashMap.containsKey(worldName)){
            Config config = configHashMap.get(worldName);

            if(!config.isSpawnEntities()){
                e.setCancelled(true);
            }
        }

    }
}
