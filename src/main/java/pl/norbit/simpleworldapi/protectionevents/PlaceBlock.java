package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.norbit.simpleworldapi.utils.PermissionUtil;
import pl.norbit.simpleworldapi.worldconfig.WorldConfig;
import pl.norbit.simpleworldapi.worldconfig.WorldConfigManager;

import java.util.HashMap;

public class PlaceBlock implements Listener {

    @EventHandler
    public void onEvent(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        String worldName = e.getBlock().getWorld().getName();
        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        PermissionUtil permissionUtil = new PermissionUtil(p);
        String[] permList = {"*", "swapi.*", "swapi.build." + worldName};

        if (configHashMap.containsKey(worldName)) {
            WorldConfig config = configHashMap.get(worldName);

            if (!config.isPlaceBlocks() && !permissionUtil.hasPermission(permList)) {
                e.setCancelled(true);
            }
        }
    }
}