package me.sillysock.SillyCore.API.Configuration;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import java.util.List;

public final class Config {

    private static FileConfiguration config;
    private static File playerDataFolder;

    private static List<String> nicknameBadWords;
    private static String storage;
    private static boolean opDisabled;

    public static void setValues() {
        config = SillyCore.getInstance().getConfig();

        setNicknameBadWords(config.getStringList("nickname_bad_words"));
        storage = config.getString("storage");
        opDisabled = config.getBoolean("disable_op");

        Lang.setValues();
        Permissions.setValues();
    }

    public static void clear() {
        Lang.clear();
        Permissions.clear();
        config = null;
        setConfig(null);
        setNicknameBadWords(null);
    }

    public static List<String> getNicknameBadWords() {
        return nicknameBadWords;
    }

    public static String getStorage() {
        return storage;
    }

    public static void setConfig(FileConfiguration config) {
        Config.config = config;
    }

    public static void setNicknameBadWords(List<String> nicknameBadWords) {
        Config.nicknameBadWords = nicknameBadWords;
    }

    public static void setStorage(String storage) {
        Config.storage = storage;
    }

    public static void setOpDisabled(boolean opDisabled) {
        Config.opDisabled = opDisabled;
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static boolean isOpDisabled() {
        return opDisabled;
    }
}
