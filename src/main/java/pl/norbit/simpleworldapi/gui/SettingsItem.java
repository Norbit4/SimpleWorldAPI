package pl.norbit.simpleworldapi.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

class SettingsItem {

    protected static void create(Inventory inv, ItemStack item, int pos, boolean enable) {

        inv.setItem(pos, item);
        inv.setItem(pos + 9, createItemStack(item, enable));
    }

    private static ItemStack createItemStack(ItemStack item, boolean enable){

        byte durability = 8;

        if(enable){
            durability = 10;
        }

        ItemStack itemStack = new ItemStack(Material.INK_SACK, 1);

        itemStack.setDurability(durability);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ItemMeta parentItemMeta = item.getItemMeta();

        itemMeta.setDisplayName(parentItemMeta.getDisplayName());
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
