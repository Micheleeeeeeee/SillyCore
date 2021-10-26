package me.sillysock.SillyCore.Commands.Member;

import me.sillysock.SillyCore.API.Config;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class FeedCommand
        implements CommandExecutor {

    private Player p;
    private Player target;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        if (!p.hasPermission("sillycore.feed.todo")) {
            p.sendMessage(Config.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.setFoodLevel(20);
            p.sendMessage(ChatColor.GRAY + "You have been fed. (TODO)");

            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Config.getDoesNotExistOrIsOffline());
            return true;
        }

        target.setFoodLevel(20);
        target.sendMessage("You have been fed.");
        p.sendMessage("You have fed " + args[0]);

        return false;
    }
}
