package me.sillysock.SillyCore.Listeners;

import me.sillysock.SillyCore.SillyCore;
import me.sillysock.SillyCore.API.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitEventHandlers
        implements Listener {

    private String joinMessage;
    private String quitMessage;
    private Player p;

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        joinMessage = Config.getJoinMessage();

        p = e.getPlayer();
        e.setJoinMessage(null);
        joinMessage = joinMessage.replace("{player_name}", p.getName());
        Bukkit.broadcastMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        quitMessage = Config.getQuitMessage();

        p = e.getPlayer();
        e.setQuitMessage(null);
        quitMessage = quitMessage.replace("{player_name}", p.getName());
        Bukkit.broadcastMessage(quitMessage);
    }
}
