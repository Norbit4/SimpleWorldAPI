package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.norbit.simpleworldapi.utils.PermissionUtil;
import pl.norbit.simpleworldapi.WorldConfig;
import pl.norbit.simpleworldapi.WorldConfigManager;

import java.util.HashMap;

public class BreakBlock implements Listener {

    @EventHandler
    public void onEvent(BlockBreakEvent e){

        Player p = e.getPlayer();
        String worldName = e.getBlock().getWorld().getName();
        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        PermissionUtil permissionUtil = new PermissionUtil(p);
        String[] permList = {"*", "swapi.*", "swapi.break." + worldName};

        if (configHashMap.containsKey(worldName)) {
            WorldConfig config = configHashMap.get(worldName);

            if (!config.isBreakBlocks() && !permissionUtil.hasPermission(permList)) {
                e.setCancelled(true);
            }
        }
    }
}
