package com.deidaramc.packetlibrary.item.material;

import com.deidaramc.packetlibrary.registry.RegistryEntry;
import org.jetbrains.annotations.NotNull;

public interface Material extends Materials, RegistryEntry {
    static @NotNull Material fromId(int id) {
        return MaterialImpl.getMaterial(id);
    }

    static @NotNull Material fromKey(@NotNull String key) {
        return MaterialImpl.getMaterial(key);
    }
}
