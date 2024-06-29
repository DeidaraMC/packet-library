package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public record ProbablePotionDetails(@NotNull PotionDetails potionDetails, float probability) implements NetworkWriter {
    @Override
    public void write(@NotNull ByteBuffer buffer) {
        potionDetails.write(buffer);
        buffer.putFloat(probability);
    }

    public static @NotNull ProbablePotionDetails fromByteBuffer(@NotNull ByteBuffer buffer) {
        return new ProbablePotionDetails(PotionDetails.fromByteBuffer(buffer), buffer.getFloat());
    }
}
