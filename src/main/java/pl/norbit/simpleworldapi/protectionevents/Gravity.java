package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import pl.norbit.simpleworldapi.WorldConfig;
import pl.norbit.simpleworldapi.WorldConfigManager;

import java.util.HashMap;

public class Gravity implements Listener {

    @EventHandler
    public void onEvent(BlockPhysicsEvent e){

        String worldName = e.getBlock().getWorld().getName();
        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        if (configHashMap.containsKey(worldName)) {
            WorldConfig config = configHashMap.get(worldName);

            if (!config.isGravity()){
                e.setCancelled(true);
            }
        }
    }
}
