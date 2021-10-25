package me.sillysock.SillyCore.API;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private static FileConfiguration config;
    private static FileConfiguration langYml;
    private static FileConfiguration permissionsYml;
    private static File langFile;
    private static File permissionsFile;

    private static File dataFolder;

    private static String startupMessage;
    private static String joinMessage;
    private static String quitMessage;
    private static String noPermission;
    private static String notNicked;
    private static String opCommandDisabled;
    private static String nicknameInsufficientArguments;
    private static String realnameInsufficientArguments;
    private static String doesNotExistOrIsOffline;

    private static boolean isOpCommandDisabled;

    public static void fileCheck() {
        if (langFile.exists()) return;
        if (permissionsFile.exists()) return;

        SillyCore.getInstance().saveResource("lang.yml", false);
        SillyCore.getInstance().saveResource("permissions.yml", false);
    }

    public static void setConfigValues() {

        config = SillyCore.getInstance().getConfig();
        dataFolder = SillyCore.getInstance().getDataFolder();

        langFile = new File(dataFolder, "lang.yml");
        permissionsFile = new File(dataFolder, "permissions.yml");

        langYml = YamlConfiguration.loadConfiguration(langFile);
        permissionsYml = YamlConfiguration.loadConfiguration(permissionsFile);

        fileCheck();

        setStartupMessage(getStringFromLang("startup_message"));
        setJoinMessage(getStringFromLang("join_message"));
        setNoPermission(getStringFromLang("no_permission"));
        setNotNicked(getStringFromLang("not_nicked"));
    }
    
    public static String getStringFromLang(final String path) {
        String out = langYml.getString(path);
        return out != null ? out : "Invalid Path";
    }

    public static String getStringFromConfig(final String path) {
        String out = config.getString(path);
        return out != null ? out : "Invalid Path";
    }

    public static String getPermission(final String path) {
        String out = permissionsYml.getString(path);
        return out != null ? out : "Invalid Path";
    }

    public static String getStartupMessage() {
        return startupMessage;
    }

    public static void setStartupMessage(String startupMessage) {
        Config.startupMessage = startupMessage;
    }

    public static String getJoinMessage() {
        return joinMessage;
    }

    public static void setJoinMessage(String joinMessage) {
        Config.joinMessage = joinMessage;
    }

    public static String getQuitMessage() {
        return quitMessage;
    }

    public static void setQuitMessage(String quitMessage) {
        Config.quitMessage = quitMessage;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static void setNoPermission(String noPermission) {
        Config.noPermission = noPermission;
    }

    public static String getNotNicked() {
        return notNicked;
    }

    public static void setNotNicked(String notNicked) {
        Config.notNicked = notNicked;
    }

    public static String getOpCommandDisabled() {
        return opCommandDisabled;
    }

    public static void setOpCommandDisabled(String opCommandDisabled) {
        Config.opCommandDisabled = opCommandDisabled;
    }

    public static String getNicknameInsufficientArguments() {
        return nicknameInsufficientArguments;
    }

    public static void setNicknameInsufficientArguments(String nicknameInsufficientArguments) {
        Config.nicknameInsufficientArguments = nicknameInsufficientArguments;
    }

    public static String getRealnameInsufficientArguments() {
        return realnameInsufficientArguments;
    }

    public static void setRealnameInsufficientArguments(String realnameInsufficientArguments) {
        Config.realnameInsufficientArguments = realnameInsufficientArguments;
    }

    public static String getDoesNotExistOrIsOffline() {
        return doesNotExistOrIsOffline;
    }

    public static void setDoesNotExistOrIsOffline(String doesNotExistOrIsOffline) {
        Config.doesNotExistOrIsOffline = doesNotExistOrIsOffline;
    }

    public FileConfiguration getLangYml() {
        return langYml;
    }

    public FileConfiguration getPermissionsYml() {
        return permissionsYml;
    }

    public File getLangFile() {
        return langFile;
    }

    public File getPermissionsFile() {
        return permissionsFile;
    }

    public static boolean isOpCommandEnabled() {
        return isOpCommandDisabled;
    }

    public static void setIsOpCommandDisabled(boolean isOpCommandDisabled) {
        Config.isOpCommandDisabled = isOpCommandDisabled;
    }
}
