package me.sillysock.SillyCore.API.Configuration;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Lang {

    private static FileConfiguration lang;
    private static File langFile;
    private static final File dataFolder = SillyCore.getSillyDataFolder();

    private static String startupMessage;
    private static String joinMessage;
    private static String quitMessage;
    private static String noPermission;
    private static String notNicked;
    private static String opCommandDisabled;
    private static String nicknameInsufficientArguments;
    private static String realnameInsufficientArguments;
    private static String doesNotExistOrIsOffline;

    public void setValues() {
        langFile = new File(dataFolder, "lang.yml");
        if (!langFile.exists()) SillyCore.getInstance().saveResource("lang.yml", false);

        lang = YamlConfiguration.loadConfiguration(langFile);

        setStartupMessage(lang.getString("startup_message"));
        setJoinMessage(lang.getString("join_message"));
        setQuitMessage(lang.getString("quit_message"));
        setNoPermission(lang.getString("no_permission"));
    }

    public static FileConfiguration getLang() {
        return lang;
    }

    public static void setLang(FileConfiguration lang) {
        Lang.lang = lang;
    }

    public static File getLangFile() {
        return langFile;
    }

    public static void setLangFile(File langFile) {
        Lang.langFile = langFile;
    }

    public static File getDataFolder() {
        return dataFolder;
    }

    public static String getStartupMessage() {
        return startupMessage;
    }

    public static void setStartupMessage(String startupMessage) {
        Lang.startupMessage = startupMessage;
    }

    public static String getJoinMessage() {
        return joinMessage;
    }

    public static void setJoinMessage(String joinMessage) {
        Lang.joinMessage = joinMessage;
    }

    public static String getQuitMessage() {
        return quitMessage;
    }

    public static void setQuitMessage(String quitMessage) {
        Lang.quitMessage = quitMessage;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static void setNoPermission(String noPermission) {
        Lang.noPermission = noPermission;
    }

    public static String getNotNicked() {
        return notNicked;
    }

    public static void setNotNicked(String notNicked) {
        Lang.notNicked = notNicked;
    }

    public static String getOpCommandDisabled() {
        return opCommandDisabled;
    }

    public static void setOpCommandDisabled(String opCommandDisabled) {
        Lang.opCommandDisabled = opCommandDisabled;
    }

    public static String getNicknameInsufficientArguments() {
        return nicknameInsufficientArguments;
    }

    public static void setNicknameInsufficientArguments(String nicknameInsufficientArguments) {
        Lang.nicknameInsufficientArguments = nicknameInsufficientArguments;
    }

    public static String getRealnameInsufficientArguments() {
        return realnameInsufficientArguments;
    }

    public static void setRealnameInsufficientArguments(String realnameInsufficientArguments) {
        Lang.realnameInsufficientArguments = realnameInsufficientArguments;
    }

    public static String getDoesNotExistOrIsOffline() {
        return doesNotExistOrIsOffline;
    }

    public static void setDoesNotExistOrIsOffline(String doesNotExistOrIsOffline) {
        Lang.doesNotExistOrIsOffline = doesNotExistOrIsOffline;
    }
}
