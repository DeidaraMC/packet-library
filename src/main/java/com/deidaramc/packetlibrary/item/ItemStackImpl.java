package com.deidaramc.packetlibrary.item;

import com.deidaramc.packetlibrary.item.material.Material;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Collection;

record ItemStackImpl(@NotNull Material material, int amount, @NotNull ItemComponents components) implements ItemStack {
    @Override
    public <T> @NotNull ItemStack with(ComponentType<T> type, T value) {
        ItemComponent<T> component = new ItemComponent<>(type, value);
        ItemComponents copy = components.copy();
        copy.addComponent(component);
        return new ItemStackImpl(material, amount, copy);
    }

    @Override
    public <T> @NotNull ItemStack without(@NotNull ComponentType<T> type) {
        ItemComponents copy = components.copy();
        copy.removeComponent(type);
        return new ItemStackImpl(material, amount, copy);
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(amount, buffer);
        if (amount <= 0) return;
        ProtocolUtil.writeVarInt(material.id(), buffer);

        Collection<ItemComponent<?>> toAdd = components.componentsToAdd();
        Collection<ComponentType<?>> toRemove = components.componentsToRemove();
        ProtocolUtil.writeVarInt(toAdd.size(), buffer);
        ProtocolUtil.writeVarInt(toRemove.size(), buffer);

        for (var component : toAdd) component.write(buffer);
        for (var type : toRemove) ProtocolUtil.writeVarInt(type.id(), buffer);
    }

    static @NotNull ItemStack fromByteBuffer(@NotNull ByteBuffer buffer) {
        int amount = ProtocolUtil.readVarInt(buffer);
        if (amount <= 0) return AIR;
        int materialId = ProtocolUtil.readVarInt(buffer);
        Material material = Material.fromId(materialId);

        int addCount = ProtocolUtil.readVarInt(buffer);
        int removeCount = ProtocolUtil.readVarInt(buffer);

        ItemComponents components = new ItemComponents();
        for (int i = 0; i < addCount; i++) components.addComponent(ItemComponent.fromByteBuffer(buffer));
        for (int i = 0; i < removeCount; i++) components.removeComponent(ComponentType.fromId(ProtocolUtil.readVarInt(buffer)));
        return new ItemStackImpl(material, amount, components);
    }
}
