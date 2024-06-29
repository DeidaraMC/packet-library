package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.Color;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import com.deidaramc.packetlibrary.util.RGBUtil;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * For firework star items
 */
public record FireworkExplosionComponent(@NotNull FireworkShape shape, @NotNull List<RGBLike> colors,
                                         @NotNull List<RGBLike> fadeColors, boolean hasTrail, boolean hasTwinkle) implements NetworkWriter {
    public FireworkExplosionComponent {
        colors = Collections.unmodifiableList(colors);
        fadeColors = Collections.unmodifiableList(fadeColors);
    }

    public FireworkExplosionComponent(@NotNull FireworkShape shape, @NotNull RGBLike color, @NotNull RGBLike fadeColor,
                                      boolean hasTrail, boolean hasTwinkle) {
        this(shape, List.of(color), List.of(fadeColor), hasTrail, hasTwinkle);
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        shape.write(buffer);
        ProtocolUtil.writeVarInt(colors.size(), buffer);
        for (RGBLike color : colors) buffer.putInt(RGBUtil.toInt(color));
        ProtocolUtil.writeVarInt(fadeColors.size(), buffer);
        for (RGBLike color : fadeColors) buffer.putInt(RGBUtil.toInt(color));
        ProtocolUtil.writeBoolean(hasTrail, buffer);
        ProtocolUtil.writeBoolean(hasTwinkle, buffer);
    }

    public static @NotNull FireworkExplosionComponent fromByteBuffer(@NotNull ByteBuffer buffer) {
        FireworkShape shape = FireworkShape.fromByteBuffer(buffer);
        int colorCount = ProtocolUtil.readVarInt(buffer);
        List<RGBLike> colors = new ArrayList<>(colorCount);
        for (int i = 0; i < colorCount; i++) colors.add(new Color(buffer.getInt()));
        int fadeCount = ProtocolUtil.readVarInt(buffer);
        List<RGBLike> fadeColors = new ArrayList<>(fadeCount);
        for (int i = 0; i < fadeCount; i++) fadeColors.add(new Color(buffer.getInt()));
        return new FireworkExplosionComponent(shape, colors, fadeColors, ProtocolUtil.readBoolean(buffer), ProtocolUtil.readBoolean(buffer));
    }
}
