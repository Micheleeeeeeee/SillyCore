package me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.PunishmentType;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.Mute.MuteCommand;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.util.Locale;

public class MenuHandler implements Listener {

    private final BiMap<Player, PunishmentType> punishmentTypeBiMap = MenuApi.getPunishmentTypeBiMap();
    private final BiMap<Player, OfflinePlayer> toPunish = MenuApi.getToPunish();
    private final BiMap<Player, OfflinePlayer> typingReason = MuteCommand.getTypingReason();
    private static final BiMap<Player, Long> expiries = HashBiMap.create();

    private final int SECOND = 1;
    private final int MINUTE = SECOND * 60;
    private final int HOUR = MINUTE * 60;
    private final int DAY = HOUR * 24;
    private final long WEEK = DAY * 7;
    private final long MONTH = WEEK * 30;
    private final long YEAR = MONTH * 12;
    private final int PERMANENT = -1;
    private long currTime;
    private long EXPIRY;

    private OfflinePlayer target;
    private Player targetPlayer;

    private final DataHandler handler = SillyCore.getDataHandler();

    @EventHandler
    public void MuteMenuHandler(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final InventoryView view = e.getView();
        final ItemStack item = e.getCurrentItem();

        if (item == null) return;
        if (!view.getTitle().contains("Mute Menu")) return;
        e.setCancelled(true);

        if (!punishmentTypeBiMap.containsKey(p)) return;
        final String name = item.getItemMeta()
                .getDisplayName()
                .toLowerCase(Locale.ENGLISH);

        target = toPunish.get(p);
        targetPlayer = target.getPlayer();
        currTime = Instant.now().getEpochSecond();

        if (name.contains("minute")) {
            EXPIRY = currTime + MINUTE;
        } else if (name.contains("permanent")) {
            EXPIRY = PERMANENT;
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

        // Tell the player to type the reason
        p.sendMessage(Lang.getTypePunishmentReason());
        p.closeInventory();
        expiries.put(p, EXPIRY);
        typingReason.put(p, target);
    }

    public static BiMap<Player, Long> getExpiries() {
        return expiries;
    }
}
