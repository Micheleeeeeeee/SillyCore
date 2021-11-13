package me.sillysock.SillyCore.API.Configuration.PlayerData;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class DataHandler {

    private File player;
    private static final File dataFolder = SillyCore.getSillyDataFolder();
    private static HashMap<UUID, FileConfiguration> playerData = new HashMap<>();
    private static final SillyCore core = SillyCore.getInstance();

    public boolean playerExists(final UUID uuid) {
        if (uuid == null) return false;
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        if (!player.exists()) return false;
        return true;
    }

    public void createPlayer(final UUID uuid) {
        if (uuid == null) return;
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        try {
            player.createNewFile();
        } catch (IOException e) {
            SillyCore.getLog().severe("Something went wrong when creating a data file.");
        }

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(player);
        Bukkit.broadcastMessage(String.valueOf(fileConfiguration));
        fileConfiguration.set("nick", "");
        fileConfiguration.set("name", Bukkit.getPlayer(uuid).getName());
        fileConfiguration.set("muted", false);
        fileConfiguration.set("vanished", false);

        fileConfiguration.set("punishments.muted.is_muted", false);
        fileConfiguration.set("punishments.muted.reason", "");
        fileConfiguration.set("punishments.muted.expiry", 0);
        fileConfiguration.set("punishments.banned.is_banned", false);
        fileConfiguration.set("punishments.banned.reason", "");
        fileConfiguration.set("punishments.banned.expiry", 0);
        fileConfiguration.set("punishments.warns", "");
        fileConfiguration.set("punishments.kicks", "");
        try {
            fileConfiguration.save(player);
        } catch (IOException e) {
            SillyCore.getLog().severe("Something went wrong when saving data to a player file. (UUID: " + uuid.toString() + ")");
        }

        playerData.put(uuid, fileConfiguration);
    }

    public String getFromPlayer(final UUID uuid,
                                final String path) {
        if (uuid == null) return "Invalid UUID";
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        return playerData.get(Bukkit.getPlayer(uuid)).getString(path);
    }

    public FileConfiguration getPlayer(final UUID uuid) {
        return YamlConfiguration.loadConfiguration(new File(dataFolder, "PlayerData/" + uuid + ".yml"));
    }

    public void mute(final UUID uuid,
                     final String reason,
                     long expiry) {
        if (uuid == null) return;
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(player);

        Bukkit.getScheduler().runTaskAsynchronously(core, () -> {
            try {
                config.set("punishments.muted.is_muted", true);
                config.set("punishments.muted.reason", reason);
                config.set("punishments.muted.expiry", expiry);

                config.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setMuted(final UUID uuid,
                         final boolean muted) {
        if (uuid == null) return;
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(player);

        Bukkit.getScheduler().runTaskAsynchronously(core, () -> {
            try {
                config.set("muted", muted);
                config.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean isMuted(final UUID uuid) {
        if (uuid == null) return false;
        final boolean[] out = new boolean[1];
        out[0] = loadConfig(uuid).getBoolean("punishments.muted.is_muted");
        return out[0];
    }

    public boolean isNicked(final UUID uuid) {
        if (uuid == null) return false;
        final boolean[] out = new boolean[1];
        Bukkit.getScheduler().runTaskAsynchronously(core,
                () -> out[0] = !loadConfig(uuid)
                        .getString("nick")
                        .equals(""));
        return out[0];
    }

    public FileConfiguration loadConfig(final UUID uuid) {
        if (uuid == null) return null;
        return YamlConfiguration.loadConfiguration(new File(dataFolder, "PlayerData/" + uuid + ".yml"));
    }

    public void setNick(final UUID uuid,
                        final String nick) {
        if (uuid == null) return;
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(player);
        Bukkit.getPlayer(uuid).setDisplayName(nick);

        Bukkit.getScheduler().runTaskAsynchronously(core, () -> {
            try {
                config.set("nick", nick);
                config.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String getNick(final UUID uuid) {
        if (uuid == null) return "Invalid UUID";
        if (!isNicked(uuid)) return "Not Nicked";
        final String[] out = new String[1];
        Bukkit.getScheduler().runTaskAsynchronously(core, () -> out[0] = loadConfig(uuid)
                .getString("nick"));
        return out[0];
    }
}
