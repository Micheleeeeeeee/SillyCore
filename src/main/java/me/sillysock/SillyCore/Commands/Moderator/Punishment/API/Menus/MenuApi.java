package me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.PunishmentType;
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

    private Inventory punishMenu;

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
        for (final Enchantment enchantment : enchantments) meta.addEnchant(enchantment, 1, true);

        item.setItemMeta(meta);
        inv.setItem(slot, item);
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

    public static BiMap<Player, OfflinePlayer> getToPunish() {
        return toPunish;
    }

    public static BiMap<Player, PunishmentType> getPunishmentTypeBiMap() {
        return punishmentTypeBiMap;
    }
}