package de.stormmyyy.lobbysystem.command;

import de.stormmyyy.lobbysystem.LobbySystem;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.stormmyyy.lobbysystem.util.DifferentClass.LocationManager.setLocation;

public class LocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!player.hasPermission("permission.location")) {
            player.sendMessage(LobbySystem.getInstance().getNoPerm());
            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH,10,10);
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("spawn")) {
                setLocation(player, "spawn");
                player.sendMessage(LobbySystem.getInstance().getPrefix()
                        + "Du hast die Location vom §9Spawn §7gesetzt§8.");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,10,10);
            }else if (args[0].equalsIgnoreCase("citybuild")) {
                setLocation(player, "citybuild");
                player.sendMessage(LobbySystem.getInstance().getPrefix()
                        + "Du hast die Location von §9CityBuild §7gesetzt§8.");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,10,10);
            }else if (args[0].equalsIgnoreCase("1vs1")) {
                setLocation(player, "1vs1");
                player.sendMessage(LobbySystem.getInstance().getPrefix()
                        + "Du hast die Location von §91vs1 §7gesetzt§8.");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,10,10);
            }
        }

        return false;
    }
}
