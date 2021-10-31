package me.sillysock.SillyCore.API.Configuration.PlayerData;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class DataHandler {

    File player;
    private static final File dataFolder = SillyCore.getSillyDataFolder();
    private static HashMap<UUID, FileConfiguration> playerData = new HashMap<>();

    public boolean playerExists(final UUID uuid) {
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        if (!player.exists()) return false;
        return true;
    }

    public void createPlayer(final UUID uuid) {
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
        try {
            fileConfiguration.save(player);
        } catch (IOException e) {
            SillyCore.getLog().severe("Something went wrong when saving data to a player file. (UUID: " + uuid.toString() + ")");
        }

        playerData.put(uuid, fileConfiguration);
    }

    public String getFromPlayer(final UUID uuid, final String path) {
        player = new File(dataFolder, "PlayerData/" + uuid + ".yml");
        return playerData.get(Bukkit.getPlayer(uuid)).getString(path);
    }

    public FileConfiguration getPlayer(final UUID uuid) {
        return YamlConfiguration.loadConfiguration(new File(dataFolder, "PlayerData/" + uuid + ".yml"));
    }

    public boolean isNicked(final UUID uuid) {
        return !playerData.get(uuid).getString("nick").equals("") ? true : false;
    }

    public String getNick(final UUID uuid) {
        if (!isNicked(uuid)) return "";
        return playerData.get(Bukkit.getPlayer(uuid)).getString("nick");
    }
}
