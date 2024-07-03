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

        byte[] bad = { 1, -26, 7, 3, 0, 31, 1, 19, 0, 0, 14, 5, 10, 8, 0, 4, 116, 101, 120, 116, 0, 14, 83, 101, 108, 101, 99, 116, 101, 100, 32, 75, 105, 116, 32, 40, 8, 0, 5, 99, 111, 108, 111, 114, 0, 5, 119, 104, 105, 116, 101, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 9, 0, 5, 101, 120, 116, 114, 97, 10, 0, 0, 0, 2, 8, 0, 5, 99, 111, 108, 111, 114, 0, 5, 103, 114, 101, 101, 110, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 5, 87, 105, 116, 99, 104, 0, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 1, 41, 0, 0 };
        byte [] bad2 = { 1, -26, 7, 3, 0, 31, 1, 11, 0, 0, 14, 5, 10, 8, 0, 4, 116, 101, 120, 116, 0, 14, 83, 101, 108, 101, 99, 116, 101, 100, 32, 75, 105, 116, 32, 40, 8, 0, 5, 99, 111, 108, 111, 114, 0, 5, 119, 104, 105, 116, 101, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 1, 0, 6, 105, 116, 97, 108, 105, 99, 0, 9, 0, 5, 101, 120, 116, 114, 97, 10, 0, 0, 0, 2, 8, 0, 5, 99, 111, 108, 111, 114, 0, 5, 103, 114, 101, 101, 110, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 5, 87, 105, 116, 99, 104, 0, 8, 0, 4, 116, 121, 112, 101, 0, 4, 116, 101, 120, 116, 8, 0, 4, 116, 101, 120, 116, 0, 1, 41, 0, 0 };
        ByteBuffer buffer = ByteBuffer.wrap(bad2);
        System.out.println(ItemStack.fromByteBuffer(buffer));
    }
}