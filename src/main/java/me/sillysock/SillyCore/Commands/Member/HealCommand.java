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

public class HealCommand implements CommandExecutor {

    private Player target;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getHeal())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.setHealthScale(20);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setFireTicks(0);
            p.setVisualFire(false);
            p.sendMessage("You have been healed. (todo in lang)");

            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.getDoesNotExistOrIsOffline());
            return true;
        }

        target.setHealth(20);
        target.setHealthScale(20);
        target.setFoodLevel(20);
        target.setFireTicks(0);
        target.setVisualFire(false);
        target.sendMessage("You have been healed. (todo in lang)");
        p.sendMessage("You have healed %s", target.getName());

        return false;
    }

}
