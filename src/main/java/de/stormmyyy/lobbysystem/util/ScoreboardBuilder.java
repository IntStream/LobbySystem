package de.stormmyyy.lobbysystem.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class ScoreboardBuilder {

    private static Scoreboard scoreboard;
    private static Objective objective;
    @Deprecated
    public ScoreboardBuilder createScoreboard(String obj, String obj2, String displayName) {
        scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        objective = scoreboard.registerNewObjective(obj, obj2);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(displayName);
        return this;
    }

    public ScoreboardBuilder createLine(String line, int lineNumber) {
        objective.getScore(line).setScore(lineNumber);
        return this;
    }


    public ScoreboardBuilder setScoreboard() {

        Bukkit.getOnlinePlayers().forEach(all -> all.setScoreboard(getScoreboard()));

        return this;
    }
    @Deprecated
    public ScoreboardBuilder setLobbyTeams() {

        Team admin = scoreboard.registerNewTeam("001Tab001");
        Team moderator = scoreboard.registerNewTeam("002Tab002");
        Team developer = scoreboard.registerNewTeam("003Tab003");
        Team game = scoreboard.registerNewTeam("004Tab004");
        Team supporter = scoreboard.registerNewTeam("005Tab005");
        Team builder = scoreboard.registerNewTeam("006Tab006");
        Team demon = scoreboard.registerNewTeam("007Tab007");
        Team phantom = scoreboard.registerNewTeam("008Tab008");
        Team defaultPlayer = scoreboard.registerNewTeam("009Tab009");

        for (Player all : Bukkit.getOnlinePlayers()) {

            if (all.hasPermission("permission.admin")) {
                admin.addPlayer(all);
                admin.setPrefix("§4Admin §8┃ ");
                admin.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.moderator")) {
                moderator.addPlayer(all);
                moderator.setPrefix("§cMod §8┃ ");
                moderator.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.developer")) {
                developer.addPlayer(all);
                developer.setPrefix("§bDev §8┃ ");
                developer.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.game")) {
                game.addPlayer(all);
                game.setPrefix("§9Game §8┃ ");
                game.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.supporter")) {
                supporter.addPlayer(all);
                supporter.setPrefix("§6Sup §8┃ ");
                supporter.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.builder")) {
                builder.addPlayer(all);
                builder.setPrefix("§aBuilder §8┃ ");
                builder.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.demon")) {
                demon.addPlayer(all);
                demon.setPrefix("§cDemon §8┃ ");
                demon.setColor(ChatColor.GRAY);
            }else if (all.hasPermission("permission.phantom")) {
                phantom.addPlayer(all);
                phantom.setPrefix("§fPhantom §8┃ ");
                phantom.setColor(ChatColor.GRAY);
            }else{
                defaultPlayer.addPlayer(all);
                defaultPlayer.setPrefix("§7Spieler §8┃ ");
                defaultPlayer.setColor(ChatColor.GRAY);
            }

        }

        return this;
    }

    public static Scoreboard getScoreboard() {
        return scoreboard;
    }
}
