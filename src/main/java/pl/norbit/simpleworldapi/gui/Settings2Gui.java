package pl.norbit.simpleworldapi.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;

class Settings2Gui {

    protected static void open(Player p, SimpleWorld simpleWorld){

        Inventory worldSettings = Bukkit.createInventory(null, 54, "World settings (2/2)");

        /*
        #-----------------------------------------------------------#
        #                     SettingsItems                         #
        #-----------------------------------------------------------#
        */

        //fall damage
        ItemStackConstructor builderFallDamage = ItemStackConstructor
                .builder()
                .name("&eFall damage")
                .material(Material.FEATHER)
                .build();
        ItemStack itemStackPVP = builderFallDamage.getItemStack();

        worldSettings.setItem(1, itemStackPVP);
        SettingsItem.create(worldSettings, itemStackPVP, 1, simpleWorld.isFallDamage());

        //center block
        ItemStackConstructor builderCenterBlock = ItemStackConstructor
                .builder()
                .name("&eCenter block")
                .build();
        ItemStack itemStackCenterBlock = builderCenterBlock.getItemStack();

        worldSettings.setItem(3, itemStackCenterBlock);
        SettingsItem.create(worldSettings, itemStackCenterBlock, 3, simpleWorld.isGenerateCenterBlock());

        //center block
        ItemStackConstructor builderCenterTemp = ItemStackConstructor
                .builder()
                .name("&cTemp world")
                .material(Material.REDSTONE)
                .build();
        ItemStack itemStackTemp = builderCenterTemp.getItemStack();

        worldSettings.setItem(5, itemStackTemp);
        SettingsItem.create(worldSettings, itemStackTemp, 5, simpleWorld.isTemporaryWorld());

        //template world
        ItemStackConstructor builderTemplate = ItemStackConstructor
                .builder()
                .name("&eTemplate world")
                .material(Material.ITEM_FRAME)
                .build();
        ItemStack itemStackTemplate = builderTemplate.getItemStack();

        worldSettings.setItem(7, itemStackTemplate);
        SettingsItem.create(worldSettings, itemStackTemplate, 7, simpleWorld.isTemplateWorld());

        //break blocks
        ItemStackConstructor builderBreakBlocks = ItemStackConstructor
                .builder()
                .name("&eBreak blocks")
                .material(Material.DIAMOND_PICKAXE)
                .build();
        ItemStack itemStackBreakBlocks = builderBreakBlocks.getItemStack();

        worldSettings.setItem(28, itemStackBreakBlocks);
        SettingsItem.create(worldSettings, itemStackBreakBlocks, 28, simpleWorld.isBreakBlocks());

        //place blocks
        ItemStackConstructor builderPlaceBlocks = ItemStackConstructor
                .builder()
                .name("&ePlace blocks")
                .material(Material.BRICK)
                .build();
        ItemStack itemStackPlaceBlocks = builderPlaceBlocks.getItemStack();

        worldSettings.setItem(30, itemStackPlaceBlocks);
        SettingsItem.create(worldSettings, itemStackPlaceBlocks, 30, simpleWorld.isPlaceBlocks());

        //place blocks
        ItemStackConstructor builderGravity = ItemStackConstructor
                .builder()
                .name("&eGravity")
                .material(Material.GRAVEL)
                .build();
        ItemStack itemStackGravity = builderGravity.getItemStack();

        worldSettings.setItem(32, itemStackGravity);
        SettingsItem.create(worldSettings, itemStackGravity, 32, simpleWorld.isGravity());

        //drown damage
        ItemStackConstructor builderDrownDamage = ItemStackConstructor
                .builder()
                .name("&eDrown damage")
                .material(Material.WATER_BUCKET)
                .build();
        ItemStack itemStackDrownDamage = builderDrownDamage.getItemStack();

        worldSettings.setItem(34, itemStackDrownDamage);
        SettingsItem.create(worldSettings, itemStackDrownDamage, 34, simpleWorld.isDrownDamage());

        /*
        #-----------------------------------------------------------#
        #                      NavItems                             #
        #-----------------------------------------------------------#
        */

        //accept
        ItemStackConstructor builderAcceptSettings = ItemStackConstructor
                .builder()
                .name("&aAccept settings")
                .material(Material.WOOL)
                .durability((byte) 5)
                .build();
        ItemStack itemStackAcceptSettings = builderAcceptSettings.getItemStack();

        worldSettings.setItem(49, itemStackAcceptSettings);

        //next page
        ItemStackConstructor builderNextPage = ItemStackConstructor
                .builder()
                .name("&7<- Previous page")
                .material(Material.ARROW)
                .build();
        ItemStack itemStackNextPage = builderNextPage.getItemStack();

        worldSettings.setItem(45, itemStackNextPage);

        //open gui
        p.openInventory(worldSettings);
    }
}
