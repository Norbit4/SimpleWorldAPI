package pl.norbit.simpleworldapi.worldcreator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;
import pl.norbit.simpleworldapi.worldbuilder.enums.SimpleWorldType;
import pl.norbit.simpleworldapi.worldconfig.WorldConfigManager;

import java.io.IOException;

public class Creator {

    public static World normal(SimpleWorld simpleWorld){
        World world = generate(simpleWorld);
        setWorldSettings(world, simpleWorld);

        return world;
    }
    private static void setWorldSettings(World world, SimpleWorld simpleWorld){
        world.setDifficulty(simpleWorld.getDifficulty());

        world.setPVP(simpleWorld.isPvp());

        simpleWorld.getGameRules().forEach(gameRule -> {
            world.setGameRuleValue(gameRule.getGameRule(), gameRule.getOption());
        });


        try {
            WorldConfigManager.createWorldConfig(simpleWorld, simpleWorld.isTemporaryWorld());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(simpleWorld.isGenerateCenterBlock()) {
            new Location(world, 0, 60, 0).getBlock().setType(Material.BEDROCK);
        }
    }

    private static World generate(SimpleWorld simpleWorld){
        boolean useSimpleType = true;

        String worldName = simpleWorld.getWorldName();

        if(simpleWorld.isTemporaryWorld()){
            worldName = "/temp/" + simpleWorld.getWorldName();

        }

        WorldCreator worldCreator = new WorldCreator(worldName)
                .environment(simpleWorld.getEnvironment())
                .generateStructures(simpleWorld.isGenerateStructures());

        //world gen settings
        if (simpleWorld.getWorldType() != null) {
            worldCreator.type(simpleWorld.getWorldType());
            useSimpleType = false;
        }

        if (simpleWorld.getGeneratorSettings() != null) {
            useSimpleType = false;
            worldCreator.generatorSettings(simpleWorld.getGeneratorSettings());
        }

        if (useSimpleType) {
            SimpleWorldType simpleWorldType = simpleWorld.getSimpleWorldType();

            worldCreator.type(simpleWorldType.getWorldType());
            worldCreator.generatorSettings(simpleWorldType.getGeneratorSettings());
        }

        return worldCreator.createWorld();
    }
}
