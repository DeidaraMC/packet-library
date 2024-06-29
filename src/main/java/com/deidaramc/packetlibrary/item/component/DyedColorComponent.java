package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.Color;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import com.deidaramc.packetlibrary.util.RGBUtil;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public record DyedColorComponent(@NotNull RGBLike color, boolean showInTooltip) implements NetworkWriter {
    public DyedColorComponent(@NotNull RGBLike color) {
        this(color, true);
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        buffer.putInt(RGBUtil.toInt(color));
        ProtocolUtil.writeBoolean(showInTooltip, buffer);
    }

    public static @NotNull DyedColorComponent fromByteBuffer(@NotNull ByteBuffer buffer) {
        return new DyedColorComponent(new Color(buffer.getInt()), ProtocolUtil.readBoolean(buffer));
    }
}
