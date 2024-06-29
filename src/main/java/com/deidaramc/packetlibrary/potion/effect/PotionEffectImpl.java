package com.deidaramc.packetlibrary.potion.effect;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryEntry;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

record PotionEffectImpl(@NotNull Key key, int id) implements PotionEffect {
    @Override
    public String toString() {
        return key.asString();
    }

    private static final Map<String, RegistryEntry> DATA = PacketLibraryRegistry.getMobEffectRegistryData();
    private static final PotionEffect[] EFFECT_BY_ID = new PotionEffect[DATA.size()];
    static @NotNull PotionEffect getEffect(String key) {
        RegistryEntry data = DATA.get(key);
        PotionEffect effect = new PotionEffectImpl(data.key(), data.id());
        EFFECT_BY_ID[data.id()] = effect;
        return effect;
    }

    static @NotNull PotionEffect getEffect(int id) {
        return EFFECT_BY_ID[id];
    }
}
