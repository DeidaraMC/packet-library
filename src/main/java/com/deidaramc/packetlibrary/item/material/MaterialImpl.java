package com.deidaramc.packetlibrary.item.material;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryType;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

record MaterialImpl(@NotNull Key key, int id) implements Material {
    private static final PacketLibraryRegistry.RegistryResult<Material> REGISTRY_RESULT =
            PacketLibraryRegistry.getRegistryData(RegistryType.ITEM, MaterialImpl::new);

    static @NotNull Material getMaterial(String key) {
        Material type = REGISTRY_RESULT.entryByKey().get(key);
        if (type == null) throw new NullPointerException();
        return type;
    }

    static @NotNull Material getMaterial(int id) {
        return REGISTRY_RESULT.entryById()[id];
    }

    @Override
    public String toString() {
        return key.asString();
    }
}
