package com.deidaramc.packetlibrary.item;

import com.deidaramc.packetlibrary.item.component.None;
import com.deidaramc.packetlibrary.item.material.Material;
import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public sealed interface ItemStack extends NetworkWriter permits ItemStackImpl {
    ItemStack AIR = of(Material.AIR, 0);

    @NotNull Material material();
    int amount();

    <T> @NotNull ItemStack with(ComponentType<T> component, T value);
    <T> @NotNull ItemStack without(@NotNull ComponentType<T> type);

    default @NotNull ItemStack with(ComponentType<None> component) {
        return with(component, None.getInstance());
    }

    static @NotNull ItemStack of(@NotNull Material material) {
        return new ItemStackImpl(material, 1, new ItemComponents());
    }

    static @NotNull ItemStack of(@NotNull Material material, int amount) {
        return new ItemStackImpl(material, amount, new ItemComponents());
    }

    static @NotNull ItemStack fromByteBuffer(@NotNull ByteBuffer buffer) {
        return ItemStackImpl.fromByteBuffer(buffer);
    }

    static @NotNull Builder builder(@NotNull Material material, int amount) {
        return new Builder(material).amount(amount);
    }

    static @NotNull Builder builder(@NotNull Material material) {
        return new Builder(material);
    }

    class Builder {
        private final Material material;
        private int amount;
        private final ItemComponents components = new ItemComponents();

        private Builder(Material material) {
            this.material = material;
        }

        private Builder(ItemStack itemStack) {
            this.material = itemStack.material();
            this.amount = itemStack.amount();
        }

        public @NotNull Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public @NotNull Builder with(@NotNull ComponentType<None> type) {
            return with(type, None.getInstance());
        }

        public <T> @NotNull Builder with(@NotNull ComponentType<T> type, T value) {
            components.addComponent(new ItemComponent<>(type, value));
            return this;
        }

        public <T> @NotNull Builder without(@NotNull ComponentType<T> type) {
            components.removeComponent(type);
            return this;
        }

        public @NotNull ItemStack build() {
            return new ItemStackImpl(material, amount, components);
        }
    }
}
