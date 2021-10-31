package me.sillysock.SillyCore.Commands.Member;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class TeleportCommand
    implements CommandExecutor {

    private Player target;
    private Player targetTwo;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player p)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getTeleport())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(Lang.getTeleportInvalidArg());
            return true;
        } else if (args.length == 1) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(Lang.getDoesNotExistOrIsOffline());
                return true;
            }

            p.teleport(target);
            p.sendMessage(Lang.formatTeleported(target.getName()));
        } else {
            target = Bukkit.getPlayer(args[0]);
            targetTwo = Bukkit.getPlayer(args[1]);

            if (target == null || targetTwo == null) {
                p.sendMessage(Lang.getDoesNotExistOrIsOffline());
                return true;
            }

            target.teleport(targetTwo);
            target.sendMessage(Lang.formatTeleported(targetTwo.getName()));
            targetTwo.sendMessage(Lang.formatTeleportedToSelf(target.getName()));
            return true;
        }

        return false;
    }
}