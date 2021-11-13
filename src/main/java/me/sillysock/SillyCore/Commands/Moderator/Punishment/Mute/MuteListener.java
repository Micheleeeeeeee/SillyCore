package me.sillysock.SillyCore.Commands.Moderator.Punishment.Mute;

import com.google.common.collect.BiMap;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus.MenuHandler;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteListener implements Listener {

    private final BiMap<Player, OfflinePlayer> typingReason = MuteCommand.getTypingReason();
    private static final DataHandler handler = SillyCore.getDataHandler();
    private final BiMap<Player, Long> expiries = MenuHandler.getExpiries();
    private OfflinePlayer target;

    @EventHandler
    public void MuteListener(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String msg = e.getMessage();

        if (handler.isMuted(p.getUniqueId())) {
            p.sendMessage("You cannot talk whilst muted.");
            e.setCancelled(true);
        }

        if (!typingReason.containsKey(p)) return;
        target = typingReason.get(p);
        typingReason.remove(p);
        e.setCancelled(true);

        handler.mute(target.getUniqueId(), msg, expiries.get(p));
        if (target.isOnline()) target.getPlayer().sendMessage("You have been muted.");
        p.sendMessage(Lang.formatYouHaveKicked(target.getName()));
    }
}
