package pl.norbit.simpleworldapi.events;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.norbit.simpleworldapi.worldconfig.Config;
import pl.norbit.simpleworldapi.worldconfig.ConfigManager;

import java.util.HashMap;

public class Weather implements Listener {

    @EventHandler
    public void onEvent(WeatherChangeEvent e){
        World world = e.getWorld();
        String worldName = world.getName();
        HashMap<String, Config> configHashMap = ConfigManager.getConfigHashMap();

        if(configHashMap.containsKey(worldName)){
            Config config = configHashMap.get(worldName);

            if(!config.isWeather()){
                e.setCancelled(e.toWeatherState());
            }
        }
    }
}
