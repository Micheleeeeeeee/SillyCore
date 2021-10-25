package me.sillysock.SillyCore.Commands.Miscellaneous;

import me.sillysock.SillyCore.Managers.NickManager;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    HashMap<String, Player> nickedPlayers = SillyCore.getNicknameManager().getNicknamedPlayers();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(SillyCore.getRealnameInsufficientArguments());
            return true;
        }

        if (nickedPlayers.get(args[0]) == null) {
            p.sendMessage(SillyCore.getNotNicked());
            return true;
        }

        p.sendMessage(ChatColor.GRAY + args[0] + "'s real name is " + ChatColor.YELLOW + nickedPlayers.get(args[0]).getName());

        return false;
    }
}
