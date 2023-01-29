package de.stormmyyy.lobbysystem;

import org.bukkit.plugin.java.JavaPlugin;

import static de.stormmyyy.lobbysystem.util.DifferentClass.Initialization.register;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;
    private final String prefix = "§9§o■ §8┃ §9Lobby §8● §7";
    private final String noPerm = prefix + "§cDazu benötigst du einen höheren Rang.";

    public static LobbySystem getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        register();
    }

    @Override
    public void onDisable() {

    }

    public String getPrefix() {
        return prefix;
    }

    public String getNoPerm() {
        return noPerm;
    }
}
