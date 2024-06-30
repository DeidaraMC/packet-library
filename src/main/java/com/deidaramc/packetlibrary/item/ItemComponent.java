package com.deidaramc.packetlibrary.item;

import com.deidaramc.packetlibrary.item.component.*;
import com.deidaramc.packetlibrary.registry.PacketLibraryRegistry;
import com.deidaramc.packetlibrary.registry.RegistryEntry;
import com.deidaramc.packetlibrary.registry.RegistryType;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import com.deidaramc.packetlibrary.protocol.NetworkWriter;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ItemComponent<T> implements NetworkWriter {
    // comment = section of unimplemented components todo implement all
    public static final ComponentType<BinaryTag> CUSTOM_DATA;
    public static final ComponentType<Integer> MAX_STACK_SIZE;
    public static final ComponentType<Integer> MAX_DAMAGE;
    public static final ComponentType<Integer> DAMAGE;
    public static final ComponentType<Boolean> UNBREAKABLE;
    public static final ComponentType<Component> CUSTOM_NAME;
    public static final ComponentType<Component> ITEM_NAME;
    public static final ComponentType<List<Component>> LORE;
    public static final ComponentType<Rarity> RARITY;
    //
    public static final ComponentType<Integer> CUSTOM_MODEL_DATA;
    public static final ComponentType<None> HIDE_ADDITIONAL_TOOLTIP;
    public static final ComponentType<None> HIDE_TOOLTIP;
    public static final ComponentType<Integer> REPAIR_COST;
    public static final ComponentType<None> CREATIVE_SLOT_LOCK;
    public static final ComponentType<Integer> ENCHANTMENT_GLINT_OVERRIDE;
    public static final ComponentType<BinaryTag> INTANGIBLE_PROJECTILE;
    public static final ComponentType<FoodComponent> FOOD;
    public static final ComponentType<None> FIRE_RESISTANT;
    //
    public static final ComponentType<DyedColorComponent> DYED_COLOR;
    public static final ComponentType<Integer> MAP_COLOR;
    public static final ComponentType<Integer> MAP_ID;
    public static final ComponentType<BinaryTag> MAP_DECORATIONS;
    public static final ComponentType<MapPostProcessing> MAP_POST_PROCESSING;
    public static final ComponentType<List<ItemStack>> CHARGED_PROJECTILES;
    public static final ComponentType<List<ItemStack>> BUNDLE_CONTENTS;
    public static final ComponentType<PotionContents> POTION_CONTENTS;
    //
    public static final ComponentType<BinaryTag> DEBUG_STICK_STATE;
    public static final ComponentType<BinaryTag> ENTITY_DATA;
    public static final ComponentType<BinaryTag> BUCKET_ENTITY_DATA;
    public static final ComponentType<BinaryTag> BLOCK_ENTITY_DATA;
    //
    public static final ComponentType<Integer> OMINOUS_BOTTLE_AMPLIFIER;
    //
    public static final ComponentType<BinaryTag> RECIPES;
    //
    public static final ComponentType<FireworkExplosionComponent> FIREWORK_EXPLOSION;
    public static final ComponentType<FireworksComponent> FIREWORKS;
    //
    public static final ComponentType<DyedColorComponent> BASE_COLOR;
    //
    public static final ComponentType<BinaryTag> LOCK;
    public static final ComponentType<BinaryTag> CONTAINER_LOOT;

    private final ComponentType<T> type;
    private final T value;

    ItemComponent(ComponentType<T> type, T value) {
        this.type = type;
        this.value = value;
    }

    ComponentType<T> type() {
        return type;
    }

    T value() {
        return value;
    }

    @Override
    public void write(@NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(type.id(), buffer);
        ((ComponentTypeImpl<T>) type).writer().accept(value, buffer);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @SuppressWarnings("unchecked")
    public static @NotNull ItemComponent<?> fromByteBuffer(@NotNull ByteBuffer buffer) {
        ComponentTypeImpl<Object> type = (ComponentTypeImpl<Object>) REGISTRY_RESULT.entryById()[ProtocolUtil.readVarInt(buffer)];
        return new ItemComponent<>(type, type.reader().apply(buffer));
    }

    private static final PacketLibraryRegistry.RegistryResult<ComponentType<?>> REGISTRY_RESULT =
            PacketLibraryRegistry.getRegistryData(RegistryType.DATA_COMPONENT_TYPE, ComponentTypeImpl::new);

    static {
        CUSTOM_DATA = createType("minecraft:custom_data", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        MAX_STACK_SIZE = createType("minecraft:max_stack_size", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        MAX_DAMAGE = createType("minecraft:max_damage", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        DAMAGE = createType("minecraft:damage", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        UNBREAKABLE = createType("minecraft:unbreakable", ProtocolUtil::readBoolean, ProtocolUtil::writeBoolean);
        CUSTOM_NAME = createType("minecraft:custom_name", ProtocolUtil::readAdventureComponent, ProtocolUtil::writeAdventureComponent);
        ITEM_NAME = createType("minecraft:item_name", ProtocolUtil::readAdventureComponent, ProtocolUtil::writeAdventureComponent);
        LORE = createType("minecraft:lore", ProtocolUtil::readAdventureComponentList, ProtocolUtil::writeAdventureComponentList);
        RARITY = createType("minecraft:rarity", Rarity::fromByteBuffer, Rarity::write);
        //
        CUSTOM_MODEL_DATA = createType("minecraft:custom_model_data", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        HIDE_ADDITIONAL_TOOLTIP = createType("minecraft:hide_additional_tooltip", b -> None.getInstance(), (n, b) -> {});
        HIDE_TOOLTIP = createType("minecraft:hide_tooltip", b -> None.getInstance(), (n, b) -> {});
        REPAIR_COST = createType("minecraft:repair_cost", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        CREATIVE_SLOT_LOCK = createType("minecraft:creative_slot_lock", b -> None.getInstance(), (n, b) -> {});
        ENCHANTMENT_GLINT_OVERRIDE = createType("minecraft:enchantment_glint_override", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        INTANGIBLE_PROJECTILE = createType("minecraft:intangible_projectile", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        FOOD = createType("minecraft:food", FoodComponent::fromByteBuffer, FoodComponent::write);
        FIRE_RESISTANT = createType("minecraft:fire_resistant", b -> None.getInstance(), (n, b) -> {});
        //
        DYED_COLOR = createType("minecraft:dyed_color", DyedColorComponent::fromByteBuffer, DyedColorComponent::write);
        MAP_COLOR = createType("minecraft:map_color", ByteBuffer::getInt, (v, b) -> b.putInt(v));
        MAP_ID = createType("minecraft:map_id", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        MAP_DECORATIONS = createType("minecraft:map_decorations", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        MAP_POST_PROCESSING = createType("minecraft:map_post_processing", MapPostProcessing::fromByteBuffer, MapPostProcessing::write);
        CHARGED_PROJECTILES = createType("minecraft:charged_projectiles", ProtocolUtil::readItemStackList, ProtocolUtil::writeItemStackList);
        BUNDLE_CONTENTS = createType("minecraft:bundle_contents", ProtocolUtil::readItemStackList, ProtocolUtil::writeItemStackList);
        POTION_CONTENTS = createType("minecraft:potion_contents", PotionContents::fromByteBuffer, PotionContents::write);
        //
        DEBUG_STICK_STATE = createType("minecraft:debug_stick_state", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        ENTITY_DATA = createType("minecraft:entity_data", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        BUCKET_ENTITY_DATA = createType("minecraft:bucket_entity_data", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        BLOCK_ENTITY_DATA = createType("minecraft:block_entity_data", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        //
        OMINOUS_BOTTLE_AMPLIFIER = createType("minecraft:ominous_bottle_amplifier", ProtocolUtil::readVarInt, ProtocolUtil::writeVarInt);
        //
        RECIPES = createType("minecraft:recipes", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        //
        FIREWORK_EXPLOSION = createType("minecraft:firework_explosion", FireworkExplosionComponent::fromByteBuffer, FireworkExplosionComponent::write);
        FIREWORKS = createType("minecraft:fireworks", FireworksComponent::fromByteBuffer, FireworksComponent::write);
        //
        BASE_COLOR = createType("minecraft:base_color", DyedColorComponent::fromByteBuffer, DyedColorComponent::write);
        //
        LOCK = createType("minecraft:lock", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
        CONTAINER_LOOT = createType("minecraft:container_loot", ProtocolUtil::readNBT, ProtocolUtil::writeNBT);
    }

    static @NotNull ComponentType<?> getType(int id) {
        return REGISTRY_RESULT.entryById()[id];
    }

    private static <T> @NotNull ComponentType<T> createType(@NotNull String key, @NotNull Function<ByteBuffer, T> reader,
                                                            @NotNull BiConsumer<T, ByteBuffer> writer) {
        RegistryEntry data = REGISTRY_RESULT.entryByKey().get(key);
        ComponentTypeImpl<T> type = new ComponentTypeImpl<>(data.key(), data.id(), reader, writer);
        // Override the default registry result with this new component that has a reader/writer
        REGISTRY_RESULT.entryByKey().put(key, type);
        REGISTRY_RESULT.entryById()[data.id()] = type;
        return type;
    }
}
