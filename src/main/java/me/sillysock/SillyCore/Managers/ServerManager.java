package me.sillysock.SillyCore.Managers;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class ServerManager
        implements CommandExecutor {

    private Player p;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        if (!p.hasPermission("sillycore.admin.manage.reloadconfig")) {
            p.sendMessage(SillyCore.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("Usage: /server <reloadconfig>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reloadconfig")) {
            p.sendMessage("Reloading config values...");
            SillyCore.refreshConfigValues();
            p.sendMessage("Config values refreshed.");

            return true;
        }

        return false;
    }
}
