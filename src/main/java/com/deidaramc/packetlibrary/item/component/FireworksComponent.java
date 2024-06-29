package com.deidaramc.packetlibrary.item.component;

import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import com.deidaramc.packetlibrary.util.RGBUtil;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * For firework items
 */
public record FireworksComponent(int duration, List<FireworkExplosionComponent> fireworkExplosions) implements NetworkWriter {
    public FireworksComponent {
        fireworkExplosions = Collections.unmodifiableList(fireworkExplosions);
    }

    public FireworksComponent(int duration, FireworkExplosionComponent fireworkExplosion) {
        this(duration, List.of(fireworkExplosion));
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(duration, buffer);
        ProtocolUtil.writeVarInt(fireworkExplosions.size(), buffer);
        for (FireworkExplosionComponent explosion : fireworkExplosions) {
            explosion.write(buffer);
        }
    }

    public static @NotNull FireworksComponent fromByteBuffer(@NotNull ByteBuffer buffer) {
        int duration = ProtocolUtil.readVarInt(buffer);
        int explosionCount = ProtocolUtil.readVarInt(buffer);
        List<FireworkExplosionComponent> explosions = new ArrayList<>(explosionCount);
        for (int i = 0; i < explosionCount; i++) {
            explosions.add(FireworkExplosionComponent.fromByteBuffer(buffer));
        }
        return new FireworksComponent(duration, explosions);
    }
}
