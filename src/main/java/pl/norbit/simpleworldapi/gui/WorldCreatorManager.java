package pl.norbit.simpleworldapi.gui;


import lombok.Getter;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.norbit.simpleworldapi.WorldManager;
import pl.norbit.simpleworldapi.config.PluginConfig;
import pl.norbit.simpleworldapi.utils.ChatUtil;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;
import pl.norbit.simpleworldapi.worldbuilder.enums.SimpleWorldType;

import java.util.HashMap;
import java.util.UUID;

public class WorldCreatorManager {
    @Getter
    private static final HashMap<UUID, SimpleWorld> creatorMap;

    static {
        creatorMap = new HashMap<>();
    }

    protected static SimpleWorld newCreator(Player p, String worldName){
        SimpleWorld simpleWorld = SimpleWorld
                .builder()
                .worldName(worldName)
                .build();

        creatorMap.put(p.getUniqueId(), simpleWorld);
        return simpleWorld;
    }

    protected static void clearCreator(Player p){
        creatorMap.remove(p.getUniqueId());
    }

    protected static void creator(Player p, ItemStack itemStack){
        boolean reload = true;

        SimpleWorld simpleWorld = creatorMap.get(p.getUniqueId());

        switch (itemStack.getType()) {
            case REDSTONE_COMPARATOR:
                GuiManager.open(p, GuiType.SETTINGS_PAGE_ONE, "", simpleWorld);
                reload = false;
                break;
            case STAINED_CLAY:
                short durability = itemStack.getDurability();
                if(durability == 5){
                    simpleWorld.setDifficulty(Difficulty.NORMAL);
                } else if(durability == 4){
                    simpleWorld.setDifficulty(Difficulty.HARD);
                } else if(durability == 14){
                    simpleWorld.setDifficulty(Difficulty.PEACEFUL);
                } else if(durability == 3){
                    simpleWorld.setDifficulty(Difficulty.EASY);
                }
                break;
            case FEATHER:
                simpleWorld.setSimpleWorldType(SimpleWorldType.NORMAL);
                break;
            case GRASS:
                simpleWorld.setSimpleWorldType(SimpleWorldType.FLAT);
                break;
            case DIRT:
                simpleWorld.setSimpleWorldType(SimpleWorldType.LARGE_BIOMES);
                break;
            case STONE:
                simpleWorld.setSimpleWorldType(SimpleWorldType.NETHER);
                break;
            case NETHERRACK:
                simpleWorld.setSimpleWorldType(SimpleWorldType.END);
                break;
            case ENDER_STONE:
                simpleWorld.setSimpleWorldType(SimpleWorldType.VOID);
                break;
            case WOOL:
                p.closeInventory();
                if(itemStack.getDurability() == 5) {
                    createWorld(p, simpleWorld);
                }
                p.closeInventory();
                WorldCreatorManager.clearCreator(p);
                reload = false;
                break;
        }

        if(reload){
            GuiManager.open(p, GuiType.CREATOR_MENU, "", simpleWorld);
        }
    }
    private static void createWorld(Player p, SimpleWorld simpleWorld){
        long t1 = System.currentTimeMillis();

        p.sendMessage(ChatUtil.format("&7World is creating..."));
        World world = WorldManager.createWorld(simpleWorld);

        long time = System.currentTimeMillis() - t1;
        String message = PluginConfig.WORLD_CREATE_MESSAGE
                .replace("{WORLD}", world.getName())
                .replace("{TIME}",String.valueOf(time));

        p.sendMessage(ChatUtil.format(message));
    }

