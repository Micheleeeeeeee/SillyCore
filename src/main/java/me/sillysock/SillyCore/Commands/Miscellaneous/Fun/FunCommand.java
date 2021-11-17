package me.sillysock.SillyCore.Commands.Miscellaneous.Fun;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FunCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission("sillycore.misc.fun")) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0 || args.length == 1 && !args[0].equalsIgnoreCase("confirm")) {
            p.sendMessage(MessageUtils.format("&cType &a/fun confirm&c to run this command."));
            return true;
        }

        final World world = p.getWorld();
        final Location loc = p.getLocation();
        for (int i = 0; i < 100; i++) {
            world.spawnEntity(loc, EntityType.LIGHTNING);
        }

        p.sendMessage(MessageUtils.format("&cThou hast been smiten!"));

        return false;
    }
}
