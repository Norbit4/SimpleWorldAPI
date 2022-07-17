package pl.norbit.simpleworldapi.gui;

import org.bukkit.entity.Player;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;

public class GuiManager {

    public static void open(Player p, GuiType guiType, String worldName, SimpleWorld simpleWorld){

        if(guiType == GuiType.SETTINGS_PAGE_ONE) {
            Settings1Gui.open(p, simpleWorld);
        } else if (guiType == GuiType.SETTINGS_PAGE_TWO) {
            Settings2Gui.open(p, simpleWorld);
        } else if (guiType == GuiType.CREATOR_MENU) {
            if(simpleWorld != null){
                CreatorGui.open(p, simpleWorld);
            }else{
                CreatorGui.open(p, WorldCreatorManager.newCreator(p, worldName));
            }
        }
    }
}

