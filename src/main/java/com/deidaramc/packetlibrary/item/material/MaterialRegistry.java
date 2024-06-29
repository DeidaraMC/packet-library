package com.deidaramc.packetlibrary.item.material;

import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class MaterialRegistry {
    private static final Map<String, MaterialImpl> MATERIAL_BY_NAME = new HashMap<>();
    private static final MaterialImpl[] MATERIAL_BY_ID;

    static {
        Map<String, PacketLibraryRegistry.RegistryData> registryData = PacketLibraryRegistry.getMaterialRegistryData();
        MATERIAL_BY_ID = new MaterialImpl[registryData.size()];
        registryData.forEach((key, data) -> {
            MaterialImpl material = new MaterialImpl(data.key(), data.id());
            MATERIAL_BY_NAME.put(key, material);
            MATERIAL_BY_ID[data.id()] = material;
        });
    }

    static @NotNull Material getMaterial(@NotNull String key) {
        Material material = MATERIAL_BY_NAME.get(key);
        if (material == null) throw new NullPointerException("Material not found " + key);
        return material;
    }

    static @NotNull Material getMaterial(int id) {
        return MATERIAL_BY_ID[id];
    }
}
