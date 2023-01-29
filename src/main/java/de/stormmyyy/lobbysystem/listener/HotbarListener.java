package de.stormmyyy.lobbysystem.listener;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HotbarListener implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        event.getPlayer().playSound(event.getPlayer().getLocation(),
                Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 10,10);
    }

}
