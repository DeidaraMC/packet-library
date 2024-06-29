package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record FoodComponent(int nutrition, boolean saturation, boolean canAlwaysEat, float secondsToEat, List<ProbablePotionDetails> potionDetails) implements NetworkWriter {
    public FoodComponent {
        potionDetails = Collections.unmodifiableList(potionDetails);
    }

    public FoodComponent(int nutrition, boolean saturation, boolean canAlwaysEat, float secondsToEat) {
        this(nutrition, saturation, canAlwaysEat, secondsToEat, Collections.emptyList());
    }

    public FoodComponent(int nutrition, boolean saturation) {
        this(nutrition, saturation, false, 1, Collections.emptyList());
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(nutrition, buffer);
        ProtocolUtil.writeBoolean(saturation, buffer);
        ProtocolUtil.writeBoolean(canAlwaysEat, buffer);
        buffer.putFloat(secondsToEat);
        ProtocolUtil.writeVarInt(potionDetails.size(), buffer);
        for (ProbablePotionDetails details : potionDetails) details.write(buffer);
    }

    public static @NotNull FoodComponent fromByteBuffer(@NotNull ByteBuffer buffer) {
        int nutrition = ProtocolUtil.readVarInt(buffer);
        boolean saturation = ProtocolUtil.readBoolean(buffer);
        boolean canAlwaysEat = ProtocolUtil.readBoolean(buffer);
        float secondsToEat = buffer.getFloat();
        int potionDetailCount = ProtocolUtil.readVarInt(buffer);
        if (potionDetailCount <= 0) {
            return new FoodComponent(nutrition, saturation, canAlwaysEat, secondsToEat);
        }

        List<ProbablePotionDetails> details = new ArrayList<>(potionDetailCount);
        for (int i = 0; i < potionDetailCount; i++) details.add(ProbablePotionDetails.fromByteBuffer(buffer));
        return new FoodComponent(nutrition, saturation, canAlwaysEat, secondsToEat, details);
    }
}
