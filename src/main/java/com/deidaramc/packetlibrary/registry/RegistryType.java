package com.deidaramc.packetlibrary.registry;

import com.deidaramc.packetlibrary.entity.type.EntityType;
import com.deidaramc.packetlibrary.item.ComponentType;
import com.deidaramc.packetlibrary.item.material.Material;
import com.deidaramc.packetlibrary.potion.effect.PotionEffect;
import com.deidaramc.packetlibrary.potion.type.PotionType;
import org.jetbrains.annotations.NotNull;

public enum RegistryType {
    DATA_COMPONENT_TYPE("minecraft:data_component_type", ComponentType.class),
    ENTITY_TYPE("minecraft:entity_type", EntityType.class),
    ITEM("minecraft:item", Material.class),
    MOB_EFFECT("minecraft:mob_effect", PotionEffect.class),
    POTION("minecraft:potion", PotionType.class);

    private final String namespace;
    private final Class<?> registryClass;

    RegistryType(@NotNull String namespace, @NotNull Class<?> registryClass) {
        this.namespace = namespace;
        this.registryClass = registryClass;
    }

    @NotNull String namespace() {
        return namespace;
    }

    @NotNull Class<?> registryClass() {
        return registryClass;
    }
}