    protected static void settingsPage1(Player p, ItemStack itemStack, Inventory inv){
        int index;
        boolean reload = true;

        SimpleWorld simpleWorld = creatorMap.get(p.getUniqueId());

        switch (itemStack.getType()) {
            case IRON_SWORD:
                index =  9;
                simpleWorld.setPvp(setNewStatus(inv, p, index));
                break;
            case COMPASS:
                index = 2 + 9;
                simpleWorld.setTime(setNewStatus(inv, p, index));
                break;
            case DOUBLE_PLANT:
                index = 4 + 9;
                simpleWorld.setWeather(setNewStatus(inv, p, index));
                break;
            case LAVA_BUCKET:
                index = 6 + 9;
                simpleWorld.setFireDamage(setNewStatus(inv, p, index));
                break;
            case FLINT_AND_STEEL:
                index = 8 + 9;
                simpleWorld.setFireSpread(setNewStatus(inv, p, index));
                break;
            case TNT:
                index = 27 + 9;
                simpleWorld.setExplosionsBreak(setNewStatus(inv, p, index));
                break;
            case REDSTONE_TORCH_ON:
                index = 29 + 9;
                simpleWorld.setLoadOnStart(setNewStatus(inv, p, index));
                break;
            case ENDER_PORTAL_FRAME:
                index = 31 + 9;
                simpleWorld.setGenerateStructures(setNewStatus(inv, p, index));
                break;
            case MONSTER_EGG:
                if(itemStack.getDurability() == 54) {
                    index = 33 + 9;
                    simpleWorld.setSpawnMonsters(setNewStatus(inv, p, index));
                }else{
                    index = 35 + 9;
                    simpleWorld.setSpawnAnimals(setNewStatus(inv, p, index));
                }
                break;
            case WOOL:
                GuiManager.open(p, GuiType.CREATOR_MENU, "", simpleWorld);
                reload = false;
                break;
            case ARROW:
                GuiManager.open(p, GuiType.SETTINGS_PAGE_TWO, "", simpleWorld);
                reload = false;
                break;
        }

        if(reload){
            GuiManager.open(p, GuiType.SETTINGS_PAGE_ONE, "", simpleWorld);
        }
    }

    protected static void settingsPage2(Player p, ItemStack itemStack, Inventory inv){
        int index;
        boolean reload = true;

        SimpleWorld simpleWorld = creatorMap.get(p.getUniqueId());

        switch (itemStack.getType()) {
            case FEATHER:
                index = 1 + 9;
                simpleWorld.setFallDamage(setNewStatus(inv, p, index));
                break;
            case BEDROCK:
                index = 3 + 9;
                simpleWorld.setGenerateCenterBlock(setNewStatus(inv, p, index));
                break;
            case REDSTONE:
                index = 5 + 9;
                simpleWorld.setTemporaryWorld(setNewStatus(inv, p, index));
                break;
            case ITEM_FRAME:
                index = 7 + 9;
                simpleWorld.setTemplateWorld(setNewStatus(inv, p, index));
                break;
            case DIAMOND_PICKAXE:
                index = 28 + 9;
                simpleWorld.setBreakBlocks(setNewStatus(inv, p, index));
                break;
            case BRICK:
                index = 30 + 9;
                simpleWorld.setPlaceBlocks(setNewStatus(inv, p, index));
                break;
            case GRAVEL:
                index = 32 + 9;
                simpleWorld.setGravity(setNewStatus(inv, p, index));
                break;
            case WATER_BUCKET:
                index = 34 + 9;
                simpleWorld.setDrownDamage(setNewStatus(inv, p, index));
                break;
            case WOOL:
                GuiManager.open(p, GuiType.CREATOR_MENU, "", simpleWorld);
                reload = false;
                break;
            case ARROW:
                GuiManager.open(p, GuiType.SETTINGS_PAGE_ONE, "", simpleWorld);
                reload = false;
                break;
        }

        if(reload){
            GuiManager.open(p, GuiType.SETTINGS_PAGE_TWO, "", simpleWorld);
        }
    }

    private static boolean setNewStatus(Inventory inv,Player p, int index){
        ItemStack item = inv.getItem(index);

        boolean newStatus = item.getDurability() != 10;

        if(newStatus){
            item.setDurability((byte) 10);
        }else{
            item.setDurability((byte) 8);
        }

        return newStatus;
    }
}
