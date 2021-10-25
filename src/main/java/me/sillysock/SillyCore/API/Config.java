package me.sillysock.SillyCore.API;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Config {

    private static final FileConfiguration config = SillyCore.getConfiguration();
    private static final FileConfiguration langYml = SillyCore.getLangYml();
    private static final FileConfiguration permissionsYml = SillyCore.getPermissionsYml();
    private static final File langFile = SillyCore.getLangFile();
    private static final File permissionsFile = SillyCore.getPermissionsFile();

    private static String startupMessage;
    private static String joinMessage;
    private static String quitMessage;
    private static String noPermission;
    private static String notNicked;
    private static String opCommandDisabled;
    private static String nicknameInsufficientArguments;
    private static String realnameInsufficientArguments;
    private static String doesNotExistOrIsOffline;

    public static void setConfigValues() {
        startupMessage = langYml.getString("startup_message");
        joinMessage = langYml.getString("join_message");
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
}
