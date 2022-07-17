package pl.norbit.simpleworldapi.gui;

import lombok.Builder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.norbit.simpleworldapi.utils.ChatUtil;

import java.util.List;

@Builder
class ItemStackConstructor {

    private String name;
    private List<String> lore;
    @Builder.Default
    private int amount = 1;
    @Builder.Default
    private byte durability = 0;

    @Builder.Default
    private Material material = Material.BEDROCK;

    public ItemStack getItemStack(){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setDurability(durability);

        if(name != null){
            itemMeta.setDisplayName(ChatUtil.format(name));
        }

        if(lore != null){
            lore.replaceAll(ChatUtil::format);
            itemMeta.setLore(lore);
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
