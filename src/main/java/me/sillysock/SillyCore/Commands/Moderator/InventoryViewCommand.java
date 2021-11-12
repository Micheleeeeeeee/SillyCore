package me.sillysock.SillyCore.Commands.Moderator;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryViewCommand implements CommandExecutor {

    private Player p;
    private Player target;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        if (!p.hasPermission("sillycore.moderator.invview")) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("Please enter a player.");
            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.getDoesNotExistOrIsOffline());
            return true;
        }

        p.openInventory(target.getInventory());

        return false;
    }
}
