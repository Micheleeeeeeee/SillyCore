package me.sillysock.SillyCore.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class NickManager {

    BiMap<Player, String> nicknamedPlayers;

    boolean isNicked(final Player p) {
        return nicknamedPlayers.containsKey(p);
    }

    String getRealname(final Player p) {
        if (isNicked(p)) return nicknamedPlayers.inverse().get(p).getName();
        return "Not Nicked";
    }

    public NickManager() {
        nicknamedPlayers = HashBiMap.create();
    }

    public BiMap<Player, String> getNicknamedPlayers() {
        return nicknamedPlayers;
    }
}
