package com.deidaramc.packetlibrary.item;

import com.deidaramc.packetlibrary.registry.RegistryEntry;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public sealed interface ComponentType<T> extends RegistryEntry permits ComponentTypeImpl {
    static @NotNull ComponentType<?> fromId(int id) {
        return ItemComponent.getType(id);
    }
}
