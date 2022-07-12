package pl.norbit.simpleworldapi.worldbuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldType;
import pl.norbit.simpleworldapi.worldbuilder.enums.GameRule;
import pl.norbit.simpleworldapi.worldbuilder.enums.SimpleWorldType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SimpleWorld {
    private String worldName;
    private final List<GameRuleObject> gameRules = new ArrayList<>();
    private WorldType worldType;
    private String generatorSettings;
    @Builder.Default
    private Difficulty difficulty = Difficulty.NORMAL;
    @Builder.Default
    private World.Environment environment = World.Environment.NORMAL;
    @Builder.Default
    private boolean generateStructures = false, loadOnStart = true, pvp = true, weather = false, dayCycle = false,
            spawnEntities = false, explosions = false, generateCenterBlock = true;
    @Builder.Default
    private SimpleWorldType simpleWorldType = SimpleWorldType.VOID;

    public void addGameRule(GameRule gameRule, String option){
        gameRules.add(new GameRuleObject(gameRule.getGameRuleString(), option));
    }
}