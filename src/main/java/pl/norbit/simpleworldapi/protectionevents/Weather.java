package pl.norbit.simpleworldapi.protectionevents;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.norbit.simpleworldapi.WorldConfig;
import pl.norbit.simpleworldapi.WorldConfigManager;

import java.util.HashMap;

public class Weather implements Listener {

    @EventHandler
    public void onEvent(WeatherChangeEvent e){
        World world = e.getWorld();
        String worldName = world.getName();
        HashMap<String, WorldConfig> configHashMap = WorldConfigManager.getConfigHashMap();

        if(configHashMap.containsKey(worldName)){
            WorldConfig config = configHashMap.get(worldName);

            if(!config.isWeather()){
                e.setCancelled(e.toWeatherState());
            }
        }
    }
}
