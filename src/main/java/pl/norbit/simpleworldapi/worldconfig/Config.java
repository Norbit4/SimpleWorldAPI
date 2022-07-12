package pl.norbit.simpleworldapi.worldconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Config {

    private boolean pvp, loadOnStart, spawnEntities, explosions, weather, dayCycle;
    private String difficulty;
}
