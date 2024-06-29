package com.deidaramc.packetlibrary.item.material;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Material extends Materials {
    @NotNull Key key();
    int id();

    static @NotNull Material fromId(int id) {
        return MaterialRegistry.getMaterial(id);
    }
}
