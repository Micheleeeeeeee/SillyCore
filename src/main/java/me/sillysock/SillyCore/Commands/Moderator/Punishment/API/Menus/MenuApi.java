package me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.PunishmentType;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class MenuApi {

    private static BiMap<Player, OfflinePlayer> toPunish; // Stores the punisher & punishee
    private static BiMap<Player, PunishmentType> punishmentTypeBiMap; // Stores the punisher & the punishmenttype

    private static final DataHandler dataHandler = SillyCore.getDataHandler();

    private Inventory punishMenu;
    private Inventory muteMenu;

    public MenuApi() {
        toPunish = HashBiMap.create();
        punishmentTypeBiMap = HashBiMap.create();
    }

    public void sendData(final Player p) {
        punishMenu = createPunishMenu(toPunish.get(p));
        p.openInventory(punishMenu);
    }

    public Inventory createPunishMenu(final OfflinePlayer target) {
        punishMenu = Bukkit.createInventory(null, 36, ChatColor.YELLOW + "Punish Menu");

        punishMenu.setItem(4, getHead(target)); // Set the 4th item in the menu the head of the target player (who is being punished)

        return punishMenu;
    }

    public Inventory createMuteMenu(final OfflinePlayer target) {
        /*
        ----TARGET----
        -1 MINUTE-1HOUR-1DAY-1MONTH-
        ----1 YEAR----
        --------PERMANENT
         */

        muteMenu = Bukkit.createInventory(null, 36, ChatColor.YELLOW + "Mute Menu");

        muteMenu.setItem(4, getHeadWithInfo(target));
        final String name = target.getName();

        createMenuItem(muteMenu, 10,
                format("&c1 Minute"), null,
                format("&fMute " + name), format("&fFor 1 minute."),
                null, null,
                Material.TERRACOTTA);

        createMenuItem(muteMenu, 12,
                format("&c1 Hour"), null,
                format("&fMute " + name), format("&ffor 1 hour."),
                null, null,
                Material.TERRACOTTA);

        createMenuItem(muteMenu, 14,
                format("&c1 Day"), null,
                format("&fMute " + name), format("&ffor 1 day."),
                null, null,
                Material.TERRACOTTA);

        createMenuItem(muteMenu, 16,
                format("&c1 Week"), null,
                format("&fMute " + name), format("&ffor 1 week."),
                null, null,
                Material.TERRACOTTA);

        createMenuItem(muteMenu, 20,
                format("&c1 Month"), null,
                format("&fMute " + name), format("&ffor 1 Month."),
                null, null,
                Material.TERRACOTTA);

        createMenuItem(muteMenu, 24,
                format("&c1 Year"), null,
                format("&fMute " + name), format("&ffor 1 year."),
                null, null,
                Material.TERRACOTTA);

        createMenuItem(muteMenu, 35,
                format("&c&lPERMANENT"), null,
                format("&cPermanently mute " + name + "."), null,
                null, null,
                Material.TERRACOTTA);

        return muteMenu;
    }

    public void createMenuItem(final Inventory inv, final int slot,
                               final String name, final String lore1,
                               final String lore2, final String lore3,
                               final String lore4, final ArrayList<Enchantment> enchantments,
                               final Material mat) {

        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<>();

        if (lore1 != null) lore.add(lore1);
        if (lore2 != null) lore.add(lore2);
        if (lore3 != null) lore.add(lore3);
        if (lore4 != null) lore.add(lore4);

        meta.setLore(lore);
        // for (final Enchantment enchantment : enchantments) meta.addEnchant(enchantment, 1, true);

        item.setItemMeta(meta);
        inv.setItem(slot, item);
    }

    public ItemStack getHeadWithInfo(final OfflinePlayer p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(p);
        meta.setDisplayName(ChatColor.YELLOW + p.getName());

        ArrayList<String> lore = new ArrayList<>();
        lore.add(null);
        lore.add(MessageUtils.format("&fName: " + p.getName()));
        lore.add(MessageUtils.format("&fNickname: " + dataHandler.getNick(p.getUniqueId())));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getHead(final OfflinePlayer p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(p);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getHead(final Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(p);
        item.setItemMeta(meta);
        return item;
    }

    private String format(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static BiMap<Player, OfflinePlayer> getToPunish() {
        return toPunish;
    }

    public static BiMap<Player, PunishmentType> getPunishmentTypeBiMap() {
        return punishmentTypeBiMap;
    }
}
