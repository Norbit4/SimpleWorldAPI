package pl.norbit.simpleworldapi.worldconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WorldConfig {

    private boolean pvp, loadOnStart, spawnEntities, explosions, weather, dayCycle;
    private String difficulty;
}
