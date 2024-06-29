package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public enum FireworkShape implements NetworkWriter {
    SMALL_BALL,
    LARGE_BALL,
    STAR,
    CREEPER,
    BURST;

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(ordinal(), buffer);
    }

    public static @NotNull FireworkShape fromByteBuffer(@NotNull ByteBuffer buffer) {
        return FireworkShape.values()[ProtocolUtil.readVarInt(buffer)];
    }
}
