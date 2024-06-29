package com.deidaramc.packetlibrary.item;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@ApiStatus.Internal
final class ItemComponents {
    private final Map<ComponentType<?>, ItemComponent<?>> addedMap;
    private final Set<ComponentType<?>> removedSet;

    ItemComponents(@NotNull Map<ComponentType<?>, ItemComponent<?>> addedMap, Set<ComponentType<?>> removedMap) {
        this.addedMap = addedMap;
        this.removedSet = removedMap;
    }

    ItemComponents() {
        this(new HashMap<>(), new HashSet<>());
    }

    @ApiStatus.Internal
    <T> void addComponent(ItemComponent<T> component) {
        addedMap.put(component.type(), component);
        removedSet.remove(component.type());
    }

    @ApiStatus.Internal
    <T> void removeComponent(ComponentType<T> type) {
        removedSet.add(type);
        addedMap.remove(type);
    }

    Collection<ItemComponent<?>> componentsToAdd() {
        return addedMap.values();
    }

    Collection<ComponentType<?>> componentsToRemove() {
        return removedSet;
    }

    /**
     * Return a new ItemComponents object, adding and removing components
     * will not affect the original
     */
    @NotNull ItemComponents copy() {
        return new ItemComponents(new HashMap<>(addedMap), new HashSet<>(removedSet));
    }


    @Override
    public String toString() {
        return "ItemComponents[added=" + addedMap + ", removed=" + removedSet + "]";
    }
}
