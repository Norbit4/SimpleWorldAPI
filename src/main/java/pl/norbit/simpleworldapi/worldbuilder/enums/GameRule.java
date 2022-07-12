package pl.norbit.simpleworldapi.worldbuilder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameRule {
    ANNOUNCE_ADVANCEMENTS("announceAdvancements"),
    COMMAND_BLOCKS_ENABLED("commandBlocksEnabled"),
    DO_DAY_LIGHT_CYCLE("doDaylightCycle"),
    DO_FIRE_TICK("doFireTick"),
    DO_MOB_LOOT("doMobLoot");


    private final String gameRuleString;
}
