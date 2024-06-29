package com.deidaramc.packetlibrary.item;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public sealed interface ComponentType<T> permits ComponentTypeImpl {
    @NotNull Key key();
    int id();

    static @NotNull ComponentType<?> fromId(int id) {
        return ItemComponent.getType(id);
    }
}
