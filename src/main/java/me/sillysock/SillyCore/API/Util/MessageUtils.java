package me.sillysock.SillyCore.API.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageUtils {

    public static String format(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sendToAll(final String msg) {
        Bukkit
                .getOnlinePlayers()
                .forEach(player -> player.sendMessage(msg));
    }
}
