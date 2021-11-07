package me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.PunishmentType;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.time.Instant;
import java.util.Locale;

public class MenuHandler implements Listener {

    private final MenuApi api = SillyCore.getMenuApi();
    private final BiMap<Player, PunishmentType> punishmentTypeBiMap = MenuApi.getPunishmentTypeBiMap();
    private final BiMap<Player, OfflinePlayer> toPunish = MenuApi.getToPunish();

    private static final int SECOND = 1;
    private static final int MINUTE = SECOND * 60;
    private static final int HOUR = MINUTE * 60;
    private static final int DAY = HOUR * 24;
    private static final long WEEK = DAY * 7;
    private static final long MONTH = WEEK * 30;
    private static final long YEAR = MONTH * 12;
    private static final int PERMANENT = -1;
    private long currTime;
    private long EXPIRY;

    private OfflinePlayer target;
    private Player targetPlayer;

    @EventHandler
    public void MuteMenuHandler(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final InventoryView view = e.getView();
        final ItemStack item = e.getCurrentItem();

        if (item == null) return;
        if (!view.getTitle().contains("Mute Menu")) return;
        e.setCancelled(true);

        if (!punishmentTypeBiMap.containsKey(p)) return;
        final String name = item.getItemMeta().getDisplayName().toLowerCase(Locale.ENGLISH);

        target = toPunish.get(p);
        targetPlayer = target.getPlayer();
        currTime = Instant.now().getEpochSecond();

        if (name.contains("minute")) {
            EXPIRY = currTime + MINUTE;
            if (target.isOnline()) {
                targetPlayer.sendMessage();
            }
        } else if (name.contains("permanent")) {
            EXPIRY = -1;
        } else if (name.contains("year")) {
            EXPIRY = currTime + YEAR;
        } else if (name.contains("month")) {
            EXPIRY = currTime + MONTH;
        } else if (name.contains("day")) {
            EXPIRY = currTime + DAY;
        } else if (name.contains("week")) {
            EXPIRY = currTime + WEEK;
        } else if (name.contains("hour")) {
            EXPIRY = currTime + HOUR;
        }

    }
}
