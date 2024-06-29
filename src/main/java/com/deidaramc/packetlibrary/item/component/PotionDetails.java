package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.potion.PotionType;
import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

// Note: this is not the same as a regular potion
public record PotionDetails(@NotNull PotionType type, int amplifier, int duration,
                            boolean ambient, boolean showParticles, boolean showIcon,
                            @Nullable PotionDetails hiddenEffect) implements NetworkWriter {
    public PotionDetails(@NotNull PotionType type, int amplifier, int duration) {
        this(type, amplifier, duration, false, true, true, null);
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(type.id(), buffer);
        ProtocolUtil.writeVarInt(amplifier, buffer);
        ProtocolUtil.writeVarInt(duration, buffer);
        ProtocolUtil.writeBoolean(ambient, buffer);
        ProtocolUtil.writeBoolean(showParticles, buffer);
        ProtocolUtil.writeBoolean(showIcon, buffer);
        boolean hasHiddenEffect = hiddenEffect != null;
        ProtocolUtil.writeBoolean(hasHiddenEffect, buffer);
        if (hasHiddenEffect) hiddenEffect.write(buffer);
    }

    public static @NotNull PotionDetails fromByteBuffer(@NotNull ByteBuffer buffer) {
        return new PotionDetails(
                PotionType.fromId(ProtocolUtil.readVarInt(buffer)),
                ProtocolUtil.readVarInt(buffer),
                ProtocolUtil.readVarInt(buffer),
                ProtocolUtil.readBoolean(buffer),
                ProtocolUtil.readBoolean(buffer),
                ProtocolUtil.readBoolean(buffer),
                ProtocolUtil.readBoolean(buffer) ? fromByteBuffer(buffer) : null
        );
    }
}
