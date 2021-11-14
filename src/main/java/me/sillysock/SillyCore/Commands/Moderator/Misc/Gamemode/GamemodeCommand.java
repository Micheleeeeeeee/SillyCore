package me.sillysock.SillyCore.Commands.Moderator.Misc.Gamemode;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    private GameMode gamemode;

    @Override
    public boolean onCommand(CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission("sillycore.moderator.gamemode.gm")) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        gamemode = p.getGameMode();
        if (gamemode.equals(GameMode.SURVIVAL)) {
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(MessageUtils.format("Your gamemode has been set to &a&lCREATIVE."));
        }
        if (gamemode.equals(GameMode.CREATIVE)) {
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(MessageUtils.format("Your gamemode has been set to &c&lSURVIVAL."));
        }
        if (gamemode.equals(GameMode.ADVENTURE)) {
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(MessageUtils.format("Your gamemode has been set to &c&lSURVIVAL."));
        }
        if (gamemode.equals(GameMode.SPECTATOR)) {
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(MessageUtils.format("Your gamemode has been set to &a&lCREATIVE."));
        }

        return true;
    }
}
