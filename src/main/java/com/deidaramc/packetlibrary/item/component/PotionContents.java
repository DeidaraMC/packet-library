package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.potion.effect.PotionEffect;
import com.deidaramc.packetlibrary.potion.type.PotionType;
import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.Color;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import com.deidaramc.packetlibrary.util.RGBUtil;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record PotionContents(@NotNull PotionType type, @Nullable RGBLike customColor, @NotNull List<PotionDetails> potionDetails) implements NetworkWriter {
    public PotionContents {
        potionDetails = Collections.unmodifiableList(potionDetails);
    }

    public PotionContents(@NotNull PotionType type, @Nullable RGBLike customColor) {
        this(type, customColor, Collections.emptyList());
    }

    public PotionContents(@NotNull PotionType type) {
        this(type, null);
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(type.id(), buffer);
        boolean hasCustomColor = customColor != null;
        ProtocolUtil.writeBoolean(hasCustomColor, buffer);
        if (hasCustomColor) buffer.putInt(RGBUtil.toInt(customColor));
        else buffer.putInt(0);
        ProtocolUtil.writeVarInt(potionDetails.size(), buffer);
        for (PotionDetails details : potionDetails) details.write(buffer);
    }

    public static @NotNull PotionContents fromByteBuffer(@NotNull ByteBuffer buffer) {
        int typeId = ProtocolUtil.readVarInt(buffer);
        boolean hasCustomColor = ProtocolUtil.readBoolean(buffer);
        int customColor = buffer.getInt();
        int potionDetailCount = ProtocolUtil.readVarInt(buffer);
        if (potionDetailCount <= 0) {
            return new PotionContents(PotionType.fromId(typeId), hasCustomColor ? new Color(customColor) : null);
        }

        List<PotionDetails> details = new ArrayList<>(potionDetailCount);
        for (int i = 0; i < potionDetailCount; i++) details.add(PotionDetails.fromByteBuffer(buffer));
        return new PotionContents(PotionType.fromId(typeId), hasCustomColor ? new Color(customColor) : null, details);
    }
}
