package me.sillysock.SillyCore.Commands.Miscellaneous;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;

public class NicknameCommand
        implements CommandExecutor {

    Player p;
    String name;
    String nickname;
    private static HashMap<Player, String> nicknamedPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        name = p.getName();

        if (args.length < 1) {
            p.sendMessage(SillyCore.getNicknameInsufficientArguments());
            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            if (nicknamedPlayers.containsKey(p)) {
                nicknamedPlayers.remove(p);
                p.setDisplayName(name);
                p.setPlayerListName(name);
            }
        }

        nickname = args[0];
        nicknamedPlayers.put(p, nickname);
        p.sendMessage(ChatColor.GRAY + "Your nickname has been changed to " + ChatColor.YELLOW + nickname + ChatColor.RESET);

        return false;
    }
}
