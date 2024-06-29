package com.deidaramc.packetlibrary.potion;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public sealed interface PotionType extends PotionTypes permits PotionTypeImpl {
    @NotNull Key key();
    int id();

    static @NotNull PotionType fromId(int id) {
        return PotionTypeImpl.getType(id);
    }
}
