package me.sillysock.SillyCore.Commands.Moderator.Misc;

import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class GamemodeCommand implements CommandExecutor {

    private Player target;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {
        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission("")) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            switch (p.getGameMode()) {
                case SURVIVAL:
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage("Your gamemode has been changed to CREATIVE.");
                case ADVENTURE:
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage("Your gamemode has been changed to SURVIVAL.");
                case CREATIVE:
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage("Your gamemode has been changed to SURVIVAL.");
                case SPECTATOR:
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage("Your gamemode has been changed to SURVIVAL.");
            }
            return true;
        }

        if (args.length == 1) {
            changeGamemode(p, args[0]);
            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        changeGamemode(target, args[1], p);

        return false;
    }

    private void changeGamemode(final Player p, final String mode) {
        switch (mode.toLowerCase(Locale.ENGLISH)) {
            case "survival":
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage("Your gamemode has been changed to SURVIVAL.");
            case "adventure":
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage("Your gamemode has been changed to ADVENTURE.");
            case "creative":
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage("Your gamemode has been changed to CREATIvE.");
            case "spectator":
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage("Your gamemode has been changed to SPECTATOR.");
            default:

        }
    }

    private void changeGamemode(final Player target, final String mode,
                                final Player p) {
        String name = target.getName();
        switch (mode.toLowerCase(Locale.ENGLISH)) {
            case "survival":
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage("Your gamemode has been changed to SURVIVAL.");
                p.sendMessage("You have changed " + name + "'s gamemode to SURVIVAL.");
            case "adventure":
                target.setGameMode(GameMode.ADVENTURE);
                target.sendMessage("Your gamemode has been changed to ADVENTURE.");
                p.sendMessage("You have changed " + name + "'s gamemode to ADVENTURE.");
            case "creative":
                target.setGameMode(GameMode.CREATIVE);
                target.sendMessage("Your gamemode has been changed to CREATIVE.");
                p.sendMessage("You have changed " + name + "'s gamemode to CREATIVE.");
            case "spectator":
                target.setGameMode(GameMode.SPECTATOR);
                target.sendMessage("Your gamemode has been changed to SPECTATOR.");
                p.sendMessage("You have changed " + name + "'s gamemode to SPEPCTATOR.");
            default:
                p.sendMessage("Invalid Args (gamemode)");
        }
    }
}
