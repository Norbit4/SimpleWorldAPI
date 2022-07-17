package pl.norbit.simpleworldapi.worldconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WorldConfig {

    private boolean
            pvp,
            loadOnStart,
            spawnMonsters,
            spawnAnimals,
            explosionsBreak,
            weather,
            time,
            fireDamage,
            fireSpread,
            fallDamage,
            breakBlocks,
            placeBlocks,
            drownDamage,
            template;
    private String difficulty;
}
