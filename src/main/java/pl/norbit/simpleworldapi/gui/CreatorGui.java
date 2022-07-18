package pl.norbit.simpleworldapi.gui;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;
import pl.norbit.simpleworldapi.worldbuilder.enums.SimpleWorldType;

import java.util.Arrays;
import java.util.List;

class CreatorGui {

    protected static void open(Player p, SimpleWorld simpleWorld){

        Inventory worldSettings = Bukkit.createInventory(null, 54, "Creator");

        /*
        #-----------------------------------------------------------#
        #                     SettingsItems                         #
        #-----------------------------------------------------------#
        */

        //name
        ItemStackConstructor builderName = ItemStackConstructor
                .builder()
                .name("&a" + simpleWorld.getWorldName())
                .material(Material.SIGN)
                .build();
        ItemStack itemName = builderName.getItemStack();

        worldSettings.setItem(4, itemName);

        //generator type
        SimpleWorldType simpleWorldType = simpleWorld.getSimpleWorldType();
        Material material = Material.SIGN;

        switch (simpleWorldType){
            case VOID:
                material = Material.FEATHER;
                break;
            case NORMAL:
                material = Material.GRASS;
                break;
            case FLAT:
                material = Material.DIRT;
                break;
            case LARGE_BIOMES:
                material = Material.STONE;
                break;
            case NETHER:
                material = Material.NETHERRACK;
                break;
            case END:
                material = Material.ENDER_STONE;
                break;
        }

        List<String> loreGeneratorType = Arrays.asList(
                "",
                "&7Generator: &c" + simpleWorld.getSimpleWorldType().name(),
                "",
                "&eClick to change!"
        );

        ItemStackConstructor builderGeneratorType = ItemStackConstructor
                .builder()
                .name("&eGenerator type")
                .lore(loreGeneratorType)
                .material(material)
                .build();
        ItemStack itemGeneratorType = builderGeneratorType.getItemStack();

        worldSettings.setItem(20, itemGeneratorType);

        //difficulty
        Difficulty difficulty = simpleWorld.getDifficulty();
        String prefix = "";
        byte durability = 0;

        switch (difficulty){
            case PEACEFUL:
                durability = 3;
                prefix = "&b";
                break;
            case EASY:
                durability = 5;
                prefix = "&a";
                break;
            case NORMAL:
                durability = 4;
                prefix = "&e";
                break;
            case HARD:
                durability = 14;
                prefix = "&c";
                break;
        }

        List<String> loreDifficulty = Arrays.asList(
                "",
                "&7Difficulty: "+ prefix + difficulty.name(),
                "",
                "&eClick to change!"
        );

        ItemStackConstructor builderDifficulty = ItemStackConstructor
                .builder()
                .lore(loreDifficulty)
                .name("&eDifficulty")
                .material(Material.STAINED_CLAY)
                .durability(durability)
                .build();
        ItemStack itemStackDifficulty = builderDifficulty.getItemStack();

        worldSettings.setItem(22, itemStackDifficulty);

        //settings
        List<String> loreSettings = Arrays.asList(
                "",
                "&7Global world settings",
                "",
                "&eClick to change!"
        );

        ItemStackConstructor builderSettings = ItemStackConstructor
                .builder()
                .lore(loreSettings)
                .name("&eWorld settings")
                .material(Material.REDSTONE_COMPARATOR)
                .build();

        ItemStack itemStackSettings = builderSettings.getItemStack();

        worldSettings.setItem(24, itemStackSettings);

        /*
        #-----------------------------------------------------------#
        #                      NavItems                             #
        #-----------------------------------------------------------#
        */

        //accept
        ItemStackConstructor builderCreateWorld = ItemStackConstructor
                .builder()
                .name("&aCreate world")
                .material(Material.WOOL)
                .durability((byte) 5)
                .build();
        ItemStack itemStackCreateWorld = builderCreateWorld.getItemStack();

        worldSettings.setItem(49, itemStackCreateWorld);

        //cancel
        ItemStackConstructor builderCancelCreate = ItemStackConstructor
                .builder()
                .name("&cCancel")
                .material(Material.WOOL)
                .durability((byte) 14)
                .build();
        ItemStack itemStackCancelCreate = builderCancelCreate.getItemStack();

        worldSettings.setItem(45, itemStackCancelCreate);

        //open gui
        p.openInventory(worldSettings);
    }
}
