package me.sillysock.SillyCore.Commands.Member;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.logging.Level;

public class MemberListCommand implements CommandExecutor {

    Player p;
    StringBuilder memberList;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SillyCore.getLog().log(Level.SEVERE, "Only players may execute this command.");
            return true;
        }

        p = (Player) sender;

        createMemberList(p);

        return false;
    }

    private void createMemberList(Player p) {
        memberList = new StringBuilder();

        memberList.append(ChatColor.GRAY + "Online members: " + ChatColor.RESET);

        for (Player pl : Bukkit.getOnlinePlayers()) {
            memberList.append(pl.getName() + ", ");
        }
        memberList.append((Bukkit.getOnlinePlayers().size() > 1 ? "\nThere are currently " + Bukkit.getOnlinePlayers().size() + " players online" : "There is currently 1 player online."));

        p.sendMessage(memberList.toString());
    }
}
