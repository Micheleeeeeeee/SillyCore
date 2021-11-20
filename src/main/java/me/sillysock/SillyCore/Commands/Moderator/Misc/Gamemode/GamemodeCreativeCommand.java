package me.sillysock.SillyCore.Commands.Moderator.Misc.Gamemode;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCreativeCommand implements CommandExecutor {

    private Player target;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission("sillycore.misc.gamemodeCREATIVE")) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(MessageUtils.format("Your gamemode has been changed to &a&lCREATIVE."));
            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Lang.getDoesNotExistOrIsOffline());
            return true;
        }

        target.setGameMode(GameMode.CREATIVE);
        target.sendMessage("Your gamemode has been changed to &c&lCREATIVE.");
        p.sendMessage(target.getName() + "'s gamemode has been changed to &c&lCREATIVE.");

        return false;
    }
}
