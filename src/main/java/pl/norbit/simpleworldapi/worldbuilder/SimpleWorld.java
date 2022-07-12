package pl.norbit.simpleworldapi.worldbuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldType;
import pl.norbit.simpleworldapi.config.PluginConfig;
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
    private Difficulty difficulty = Difficulty.valueOf(PluginConfig.DIFFICULTY.toUpperCase());
    @Builder.Default
    private World.Environment environment = World.Environment.NORMAL;
    @Builder.Default
    private boolean
            generateStructures = PluginConfig.GENERATE_STRUCTURES,
            loadOnStart = true,
            pvp = PluginConfig.PVP,
            weather = PluginConfig.WEATHER,
            time = PluginConfig.TIME,
            spawnEntities = PluginConfig.SPAWN_ENTITIES,
            explosionsBreak = PluginConfig.EXPLOSION_BREAK,
            generateCenterBlock = PluginConfig.CENTER_BLOCK;
    @Builder.Default
    private SimpleWorldType simpleWorldType = SimpleWorldType.valueOf(PluginConfig.SIMPLE_WORLD_TYPE.toUpperCase());

    public void addGameRule(GameRule gameRule, String option){
        gameRules.add(new GameRuleObject(gameRule.getGameRuleString(), option));
    }
}