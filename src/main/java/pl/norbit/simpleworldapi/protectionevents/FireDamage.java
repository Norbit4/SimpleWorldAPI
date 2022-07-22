package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.norbit.simpleworldapi.WorldConfig;
import pl.norbit.simpleworldapi.WorldConfigManager;

import java.util.HashMap;

public class FireDamage implements Listener {

    @EventHandler
    public void onEvent(EntityDamageEvent e){

        if(e.getEntity() instanceof Player) {
            String worldName = e.getEntity().getWorld().getName();
            HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

            EntityDamageEvent.DamageCause cause = e.getCause();

            if(
                    cause == EntityDamageEvent.DamageCause.LAVA
                    || cause == EntityDamageEvent.DamageCause.FIRE
                    || cause == EntityDamageEvent.DamageCause.FIRE_TICK
            ){
                if (configHashMap.containsKey(worldName)) {
                    WorldConfig config = configHashMap.get(worldName);

                    if (!config.isFireDamage()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
