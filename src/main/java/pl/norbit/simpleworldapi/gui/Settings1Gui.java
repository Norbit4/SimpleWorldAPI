package pl.norbit.simpleworldapi.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;

class Settings1Gui {

    protected static void open(Player p, SimpleWorld simpleWorld){

        Inventory worldSettings = Bukkit.createInventory(null, 54, "World settings (1/2)");

        /*
        #-----------------------------------------------------------#
        #                     SettingsItems                         #
        #-----------------------------------------------------------#
        */

        //pvp
        ItemStackConstructor builderPVP = ItemStackConstructor
                .builder()
                .name("&ePVP")
                .material(Material.IRON_SWORD)
                .build();
        ItemStack itemStackPVP = builderPVP.getItemStack();

        worldSettings.setItem(0, itemStackPVP);
        SettingsItem.create(worldSettings, itemStackPVP, 0, simpleWorld.isPvp());

        //time
        ItemStackConstructor builderTime = ItemStackConstructor
                .builder()
                .name("&eTime cycle")
                .material(Material.COMPASS)
                .build();
        ItemStack itemStackTime = builderTime.getItemStack();

        worldSettings.setItem(2, itemStackTime);
        SettingsItem.create(worldSettings, itemStackTime, 2, simpleWorld.isTime());

        //weather
        ItemStackConstructor builderWeather = ItemStackConstructor
                .builder()
                .name("&eWeather cycle")
                .material(Material.DOUBLE_PLANT)
                .build();
        ItemStack itemStackWeather = builderWeather.getItemStack();

        worldSettings.setItem(4, itemStackWeather);
        SettingsItem.create(worldSettings, itemStackWeather, 4, simpleWorld.isWeather());

        //fire damage
        ItemStackConstructor builderFireDamage = ItemStackConstructor
                .builder()
                .name("&eFire damage")
                .material(Material.LAVA_BUCKET)
                .build();
        ItemStack itemStackFireDamage = builderFireDamage.getItemStack();

        worldSettings.setItem(6, itemStackFireDamage);
        SettingsItem.create(worldSettings, itemStackFireDamage, 6, simpleWorld.isFireDamage());

        //fire spread
        ItemStackConstructor builderFireSpread = ItemStackConstructor
                .builder()
                .name("&eFire spread")
                .material(Material.FLINT_AND_STEEL)
                .build();
        ItemStack itemStackFireSpread = builderFireSpread.getItemStack();

        worldSettings.setItem(8, itemStackFireSpread);
        SettingsItem.create(worldSettings, itemStackFireSpread, 8, simpleWorld.isFireSpread());

        //tnt explosions
        ItemStackConstructor builderTntExplosions = ItemStackConstructor
                .builder()
                .name("&eTnt explosions")
                .material(Material.TNT)
                .build();
        ItemStack itemStackTntExplosions = builderTntExplosions.getItemStack();

        worldSettings.setItem(27, itemStackTntExplosions);
        SettingsItem.create(worldSettings, itemStackTntExplosions, 27, simpleWorld.isExplosionsBreak());

        //tnt explosions
        ItemStackConstructor builderLOadOnStart = ItemStackConstructor
                .builder()
                .name("&eLoad on start")
                .material(Material.REDSTONE_TORCH_ON)
                .build();
        ItemStack itemStackLoadOnStart = builderLOadOnStart.getItemStack();

        worldSettings.setItem(29, itemStackLoadOnStart);

        //generate structures
        SettingsItem.create(worldSettings, itemStackLoadOnStart, 29, simpleWorld.isLoadOnStart());

        ItemStackConstructor builderGenerateStructures = ItemStackConstructor
                .builder()
                .name("&eGenerate structures")
                .material(Material.ENDER_PORTAL_FRAME)
                .build();
        ItemStack itemStackGenerateStructures = builderGenerateStructures.getItemStack();

        worldSettings.setItem(31, itemStackGenerateStructures);
        SettingsItem.create(worldSettings, itemStackGenerateStructures, 31, simpleWorld.isGenerateStructures());

        //Spawn monsters
        ItemStackConstructor builderSpawnMonsters = ItemStackConstructor
                .builder()
                .name("&eSpawn monsters")
                .material(Material.MONSTER_EGG)
                .durability((byte) 54)
                .build();
        ItemStack itemStackSpawnMonsters = builderSpawnMonsters.getItemStack();

        worldSettings.setItem(33, itemStackSpawnMonsters);
        SettingsItem.create(worldSettings, itemStackSpawnMonsters, 33, simpleWorld.isSpawnMonsters());

        //Spawn animals
        ItemStackConstructor builderSpawnAnimals = ItemStackConstructor
                .builder()
                .name("&eSpawn animals")
                .material(Material.MONSTER_EGG)
                .durability((byte) 90)
                .build();
        ItemStack itemStackSpawnAnimals = builderSpawnAnimals.getItemStack();

        worldSettings.setItem(35, itemStackSpawnAnimals);
        SettingsItem.create(worldSettings, itemStackSpawnAnimals, 35, simpleWorld.isSpawnAnimals());

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
                .name("&7Next page ->")
                .material(Material.ARROW)
                .build();
        ItemStack itemStackNextPage = builderNextPage.getItemStack();

        worldSettings.setItem(53, itemStackNextPage);

        //open gui
        p.openInventory(worldSettings);
    }
}
