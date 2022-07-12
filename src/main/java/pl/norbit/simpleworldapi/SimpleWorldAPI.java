package pl.norbit.simpleworldapi;

import org.bukkit.plugin.java.JavaPlugin;
import pl.norbit.simpleworldapi.cmd.MainCMD;
import pl.norbit.simpleworldapi.config.PluginConfigManager;
import pl.norbit.simpleworldapi.events.EventManager;
import pl.norbit.simpleworldapi.file.FileManager;
import pl.norbit.simpleworldapi.tasks.TimeTask;
import pl.norbit.simpleworldapi.worldconfig.WorldConfigManager;

import java.io.IOException;

public final class SimpleWorldAPI extends JavaPlugin {

    private static JavaPlugin instance;

    @Override
    public void onEnable() {

        PluginConfigManager.load();

        try {
            FileManager.clearTempFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        instance = this;
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
        // Plugin shutdown logic
    }
    public static JavaPlugin getInstance() {
        return instance;
    }
}
