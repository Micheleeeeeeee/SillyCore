package me.sillysock.SillyCore.Commands.Moderator.Punishment.Kick;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;

public class KickCommand implements CommandExecutor {

    private static final HashMap<Player, Player> typingReason = new HashMap<>();
    private Player target;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {
        if (!(sender instanceof Player p)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getKick())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("todo no args");
            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.getDoesNotExistOrIsOffline());
            return true;
        }

        p.sendMessage("Please type the reason for the punishment in chat.");
        typingReason.put(p, target);

        return false;
    }

    public static HashMap<Player, Player> getTypingReason() {
        return typingReason;
    }
}
