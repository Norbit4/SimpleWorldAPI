package pl.norbit.simpleworldapi;

import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.cmd.MainCMD;
import pl.norbit.simpleworldapi.config.PluginConfigManager;
import pl.norbit.simpleworldapi.protectionevents.EventManager;
import pl.norbit.simpleworldapi.file.FileManager;
import pl.norbit.simpleworldapi.tasks.TimeTask;

import java.io.IOException;

public final class SimpleWorldAPI extends JavaPlugin {

    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        enableMessage();

        instance = this;

        PluginConfigManager.load();

        try {
            FileManager.clearTempFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WorldConfigManager.init(this);

        TimeTask.run();
        EventManager.registerEvents();

        try {
            WorldManager.loadConfigWorlds();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getCommand("swapi").setExecutor(new MainCMD());
        getCommand("simpleworldapi").setExecutor(new MainCMD());
    }

    @Override
    public void onDisable() {
        disableMessage();
    }
    private static void enableMessage(){
        System.out.println("");
        System.out.println("-------[SimpleWorldAPI]-------");
        System.out.println("");
        System.out.println("HI :)");
        System.out.println("");
        System.out.println("Wiki: ");
        System.out.println("Plugin created by Norbit4");
        System.out.println("------------------------------");
    }

    private static void disableMessage(){
        System.out.println("");
        System.out.println("-------[SimpleWorldAPI]-------");
        System.out.println("");
        System.out.println("BYE :(");
        System.out.println("");
        System.out.println("------------------------------");
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
