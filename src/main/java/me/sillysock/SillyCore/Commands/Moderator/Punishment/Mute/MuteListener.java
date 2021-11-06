package me.sillysock.SillyCore.Commands.Moderator.Punishment.Mute;

import com.google.common.collect.BiMap;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteListener implements Listener {

    private final BiMap<Player, OfflinePlayer> typingReason = MuteCommand.getTypingReason();
    private static final DataHandler handler = SillyCore.getDataHandler();
    private OfflinePlayer target;

    @EventHandler
    public void MuteListener(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String msg = e.getMessage();

        if (handler.isMuted(p.getUniqueId())) {
            e.setCancelled(true);
            p.sendMessage("You cannot talk whilst muted.");
        }

        if (!typingReason.containsKey(p)) return;
        typingReason.remove(p);
        target = typingReason.get(p);
        e.setCancelled(true);

        handler.setMuted(target.getUniqueId(), true);
        if (target.isOnline()) target.getPlayer().sendMessage("You have been muted.");
        p.sendMessage(Lang.formatYouHaveKicked(target.getName()));
    }
}
