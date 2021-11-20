package me.sillysock.SillyCore.Commands.Moderator.Misc.Gamemode;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class GamemodeCommand implements CommandExecutor {

    private GameMode gamemode;
    private Player target;

    @Override
    public boolean onCommand(CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getGamemode())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            gamemode = p.getGameMode();
            if (gamemode.equals(GameMode.SURVIVAL)) {
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(Lang.formatGamemode(GameMode.CREATIVE));
            }
            if (gamemode.equals(GameMode.CREATIVE)) {
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(Lang.formatGamemode(GameMode.SURVIVAL));
            }
            if (gamemode.equals(GameMode.ADVENTURE)) {
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(Lang.formatGamemode(GameMode.SURVIVAL));
            }
            if (gamemode.equals(GameMode.SPECTATOR)) {
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(Lang.formatGamemode(GameMode.SURVIVAL));
            }

            return true;
        }

        try {
            gamemode = GameMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                p.sendMessage(Lang.getDoesNotExistOrIsOffline());
                return true;
            }

            if (args.length != 2) {
                p.sendMessage("Invalid Args");
                return true;
            }

            try {
                gamemode = GameMode.valueOf(args[1].toUpperCase());
            } catch (IllegalArgumentException ex) {
                p.sendMessage("Invalid Gamemode");
            }

            target.setGameMode(gamemode);
            target.sendMessage(Lang.formatGamemode(gamemode));
            p.sendMessage(Lang.formatGamemodeOther(target.getDisplayName(), gamemode));
        }

        return true;
    }
}
