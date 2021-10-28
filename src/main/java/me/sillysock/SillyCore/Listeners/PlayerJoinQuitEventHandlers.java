package me.sillysock.SillyCore.Listeners;

import me.sillysock.SillyCore.API.Configuration.Lang;
import org.bukkit.Bukkit;
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
        joinMessage = Lang.getJoinMessage();

        p = e.getPlayer();
        e.setJoinMessage(null);
        joinMessage = joinMessage.replace("{player_name}", p.getName());
        Bukkit.broadcastMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        quitMessage = Lang.getQuitMessage();

        p = e.getPlayer();
        e.setQuitMessage(null);
        quitMessage = quitMessage.replace("{player_name}", p.getName());
        Bukkit.broadcastMessage(quitMessage);
    }
}
