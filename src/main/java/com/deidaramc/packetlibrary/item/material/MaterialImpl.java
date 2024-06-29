package com.deidaramc.packetlibrary.item.material;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

record MaterialImpl(@NotNull Key key, int id) implements Material {
    @Override
    public String toString() {
        return key.asString();
    }
}
