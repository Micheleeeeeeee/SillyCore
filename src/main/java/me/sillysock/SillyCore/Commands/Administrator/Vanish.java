package me.sillysock.SillyCore.Commands.Administrator;

import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Level;

public class Vanish
    implements CommandExecutor {

    Player p;
    private ArrayList<Player> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        if (!p.hasPermission("sillycore.admin.vanish")) {
            p.sendMessage(ChatColor.RED + "You do not have the required permissions to execute this command.");
            return true;
        }

        if (vanished.contains(p)) {
            for (final Player pl : Bukkit.getOnlinePlayers()) {
                pl.showPlayer(p);
            }
            p.sendMessage(ChatColor.GREEN + "You are " + ChatColor.YELLOW + "no longer " + ChatColor.GREEN + "vanished.");
            vanished.remove(p);

        } else {
            for (final Player pl : Bukkit.getOnlinePlayers()) {
                if (!pl.hasPermission(Permissions.getSeeVanished())) pl.hidePlayer(p);
            }
            p.sendMessage(ChatColor.GREEN + "You are " + ChatColor.YELLOW + "now " + ChatColor.GREEN + "vanished.");
            vanished.add(p);

        }
        return true;
    }
}
