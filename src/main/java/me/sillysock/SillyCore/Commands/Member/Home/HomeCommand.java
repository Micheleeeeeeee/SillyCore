package me.sillysock.SillyCore.Commands.Member.Home;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {
        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission("sillycore.member.home")) {
            p.sendMessage("No Permissions");
            return true;
        }

        return false;
    }
}
