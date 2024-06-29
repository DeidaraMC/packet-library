package com.deidaramc.packetlibrary.registry;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public interface RegistryEntry {
    int id();
    @NotNull Key key();
}
