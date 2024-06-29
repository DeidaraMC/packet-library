package com.deidaramc.packetlibrary.util;

import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;

public final class RGBUtil {
    public static int toInt(@NotNull RGBLike color) {
        int rgb = color.red();
        rgb = (rgb << 8) + color.green();
        return (rgb << 8) + color.blue();
    }
}
