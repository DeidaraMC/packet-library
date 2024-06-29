package com.deidaramc.packetlibrary.potion.effect;

import com.deidaramc.packetlibrary.registry.RegistryEntry;
import org.jetbrains.annotations.NotNull;

public sealed interface PotionEffect extends PotionEffects, RegistryEntry permits PotionEffectImpl {
    static @NotNull PotionEffect fromId(int id) {
        return PotionEffectImpl.getEffect(id);
    }
}
