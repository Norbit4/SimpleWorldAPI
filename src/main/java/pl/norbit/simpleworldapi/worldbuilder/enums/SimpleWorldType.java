package pl.norbit.simpleworldapi.worldbuilder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.WorldType;

@Getter
@AllArgsConstructor
public enum SimpleWorldType {
    VOID("2;0;1", WorldType.FLAT);

    private final String generatorSettings;
    private final WorldType worldType;
}
