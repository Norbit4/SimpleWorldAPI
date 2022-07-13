package pl.norbit.simpleworldapi.worldconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WorldConfig {

    private boolean pvp, loadOnStart, spawnEntities, explosionsBreak, weather, time, template;
    private String difficulty;
}
