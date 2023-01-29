package de.stormmyyy.lobbysystem.util;

import de.stormmyyy.lobbysystem.LobbySystem;
import de.stormmyyy.lobbysystem.command.BuildCommand;
import de.stormmyyy.lobbysystem.command.LocationCommand;
import de.stormmyyy.lobbysystem.listener.ClickListener;
import de.stormmyyy.lobbysystem.listener.HotbarListener;
import de.stormmyyy.lobbysystem.listener.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class DifferentClass {

    public static class Initialization {

        public static void register() {
            Objects.requireNonNull(Bukkit.getServer().getPluginCommand("build")).setExecutor(new BuildCommand());
            Objects.requireNonNull(Bukkit.getServer().getPluginCommand("location")).setExecutor(new LocationCommand());

            Bukkit.getServer().getPluginManager().registerEvents(new PlayerManager(), LobbySystem.getInstance());
            Bukkit.getServer().getPluginManager().registerEvents(new ProtectionManager(), LobbySystem.getInstance());
            Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(), LobbySystem.getInstance());
            Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), LobbySystem.getInstance());
            Bukkit.getServer().getPluginManager().registerEvents(new HotbarListener(), LobbySystem.getInstance());
        }

    }

    public static class PlayerManager implements Listener {

        public static ArrayList<UUID> protection = new ArrayList<>();

        public static void setup(Player player) {
            player.getInventory().clear();
            player.setFoodLevel(40);
            player.setHealth(20);
            player.setFireTicks(0);
            player.setGameMode(GameMode.ADVENTURE);

            player.getInventory().setItem(3, new ItemBuilder(Material.BOOK).setName("§8» §eTeleporter §8● §7Rechtsklick").toItemStack());
            player.getInventory().setItem(5, new ItemBuilder(Material.REDSTONE_TORCH).setName("§8» §eEinstellungen §8● §7Rechtsklick").toItemStack());
        }

        private static final ScoreboardBuilder scoreboardBuilder = new ScoreboardBuilder();
        @Deprecated
        public static void createLobbyScoreboard() {
            scoreboardBuilder.createScoreboard("aaa", "bbb", "§8» §9VaruniaNET");

            //Lines
            scoreboardBuilder.createLine("§1", 14);
            scoreboardBuilder.createLine("§7§o■ §8┃ §7", 13);
            scoreboardBuilder.createLine("  §8» §91000", 12);
            scoreboardBuilder.createLine("§2", 11);
            scoreboardBuilder.createLine("§7§o■ §8┃ §7Clan", 10);
            scoreboardBuilder.createLine("  §8» §9Keinen", 9);
            scoreboardBuilder.createLine("§3", 8);
            scoreboardBuilder.setLobbyTeams();
            scoreboardBuilder.setScoreboard();
        }
        @EventHandler @Deprecated
        public void onJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();

            setup(player);
            if (!player.hasPlayedBefore()) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(LobbySystem.getInstance(), () -> {
                    player.teleport(LocationManager.getLocation("spawn"));
                }, 20L * 20L);
            }else{
                player.teleport(LocationManager.getLocation("spawn"));
            }

            createLobbyScoreboard();

            event.setJoinMessage(null);
        }

        @EventHandler @Deprecated
        public void onQuit(PlayerQuitEvent event) {
            Player player = event.getPlayer();

            createLobbyScoreboard();
            protection.remove(player.getUniqueId());
            event.setQuitMessage(null);
        }

    }

    public static class ProtectionManager implements Listener {

        @EventHandler
        public void onProtect(BlockBreakEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(BlockPlaceEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(PlayerDropItemEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(PlayerPickupItemEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(PlayerInteractEntityEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(PlayerInteractEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(PlayerInteractAtEntityEvent event) {
            if (!PlayerManager.protection.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(InventoryClickEvent event) {
            if (!PlayerManager.protection.contains(event.getWhoClicked().getUniqueId())) event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(EntityDamageByBlockEvent event) {
            event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(EntityDamageByEntityEvent event) {
            event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(EntityDamageEvent event) {
            event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(WeatherChangeEvent event) {
            event.setCancelled(true);
        }

        @EventHandler
        public void onProtect(FoodLevelChangeEvent event) {
            event.setCancelled(true);
        }

    }

    public static class LocationManager {

        private static final File file = new File("plugins//LobbySystem//Location.yml");
        private static final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        private static void save() {
            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void setLocation(Player player, String name) {
            config.set(name + ".World", player.getWorld().getName());
            config.set(name + ".X", player.getLocation().getX());
            config.set(name + ".Y", player.getLocation().getY());
            config.set(name + ".Z", player.getLocation().getZ());
            config.set(name + ".Yaw", player.getLocation().getYaw());
            config.set(name + ".Pitch", player.getLocation().getPitch());
            save();
        }

        public static Location getLocation(String name) {
            Location location = new Location(Bukkit.getWorld(Objects.requireNonNull(
                    config.getString(name + ".World"))), config.getDouble(name + ".X"),
                    config.getDouble(name + ".Y"), config.getDouble(name + ".Z"));
            location.setYaw(config.getInt(name + ".Yaw"));
            location.setPitch(config.getInt(name + ".Pitch"));
            return location;
        }

    }

}
