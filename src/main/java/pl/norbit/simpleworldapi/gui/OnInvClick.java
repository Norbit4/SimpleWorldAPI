package pl.norbit.simpleworldapi.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnInvClick implements Listener {

    @EventHandler
    public void onEvent(InventoryClickEvent e) {

        Inventory clickedInventory = e.getClickedInventory();
        if (clickedInventory != null) {
            String name = clickedInventory.getName();
            ItemStack itemStack = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();

            if (itemStack != null) {
                switch (name) {
                    case "World settings (2/2)":
                        WorldCreatorManager.settingsPage2(p, itemStack, clickedInventory);
                        e.setCancelled(true);
                        break;
                    case "World settings (1/2)":
                        WorldCreatorManager.settingsPage1(p, itemStack, clickedInventory);
                        e.setCancelled(true);
                        break;
                    case "Creator":
                        WorldCreatorManager.creator(p, itemStack);
                        e.setCancelled(true);
                        break;
                }
            }
        }
    }
}
