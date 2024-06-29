package com.deidaramc.packetlibrary.item.material;

import com.deidaramc.packetlibrary.registry.RegistryEntry;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Material extends Materials, RegistryEntry {
    static @NotNull Material fromId(int id) {
        return MaterialRegistry.getMaterial(id);
    }
}
