package de.stormmyyy.lobbysystem.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.stream.IntStream;

public class InventoryBuilder {

    private static Inventory inventory;

    public InventoryBuilder(int size, String displayName) {
        inventory = Bukkit.createInventory(null, size, displayName);
    }

    public InventoryBuilder setItem(int slot, ItemStack itemStack) {
        inventory.setItem(slot, itemStack);
        return this;
    }

    public InventoryBuilder addItem(ItemStack itemStack) {
        inventory.addItem(itemStack);
        return this;
    }

    public InventoryBuilder setBackground(ItemStack itemStack) {
        IntStream.range(0, inventory.getSize()).forEachOrdered(i -> inventory.setItem(i, itemStack));
        return this;
    }

    public InventoryBuilder openInventory(Player player) {
        player.openInventory(inventory);
        return this;
    }

}
