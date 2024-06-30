package com.deidaramc.packetlibrary.potion.effect;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryType;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

record PotionEffectImpl(@NotNull Key key, int id) implements PotionEffect {
    private static final PacketLibraryRegistry.RegistryResult<PotionEffect> REGISTRY_RESULT =
            PacketLibraryRegistry.getRegistryData(RegistryType.MOB_EFFECT, PotionEffectImpl::new);

    static @NotNull PotionEffect getEffect(String key) {
        PotionEffect type = REGISTRY_RESULT.entryByKey().get(key);
        if (type == null) throw new NullPointerException();
        return type;
    }

    static @NotNull PotionEffect getEffect(int id) {
        return REGISTRY_RESULT.entryById()[id];
    }

    @Override
    public String toString() {
        return key.asString();
    }
}
