package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public enum Rarity implements NetworkWriter {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC;

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(ordinal(), buffer);
    }

    public static @NotNull Rarity fromByteBuffer(@NotNull ByteBuffer buffer) {
        return Rarity.values()[ProtocolUtil.readVarInt(buffer)];
    }
}
