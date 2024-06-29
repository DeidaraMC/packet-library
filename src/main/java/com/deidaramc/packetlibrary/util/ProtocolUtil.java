package com.deidaramc.packetlibrary.util;

import com.deidaramc.packetlibrary.adventure.serializer.nbt.NbtComponentSerializer;
import com.deidaramc.packetlibrary.item.ItemStack;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagType;
import net.kyori.adventure.nbt.BinaryTagTypes;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public final class ProtocolUtil {
    public static void writeAdventureComponent(@NotNull Component value, @NotNull ByteBuffer buffer) {
        BinaryTag tag = NbtComponentSerializer.nbt().serialize(value);
        writeNBT(tag, buffer);
    }

    public static @NotNull Component readAdventureComponent(@NotNull ByteBuffer buffer) {
        BinaryTag tag = readNBT(buffer);
        return NbtComponentSerializer.nbt().deserialize(tag);
    }

    public static void writeAdventureComponentList(@NotNull List<Component> value, @NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(value.size(), buffer);
        value.forEach(v -> writeAdventureComponent(v, buffer));
    }

    public static List<Component> readAdventureComponentList(@NotNull ByteBuffer buffer) {
        int size = ProtocolUtil.readVarInt(buffer);
        List<Component> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) list.add(ProtocolUtil.readAdventureComponent(buffer));
        return list;
    }

    public static boolean readBoolean(@NotNull ByteBuffer buffer) {
        return buffer.get() != 0;
    }

    public static void writeBoolean(boolean value, @NotNull ByteBuffer buffer) {
        buffer.put(value ? (byte) 1 : (byte) 0);
    }

    public static void writeItemStackList(@NotNull List<ItemStack> value, @NotNull ByteBuffer buffer) {
        ProtocolUtil.writeVarInt(value.size(), buffer);
        value.forEach(itemStack -> itemStack.write(buffer));
    }

    public static List<ItemStack> readItemStackList(@NotNull ByteBuffer buffer) {
        int size = ProtocolUtil.readVarInt(buffer);
        List<ItemStack> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) list.add(ItemStack.fromByteBuffer(buffer));
        return list;
    }

    public static void writeNBT(@NotNull BinaryTag value, @NotNull ByteBuffer buffer) {
        BinaryTagType<BinaryTag> type = (BinaryTagType<BinaryTag>) value.type();
        buffer.put(type.id());
        DataOutput output = new DataOutputStream(new OutputStream() {
            @Override
            public void write(int b) {
                buffer.put((byte) b);
            }
        });
        try {
            type.write(value, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BinaryTagType<?>[] NBT_TYPES = new BinaryTagType[] {
            BinaryTagTypes.END, BinaryTagTypes.BYTE, BinaryTagTypes.SHORT, BinaryTagTypes.INT, BinaryTagTypes.LONG,
            BinaryTagTypes.FLOAT, BinaryTagTypes.DOUBLE, BinaryTagTypes.BYTE_ARRAY, BinaryTagTypes.STRING,
            BinaryTagTypes.LIST, BinaryTagTypes.COMPOUND, BinaryTagTypes.INT_ARRAY, BinaryTagTypes.LONG_ARRAY
    };
    public static BinaryTag readNBT(@NotNull ByteBuffer buffer) {
        BinaryTagType<?> type = NBT_TYPES[buffer.get()];
        DataInput input = new DataInputStream(new InputStream() {
            @Override
            public int read() {
                return buffer.get() & 0xFF;
            }

            @Override
            public int available() {
                return buffer.remaining();
            }
        });
        try {
            return type.read(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int readVarInt(ByteBuffer buffer) {
        int i = 0;
        int maxRead = Math.min(5, buffer.remaining());
        for (int j = 0; j < maxRead; j++) {
            int k = buffer.get();
            i |= (k & 0x7F) << j * 7;
            if ((k & 0x80) != 128) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void writeVarInt(int value, ByteBuffer buffer) {
        if ((value & (0xFFFFFFFF << 7)) == 0) {
            buffer.put((byte) value);
        } else if ((value & (0xFFFFFFFF << 14)) == 0) {
            int w = (value & 0x7F | 0x80) << 8 | (value >>> 7);
            buffer.putShort((short) w);
        } else if ((value & (0xFFFFFFFF << 21)) == 0) {
            int w = (value & 0x7F | 0x80) << 16 | ((value >>> 7) & 0x7F | 0x80) << 8 | (value >>> 14);
            buffer.put((byte) (w >> 16));
            buffer.put((byte) (w >> 8));
            buffer.put((byte) w);
        } else if ((value & (0xFFFFFFFF << 28)) == 0) {
            int w = (value & 0x7F | 0x80) << 24 | (((value >>> 7) & 0x7F | 0x80) << 16)
                    | ((value >>> 14) & 0x7F | 0x80) << 8 | (value >>> 21);
            buffer.putInt(w);
        } else {
            int w = (value & 0x7F | 0x80) << 24 | ((value >>> 7) & 0x7F | 0x80) << 16
                    | ((value >>> 14) & 0x7F | 0x80) << 8 | ((value >>> 21) & 0x7F | 0x80);
            buffer.putInt(w);
            buffer.put((byte) (value >>> 28));
        }
    }
}
