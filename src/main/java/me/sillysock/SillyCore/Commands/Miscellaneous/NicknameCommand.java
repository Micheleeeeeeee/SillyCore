package me.sillysock.SillyCore.Commands.Miscellaneous;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TraderLlama;

import java.util.HashMap;
import java.util.logging.Level;

public class NicknameCommand
        implements CommandExecutor {

    HashMap<String, Player> nicknamedPlayers = SillyCore.getNicknameManager().getNicknamedPlayers();

    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getNick())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        String name = p.getName();

        if (args.length < 0x1) { // i was bored (0x1 = 1 as 16^0 = 1)
            p.sendMessage(Lang.getNicknameInsufficientArguments());
            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            if (nicknamedPlayers.containsKey(p)) {
                nicknamedPlayers.remove(p);
                p.setDisplayName(name);
                p.setPlayerListName(name);

                p.sendMessage(Lang.formatNameReset(name));
                return true;
            }
        }

        String nickname = args[0];
        if (nicknamedPlayers.containsKey(p.getDisplayName())) nicknamedPlayers.remove(p.getDisplayName());
        nicknamedPlayers.put(nickname, p);
        p.setDisplayName(nickname);
        p.setPlayerListName(nickname);
        p.sendMessage(Lang.formatNickSuccess(nickname));

        return false;
    }
}
