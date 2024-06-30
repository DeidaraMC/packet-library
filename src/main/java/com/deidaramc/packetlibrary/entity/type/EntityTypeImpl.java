package com.deidaramc.packetlibrary.entity.type;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryType;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public record EntityTypeImpl(@NotNull Key key, int id) implements EntityType {
    private static final PacketLibraryRegistry.RegistryResult<EntityType> REGISTRY_RESULT =
            PacketLibraryRegistry.getRegistryData(RegistryType.ENTITY_TYPE, EntityTypeImpl::new);

    static @NotNull EntityType getType(String key) {
        EntityType type = REGISTRY_RESULT.entryByKey().get(key);
        if (type == null) throw new NullPointerException();
        return type;
    }

    static @NotNull EntityType getType(int id) {
        return REGISTRY_RESULT.entryById()[id];
    }

    @Override
    public String toString() {
        return key.asString();
    }
}
