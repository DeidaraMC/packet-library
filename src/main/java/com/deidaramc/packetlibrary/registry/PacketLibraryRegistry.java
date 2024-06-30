package com.deidaramc.packetlibrary.registry;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PacketLibraryRegistry {
    private static final JSONObject REGISTRIES_JSON;

    static {
        String path = "1_21/registries.json";
        REGISTRIES_JSON = createRegistryJson(path);
    }

    private static @NotNull JSONObject createRegistryJson(@NotNull String path) {
        try (InputStream is = PacketLibraryRegistry.class.getClassLoader().getResourceAsStream(path)) {
            return (JSONObject) JSON.parse(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load registry");
        }
    }

    @ApiStatus.Internal
    @SuppressWarnings("unchecked")
    public static <T extends RegistryEntry> @NotNull RegistryResult<T> getRegistryData(
            @NotNull RegistryType registryType, BiFunction<Key, Integer, T> supplier) {
        JSONObject registry = REGISTRIES_JSON.getJSONObject(registryType.namespace());
        JSONObject entries = registry.getJSONObject("entries");

        Map<String, T> entryByKey = new HashMap<>();
        T[] entryById = (T[]) Array.newInstance(registryType.registryClass(), entries.size());
        for (String key : entries.keySet()) {
            JSONObject entry = entries.getJSONObject(key);
            int id = entry.getInteger("protocol_id");
            T result = supplier.apply(Key.key(key), id);
            entryByKey.put(key, result);
            entryById[id] = result;
        }

        return new RegistryResult<T>(entryByKey, entryById);
    }

    public record RegistryResult<T extends RegistryEntry>(
            @NotNull Map<String, T> entryByKey, T[] entryById) { }
}
