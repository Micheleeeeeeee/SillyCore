package me.sillysock.SillyCore.Commands.Testing;

import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class FloodFileConfigurations implements CommandExecutor {

    private static final DataHandler handler = SillyCore.getDataHandler();

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("players only pls");
            return true;
        }

        p.sendMessage("Beginning.");
        final UUID uuid = p.getUniqueId();

        for (int i = 0; i < 50; i++) {
            p.sendMessage("Changing nick");
            handler.setNick(uuid, String.valueOf(Math.round(Math.random() * 500)));
            p.sendMessage("New Nick: " + handler.getNick(uuid));
            p.performCommand("tps");

            new BukkitRunnable() {
                @Override
                public void run() {
                    cancel();
                }
            }.runTaskTimer(SillyCore.getInstance(), 2, 2);
        }

        return false;
    }
}
