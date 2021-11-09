package me.sillysock.SillyCore.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NickManager {

    BiMap<Player, String> nicknamedPlayers;
    DataHandler handler;

    @Deprecated(since = "1.0-Developer", forRemoval = true)
    boolean isNicked(final Player p) {
        return nicknamedPlayers.containsKey(p);
    }

    @Deprecated(since = "1.0-Developer", forRemoval = true)
    String getRealname(final Player p) {
        if (isNicked(p.getUniqueId())) return p.getName();
        return "Not Nicked";
    }

    @Deprecated(since = "1.0-Developer", forRemoval = true)
    boolean isNicked(final UUID uuid) {
        return handler.isNicked(uuid);
    }

    @Deprecated(since = "1.0-Developer", forRemoval = true)
    public NickManager() {
        nicknamedPlayers = HashBiMap.create();
        handler = SillyCore.getDataHandler();
    }

    @Deprecated(since = "1.0-Developer", forRemoval = true)
    public BiMap<Player, String> getNicknamedPlayers() {
        return nicknamedPlayers;
    }
}
