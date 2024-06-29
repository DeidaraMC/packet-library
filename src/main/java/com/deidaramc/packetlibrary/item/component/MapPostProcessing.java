package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public enum MapPostProcessing implements NetworkWriter {
    LOCK,
    SCALE;

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(ordinal(), buffer);
    }

    public static @NotNull MapPostProcessing fromByteBuffer(@NotNull ByteBuffer buffer) {
        return MapPostProcessing.values()[ProtocolUtil.readVarInt(buffer)];
    }
}
