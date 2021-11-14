package me.sillysock.SillyCore.Commands.Moderator.Punishment.Kick;

import com.google.common.collect.BiMap;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class KickListeners implements Listener {

    private static final BiMap<Player, Player> typingReason = KickCommand.getTypingReason();
    private Player target;

    @EventHandler
    public void KickListenerHandler(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String msg = e.getMessage();

        if (!typingReason.containsKey(p)) return;

        target = typingReason.get(p);
        e.setCancelled(true);

        // The below code is to kick a player while not being asynchronous.
        Bukkit.getScheduler().runTask(SillyCore.getInstance(), () -> {
            p.kickPlayer(MessageUtils.format(msg));
            p.sendMessage(Lang.formatYouHaveKicked(target.getName()));
        });

        p.sendMessage(Lang.formatYouHaveKicked(target.getName()));
    }
}
