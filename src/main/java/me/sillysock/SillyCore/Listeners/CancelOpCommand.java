package me.sillysock.SillyCore.Listeners;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Locale;

public class CancelOpCommand
        implements Listener {

    Player p;

    @EventHandler
    public void CancelOpCommand(final PlayerCommandPreprocessEvent e) {
        p = e.getPlayer();

        if (e.getMessage().toLowerCase().startsWith("/op") && !SillyCore.isOpCommandEnabled())  {
            p.sendMessage(SillyCore.getOpCommandDisabled());
            e.setCancelled(true);
        }
    }
}
