package de.stormmyyy.lobbysystem.listener;

import de.stormmyyy.lobbysystem.util.InventoryBuilder;
import de.stormmyyy.lobbysystem.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(Objects.requireNonNull(event.getItem()).getType() == Material.BOOK) {
                InventoryBuilder inventoryBuilder = new InventoryBuilder(54
                        , "§9§o■ §8┃ §9Teleporter");
                inventoryBuilder.setBackground(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§1")
                        .toItemStack());
                for (int i = 0; i < 9; i++) {
                    inventoryBuilder.setItem(i, new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§1")
                            .toItemStack());
                }

                for (int i = 9; i < 53; i++) {
                    inventoryBuilder.setItem(i, new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§1")
                            .toItemStack());
                }

                inventoryBuilder.setItem(49, new ItemBuilder(Material.BEACON).setName("§8» §9Spawn")
                        .toItemStack());
                inventoryBuilder.setItem(13, new ItemBuilder(Material.IRON_PICKAXE).setName("§8» §9CityBuild")
                        .toItemStack());
                inventoryBuilder.setItem(22, new ItemBuilder(Material.IRON_SWORD).setName("§8» §91vs1")
                        .toItemStack());
                inventoryBuilder.setItem(20, new ItemBuilder(Material.GRAY_DYE).setName("§8» §cComing Soon")
                        .toItemStack());
                inventoryBuilder.setItem(24, new ItemBuilder(Material.GRAY_DYE).setName("§8» §cComing Soon")
                        .toItemStack());
                inventoryBuilder.setItem(30, new ItemBuilder(Material.GRAY_DYE).setName("§8» §cComing Soon")
                        .toItemStack());
                inventoryBuilder.setItem(32, new ItemBuilder(Material.GRAY_DYE).setName("§8» §cComing Soon")
                        .toItemStack());

                inventoryBuilder.openInventory(player);
            }

        }
    }

}
