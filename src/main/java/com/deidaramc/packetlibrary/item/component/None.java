package com.deidaramc.packetlibrary.item.component;

import org.jetbrains.annotations.NotNull;

public final class None {
    private static final None INSTANCE = new None();

    private None() { }

    public static @NotNull None getInstance() {
        return INSTANCE;
    }
}
