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

public class FlyCommand implements CommandExecutor {

    private Player target;

    @Override public boolean onCommand(final CommandSender sender, final Command cmd,
                                    final String label, final String[] args) {
        if (!(sender instanceof Player p)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getFly())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            if (p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.sendMessage(Lang.getFlyOff());
            } else {
                p.setAllowFlight(true);
                p.sendMessage(Lang.getFlyOn());

            }
            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.getDoesNotExistOrIsOffline());
            return true;
        }

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.sendMessage(Lang.getFlyOff());
            p.sendMessage(Lang.formatFlyOtherOff(target.getName()));
        } else {
            target.setAllowFlight(true);
            target.sendMessage(Lang.getFlyOn());
            p.sendMessage(Lang.formatFlyOtherOn(target.getName()));
        }

        return false;
    }
}
