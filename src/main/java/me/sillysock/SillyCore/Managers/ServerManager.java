package me.sillysock.SillyCore.Managers;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
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
        if (!p.hasPermission(Permissions.getReloadConfig())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("Usage: /server <reloadconfig>");
            return true;
        }

        if (args[0].equalsIgnoreCase("rlconfig")) {
            me.sillysock.SillyCore.API.Configuration.Config.setValues();
            p.sendMessage("Testing new config values.");
            p.sendMessage("join: " + Lang.getJoinMessage());
            p.sendMessage("If this is not correct from your lang.yml please contact me on my resource.");

            return true;
        }

        return false;
    }
}
