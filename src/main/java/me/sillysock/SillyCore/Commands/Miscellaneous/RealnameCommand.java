package me.sillysock.SillyCore.Commands.Miscellaneous;

import com.google.common.collect.BiMap;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;

public class RealnameCommand
        implements CommandExecutor {

    Player p;
    Player target;
    BiMap<Player, String> nickedPlayers = SillyCore.getNicknameManager().getNicknamedPlayers();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        if (args.length < 1) {
            p.sendMessage(Lang.getNicknameInsufficientArguments());
            return true;
        }

        target = nickedPlayers.inverse().get(args[0]);

        if (target == null) {
            p.sendMessage(Lang.getNotNicked());
            return true;
        }

        p.sendMessage(Lang.formatRealnameSuccess(target.getName(), target.getDisplayName()));

        return false;
    }
}
