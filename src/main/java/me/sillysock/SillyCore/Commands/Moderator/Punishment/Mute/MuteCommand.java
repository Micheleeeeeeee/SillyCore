package me.sillysock.SillyCore.Commands.Moderator.Punishment.Mute;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.Permissions;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.Menus.MenuApi;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.API.PunishmentType;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.nio.charset.CoderResult;
import java.util.UUID;

public class MuteCommand implements CommandExecutor {

    private static final BiMap<Player, OfflinePlayer> typingReason = HashBiMap.create();
    private final MenuApi menuApi = SillyCore.getMenuApi();
    private OfflinePlayer target;

    /**
     * When the /mute command is run, the below code is executed.
     * @param sender
     * @param cmd
     * @param label
     * @param args
     * @return
     */

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
                             final String label, final String[] args) {
        if (!(sender instanceof Player p)) {
            SillyCore.getLog().severe("Only players may execute this command.");
            return true;
        }

        if (!p.hasPermission(Permissions.getMute())) {
            p.sendMessage(Lang.getNoPermission());
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(Lang.getMuteInvalidArg());
            return true;
        }

        target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.hasPlayedBefore()) {
            p.sendMessage(Lang.getDoesNotExistOrIsOffline());
            return true;
        }

        MenuApi.getPunishmentTypeBiMap().put(p, PunishmentType.MUTE);
        MenuApi.getToPunish().put(p, target);

        p.openInventory(menuApi.createMuteMenu(target));

        return false;
    }

    public static BiMap<Player, OfflinePlayer> getTypingReason() {
        return typingReason;
    }
}
