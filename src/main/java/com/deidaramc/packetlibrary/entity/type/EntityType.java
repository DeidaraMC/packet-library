package com.deidaramc.packetlibrary.entity.type;

import com.deidaramc.packetlibrary.registry.RegistryEntry;
import org.jetbrains.annotations.NotNull;

public sealed interface EntityType extends EntityTypes, RegistryEntry permits EntityTypeImpl {
    static @NotNull EntityType fromId(int id) {
        return EntityTypeImpl.getType(id);
    }

    static @NotNull EntityType fromKey(@NotNull String key) {
        return EntityTypeImpl.getType(key);
    }
}
