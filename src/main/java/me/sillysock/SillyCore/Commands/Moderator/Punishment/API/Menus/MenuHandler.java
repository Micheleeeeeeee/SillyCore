package me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus;

import com.google.common.collect.BiMap;
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
import java.util.Locale;

public class MenuHandler implements Listener {

    private final MenuApi api = SillyCore.getMenuApi();
    private final BiMap<Player, PunishmentType> punishmentTypeBiMap = MenuApi.getPunishmentTypeBiMap();
    private final BiMap<Player, OfflinePlayer> toPunish = MenuApi.getToPunish();

    @EventHandler
    public void MuteMenuHandler(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final InventoryView view = e.getView();
        final ItemStack item = e.getCurrentItem();

        if (item == null) return;
        if (!view.getTitle().contains("Mute Menu")) return;
        e.setCancelled(true);

        if (!punishmentTypeBiMap.containsKey(p)) return;
        final String name = item.getItemMeta().getDisplayName().toLowerCase(Locale.ROOT);

        if (name.contains("minute")) p.sendMessage("minute");
        if (name.contains("permanent")) p.sendMessage("permanent");
        if (name.contains("year")) p.sendMessage("year");
        if (name.contains("month")) p.sendMessage("month");
        if (name.contains("day")) p.sendMessage("day");
        if (name.contains("week")) p.sendMessage("week");
    }
}
