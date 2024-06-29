package com.deidaramc.packetlibrary.item;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.function.BiConsumer;
import java.util.function.Function;

record ComponentTypeImpl<T>(@NotNull Key key, int id, Function<ByteBuffer, T> reader,
                            BiConsumer<T, ByteBuffer> writer)
        implements ComponentType<T> {
    @Override
    public String toString() {
        return key.asString();
    }
}
