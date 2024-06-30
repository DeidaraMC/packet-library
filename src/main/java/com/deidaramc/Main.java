package com.deidaramc;

import com.deidaramc.packetlibrary.entity.type.EntityType;
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
        /*

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
        System.out.println(packetStack); */

        byte[] bytes = {1, -24, 7, 1, 0, 5, 10, 8, 0, 4, 116, 101, 120, 116, 0, 14, 83, 101, 108, 101, 99, 116, 101, 100, 32, 75, 105, 116, 32, 40, 8, 0, 5, 99, 111, 108, 111, 114, 0, 5, 119, 104, 105, 116, 101, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 9, 0, 5, 101, 120, 116, 114, 97, 10, 0, 0, 0, 2, 8, 0, 5, 99, 111, 108, 111, 114, 0, 5, 103, 114, 101, 101, 110, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 6, 83, 112, 105, 100, 101, 114, 0, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 1, 41, 0, 0};
    //    ByteBuffer buffer = ByteBuffer.wrap(bytes);
   //     System.out.println(ItemStack.fromByteBuffer(buffer));

        byte[] bad = {1, -118, 9, 4, 0, 5, 10, 8, 0, 4, 116, 101, 120, 116, 0, 7, 78, 101, 101, 100, 108, 101, 114, 1, 0, 4, 98, 111, 108, 100, 1, 8, 0, 5, 99, 111, 108, 111, 114, 0, 7, 35, 102, 102, 97, 53, 48, 48, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 13, 4, 7, 9, 10, 8, 0, 4, 116, 101, 120, 116, 0, 1, 91, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 9, 0, 5, 101, 120, 116, 114, 97, 10, 0, 0, 0, 2, 8, 0, 5, 99, 111, 108, 111, 114, 0, 9, 100, 97, 114, 107, 95, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 10, -54, -97, -31, -76, -113, -54, -128, -31, -76, -121, 0, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 1, 93, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 26, 70, 105, 114, 101, 32, 97, 32, 115, 116, 114, 101, 97, 109, 32, 111, 102, 32, 112, 111, 105, 115, 111, 110, 111, 117, 115, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 8, 110, 101, 101, 100, 108, 101, 115, 33, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 10, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 1, 91, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 9, 0, 5, 101, 120, 116, 114, 97, 10, 0, 0, 0, 2, 8, 0, 5, 99, 111, 108, 111, 114, 0, 9, 100, 97, 114, 107, 95, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 13, -47, -107, -31, -76, -101, -31, -76, -128, -31, -76, -101, -47, -107, 0, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 1, 93, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 22, -31, -76, -123, -31, -76, -128, -31, -76, -115, -31, -76, -128, -55, -94, -31, -76, -121, 58, 32, 49, 46, 48, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 32, -31, -76, -117, -55, -76, -31, -76, -113, -31, -76, -124, -31, -76, -117, -54, -103, -31, -76, -128, -31, -76, -124, -31, -76, -117, 58, 32, 49, 46, 48, -47, -123, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 20, -31, -76, -128, -31, -76, -115, -31, -76, -113, -31, -76, -100, -55, -76, -31, -76, -101, 58, 32, 54, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 10, 8, 0, 4, 116, 101, 120, 116, 0, 28, -31, -76, -124, -31, -76, -113, -31, -76, -113, -54, -97, -31, -76, -123, -31, -76, -113, -31, -76, -95, -55, -76, 58, 32, 50, 46, 53, 115, 8, 0, 5, 99, 111, 108, 111, 114, 0, 4, 103, 114, 97, 121, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 0, 4, 0};
        ByteBuffer buffer = ByteBuffer.wrap(bad);
        System.out.println(ItemStack.fromByteBuffer(buffer));
    }
}