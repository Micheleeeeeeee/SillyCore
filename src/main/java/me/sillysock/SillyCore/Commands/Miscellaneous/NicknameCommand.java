package me.sillysock.SillyCore.Commands.Miscellaneous;

import com.google.common.collect.BiMap;
import me.sillysock.SillyCore.API.Configuration.Config;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TraderLlama;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class NicknameCommand
        implements CommandExecutor {

    BiMap<Player, String> nicknamedPlayers = SillyCore.getNicknameManager().getNicknamedPlayers();
    private static final DataHandler handler = SillyCore.getDataHandler();

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
        final UUID uuid = p.getUniqueId();


        if (args.length < 0x1) { // i was bored (0x1 = 1 as 16^0 = 1)
            p.sendMessage(Lang.getNicknameInsufficientArguments());
            return true;
        }

        String nickname = args[0];
        if (nickname.equalsIgnoreCase("reset")) {
            if (handler.isNicked(uuid)) {
                p.setDisplayName(name);
                p.setPlayerListName(name);
                handler.setNick(uuid, "");

                p.sendMessage(Lang.formatNameReset(name));
                return true;
            }
        }

        for (final String badWord : Config.getNicknameBadWords()) {
            if (nickname.contains(badWord)) {
                p.sendMessage("That's a bad word!");
                return true;
            }
        }

        handler.setNick(uuid, nickname);
        p.setDisplayName(nickname);
        p.setPlayerListName(nickname);
        p.sendMessage(Lang.formatNickSuccess(nickname));

        return false;
    }
}
