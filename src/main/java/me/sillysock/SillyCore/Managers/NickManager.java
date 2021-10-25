package me.sillysock.SillyCore.Managers;

import com.google.common.collect.BiMap;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class NickManager {

    HashMap<String, Player> nicknamedPlayers;
    BiMap<Player, String> test;

    boolean isNicked(final Player p) {
        return nicknamedPlayers.containsKey(p);
    }

    String getRealname(final Player p) {
        if (isNicked(p)) return nicknamedPlayers.get(p).getName();
        return "Not Nicked";
    }

    public NickManager() {
        nicknamedPlayers = new HashMap<>();
    }

    public HashMap<String, Player> getNicknamedPlayers() {
        return nicknamedPlayers;
    }
}
