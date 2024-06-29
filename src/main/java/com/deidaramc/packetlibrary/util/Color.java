package com.deidaramc.packetlibrary.util;

import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.Range;

public record Color(int red, int green, int blue) implements RGBLike {
    public Color(int rgb) {
        this(rgb >> 16 & 255, rgb >> 8 & 255, rgb & 255);
    }

    @Override
    public @Range(from = 0L, to = 255L) int red() {
        return red;
    }

    @Override
    public @Range(from = 0L, to = 255L) int green() {
        return green;
    }

    @Override
    public @Range(from = 0L, to = 255L) int blue() {
        return blue;
    }
}
