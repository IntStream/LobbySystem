package de.stormmyyy.lobbysystem.listener;

import de.stormmyyy.lobbysystem.util.DifferentClass;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static de.stormmyyy.lobbysystem.util.DifferentClass.LocationManager.getLocation;

public class ClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase("§9§o■ §8┃ §9Teleporter")) {

            assert event.getCurrentItem() != null;

            if (event.getCurrentItem().getType() == Material.BEACON) {
                player.teleport(getLocation("spawn"));
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,10,10);
            }else if (event.getCurrentItem().getType() == Material.IRON_PICKAXE) {
                player.teleport(getLocation("citybuild"));
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,10,10);
            }else if (event.getCurrentItem().getType() == Material.IRON_SWORD) {
                player.teleport(getLocation("1vs1"));
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,10,10);
            }

        }
    }

}
