package de.stormmyyy.lobbysystem.command;

import de.stormmyyy.lobbysystem.LobbySystem;
import de.stormmyyy.lobbysystem.util.DifferentClass;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.stormmyyy.lobbysystem.util.DifferentClass.PlayerManager.protection;
import static de.stormmyyy.lobbysystem.util.DifferentClass.PlayerManager.setup;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!player.hasPermission("permission.build")) {
            player.sendMessage(LobbySystem.getInstance().getNoPerm());
            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH,10,10);
            return true;
        }

        if (protection.contains(player.getUniqueId())) {
            protection.remove(player.getUniqueId());

            setup(player);
            player.sendMessage(LobbySystem.getInstance().getPrefix() + "Du hast den §9Build §7Modus §cverlassen§8.");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,10,10);
            return true;
        }

        protection.add(player.getUniqueId());

        player.getInventory().clear();
        player.setGameMode(GameMode.CREATIVE);

        player.sendMessage(LobbySystem.getInstance().getPrefix() + "Du hast den §9Build §7Modus §abetreten§8.");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,10,10);
        return false;
    }
}
