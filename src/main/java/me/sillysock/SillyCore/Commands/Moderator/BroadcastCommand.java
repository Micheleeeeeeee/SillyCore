package me.sillysock.SillyCore.Commands.Moderator;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BroadcastCommand implements CommandExecutor {

    /**
     * When the /broadcast command is run, the below code is executed.
     *
     * Developed by Silly Sock (c) 2021
     */

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Lang.getBroadcast())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(Lang.getBroadcastNoArgs());
            return true;
        }

        String broadcast = String.join(" ", args);
        Bukkit.broadcastMessage(Lang.formatBroadcast(broadcast));

        return false;
    }
}
