package com.deidaramc.packetlibrary.potion.type;

import com.deidaramc.packetlibrary.entity.type.EntityType;
import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryType;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

record PotionTypeImpl(@NotNull Key key, int id) implements PotionType {
    private static final PacketLibraryRegistry.RegistryResult<PotionType> REGISTRY_RESULT =
            PacketLibraryRegistry.getRegistryData(RegistryType.POTION, PotionTypeImpl::new);

    static @NotNull PotionType getType(String key) {
        PotionType type = REGISTRY_RESULT.entryByKey().get(key);
        if (type == null) throw new NullPointerException();
        return type;
    }

    static @NotNull PotionType getType(int id) {
        return REGISTRY_RESULT.entryById()[id];
    }

    @Override
    public String toString() {
        return key.asString();
    }
}
