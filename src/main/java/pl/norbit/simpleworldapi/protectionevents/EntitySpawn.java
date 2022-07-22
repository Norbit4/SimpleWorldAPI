package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import pl.norbit.simpleworldapi.WorldConfig;
import pl.norbit.simpleworldapi.WorldConfigManager;

import java.util.HashMap;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onEvent(EntitySpawnEvent e){

        Location loc = e.getLocation();
        String worldName = loc.getWorld().getName();
        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        if(configHashMap.containsKey(worldName)){
            WorldConfig config = configHashMap.get(worldName);

            if(!config.isSpawnMonsters()){
                e.setCancelled(true);
            }
        }
    }
}
