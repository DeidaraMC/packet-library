package com.deidaramc.packetlibrary.protocol;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public interface NetworkWriter {
    void write(@NotNull ByteBuffer buffer);
}
