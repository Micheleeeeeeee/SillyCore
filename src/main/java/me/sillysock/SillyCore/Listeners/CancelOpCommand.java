package me.sillysock.SillyCore.Listeners;

import me.sillysock.SillyCore.API.Configuration.Config;
import me.sillysock.SillyCore.API.Configuration.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CancelOpCommand
        implements Listener {

    Player p;

    @EventHandler
    public void CancelOpCommand(final PlayerCommandPreprocessEvent e) {
        p = e.getPlayer();

        if (e.getMessage().toLowerCase().startsWith("/op") && Config.isOpDisabled())  {
            p.sendMessage(Lang.getOpCommandDisabled());
            e.setCancelled(true);
        }
    }
}
