package com.deidaramc.packetlibrary.potion;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

record PotionTypeImpl(@NotNull Key key, int id) implements PotionType {
    @Override
    public String toString() {
        return key.asString();
    }

    private static final Map<String, PacketLibraryRegistry.RegistryData> DATA = PacketLibraryRegistry.getMobEffectRegistryData();
    private static final PotionType[] EFFECT_BY_ID = new PotionType[DATA.size()];
    static @NotNull PotionType getType(String key) {
        PacketLibraryRegistry.RegistryData data = DATA.get(key);
        PotionType effect = new PotionTypeImpl(data.key(), data.id());
        EFFECT_BY_ID[data.id()] = effect;
        return effect;
    }

    static @NotNull PotionType getType(int id) {
        return EFFECT_BY_ID[id];
    }
}
