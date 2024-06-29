package com.deidaramc.packetlibrary.potion.type;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryEntry;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

record PotionTypeImpl(@NotNull Key key, int id) implements PotionType {
    @Override
    public String toString() {
        return key.asString();
    }

    private static final Map<String, RegistryEntry> DATA = PacketLibraryRegistry.getPotionRegistryEntries();
    private static final PotionType[] TYPE_BY_ID = new PotionType[DATA.size()];
    static @NotNull PotionType getType(String key) {
        RegistryEntry data = DATA.get(key);
        PotionType type = new PotionTypeImpl(data.key(), data.id());
        TYPE_BY_ID[data.id()] = type;
        return type;
    }

    static @NotNull PotionType getType(int id) {
        return TYPE_BY_ID[id];
    }
}
