package com.deidaramc.packetlibrary.potion.type;

import com.deidaramc.packetlibrary.registry.RegistryEntry;
import org.jetbrains.annotations.NotNull;

public sealed interface PotionType extends PotionTypes, RegistryEntry permits PotionTypeImpl {
    static @NotNull PotionType fromId(int id) {
        return PotionTypeImpl.getType(id);
    }
}
