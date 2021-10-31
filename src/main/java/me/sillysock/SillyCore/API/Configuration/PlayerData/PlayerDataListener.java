package me.sillysock.SillyCore.API.Configuration.PlayerData;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerDataListener implements Listener {

    private DataHandler handler = SillyCore.getDataHandler();

    @EventHandler
    public void PlayerDataListener(final PlayerJoinEvent e) {
        final UUID uuid = e.getPlayer().getUniqueId();
        if (!handler.playerExists(uuid)) handler.createPlayer(uuid);
        if (handler.isNicked(uuid)) Bukkit.broadcastMessage("They're nicked.");
    }
}
