package com.deidaramc;

import com.deidaramc.packetlibrary.item.ItemComponent;
import com.deidaramc.packetlibrary.item.ItemStack;
import com.deidaramc.packetlibrary.item.component.*;
import com.deidaramc.packetlibrary.item.material.Material;
import com.deidaramc.packetlibrary.potion.effect.PotionEffect;
import com.deidaramc.packetlibrary.potion.type.PotionType;
import com.deidaramc.packetlibrary.util.Color;
import com.deidaramc.packetlibrary.util.ProtocolUtil;
import net.kyori.adventure.text.Component;

import java.nio.ByteBuffer;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ByteBuffer tagBuffer = ByteBuffer.allocate(1000);
        Component component = Component.text("This is a test!!");
        ProtocolUtil.writeAdventureComponent(component, tagBuffer);
        tagBuffer.flip();
        System.out.println(ProtocolUtil.readAdventureComponent(tagBuffer));

        ByteBuffer buffer = ByteBuffer.allocate(1000);
        ItemStack stack = ItemStack.of(Material.STICK, 1)
                .with(ItemComponent.FIRE_RESISTANT)
                .with(ItemComponent.UNBREAKABLE, true).with(ItemComponent.MAX_DAMAGE, 69)
                .with(ItemComponent.DAMAGE, 21).without(ItemComponent.UNBREAKABLE)
                .with(ItemComponent.POTION_CONTENTS, new PotionContents(PotionType.TURTLE_MASTER, new Color(83), List.of(new PotionDetails(PotionEffect.BAD_OMEN, 1, 1))))
                .with(ItemComponent.FOOD, new FoodComponent(3, false, true, 0.7f, List.of(new ProbablePotionDetails(new PotionDetails(PotionEffect.ABSORPTION, 0, 0), 0.5f))))
                .with(ItemComponent.FIREWORKS, new FireworksComponent(3, new FireworkExplosionComponent(FireworkShape.CREEPER, new Color(3, 3, 3), new Color(3, 3, 3), true, false)));

        stack.write(buffer);
        buffer.flip();
        ItemStack packetStack = ItemStack.fromByteBuffer(buffer);
        System.out.println(packetStack);
    }
}