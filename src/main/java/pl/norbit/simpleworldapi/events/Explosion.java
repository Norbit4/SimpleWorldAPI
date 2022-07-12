package pl.norbit.simpleworldapi.events;
;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.norbit.simpleworldapi.worldconfig.WorldConfig;
import pl.norbit.simpleworldapi.worldconfig.WorldConfigManager;

import java.util.HashMap;

public class Explosion implements Listener {

    @EventHandler
    public void onEvent(EntityExplodeEvent e){

        Location loc = e.getLocation();
        String worldName = loc.getWorld().getName();
        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        if(configHashMap.containsKey(worldName)){
            WorldConfig config = configHashMap.get(worldName);

            if(!config.isExplosions()){
                e.blockList().clear();
            }
        }
    }
}
