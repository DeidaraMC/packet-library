package com.deidaramc.packetlibrary.registry;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PacketLibraryRegistry {
    private static final Map<String, RegistryData> MATERIAL_DATA = new HashMap<>();
    private static final Map<String, RegistryData> COMPONENT_DATA = new HashMap<>();
    private static final Map<String, RegistryData> MOB_EFFECT_DATA = new HashMap<>();

    static {
        String path = "1_21/registries.json";
        JSONObject registries = createRegistryJson(path);
        loadRegistryData(registries, "minecraft:item", MATERIAL_DATA);
        loadRegistryData(registries, "minecraft:data_component_type", COMPONENT_DATA);
        loadRegistryData(registries, "minecraft:mob_effect", MOB_EFFECT_DATA);
    }

    private static @NotNull JSONObject createRegistryJson(@NotNull String path) {
        try (InputStream is = PacketLibraryRegistry.class.getClassLoader().getResourceAsStream(path)) {
            return (JSONObject) JSON.parse(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load registry");
        }
    }

    private static void loadRegistryData(@NotNull JSONObject registries, @NotNull String key, @NotNull Map<String, RegistryData> map) {
        JSONObject registry = (JSONObject) registries.get(key);
        JSONObject entries = (JSONObject) registry.get("entries");
        entries.keySet().forEach(name -> {
            JSONObject entry = (JSONObject) entries.get(name);
            int id = entry.getInteger("protocol_id");
            map.put(name, new RegistryData(Key.key(name), id));
        });
    }

    public static @NotNull Map<String, RegistryData> getMaterialRegistryData() {
        return new HashMap<>(MATERIAL_DATA);
    }

    public static @NotNull Map<String, RegistryData> getComponentRegistryData() {
        return new HashMap<>(COMPONENT_DATA);
    }

    public static @NotNull Map<String, RegistryData> getMobEffectRegistryData() {
        return new HashMap<>(MOB_EFFECT_DATA);
    }

    public record RegistryData(@NotNull Key key, int id) { }
}
