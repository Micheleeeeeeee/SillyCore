package me.sillysock.SillyCore.API.Configuration;

import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.API.Util.SillyPlayer;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Lang {

    private static FileConfiguration lang;
    private static File langFile;
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
    private static String realnameSuccess;
    private static String nickSuccess;

    private static String prefix;

    public static void setValues() {
        dataFolder = SillyCore.getSillyDataFolder();
        langFile = new File(dataFolder, "lang.yml");
        if (!langFile.exists()) SillyCore.getInstance().saveResource("lang.yml", false);

        lang = YamlConfiguration.loadConfiguration(langFile);

        setStartupMessage(lang.getString("startup"));
        setJoinMessage(lang.getString("join"));
        setQuitMessage(lang.getString("quit"));
        setNoPermission(lang.getString("no_permission"));
        setNotNicked(lang.getString("not_nicked"));
        setOpCommandDisabled(lang.getString("operator_command_disabled"));
        setNicknameInsufficientArguments(lang.getString("nick_invalidarg"));
        setRealnameInsufficientArguments(lang.getString("realname_invalidarg"));
        setDoesNotExistOrIsOffline(lang.getString("target_offline"));
        setPrefix(lang.getString("prefix"));
        setRealnameSuccess(lang.getString("realname"));
        setNickSuccess(lang.getString("player_nicked"));
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
        Lang.startupMessage = MessageUtils.format(startupMessage);
    }

    public static String getJoinMessage() {
        return joinMessage;
    }

    public static void setJoinMessage(String joinMessage) {
        Lang.joinMessage = MessageUtils.format(joinMessage);
    }

    public static String getQuitMessage() {
        return quitMessage;
    }

    public static void setQuitMessage(String quitMessage) {
        Lang.quitMessage = MessageUtils.format(quitMessage);
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static void setNoPermission(String noPermission) {
        Lang.noPermission = MessageUtils.format(noPermission);
    }

    public static String getNotNicked() {
        return notNicked;
    }

    public static void setNotNicked(String notNicked) {
        Lang.notNicked = MessageUtils.format(notNicked);
    }

    public static String getOpCommandDisabled() {
        return opCommandDisabled;
    }

    public static void setOpCommandDisabled(String opCommandDisabled) {
        Lang.opCommandDisabled = MessageUtils.format(opCommandDisabled);
    }

    public static String getNicknameInsufficientArguments() {
        return nicknameInsufficientArguments;
    }

    public static void setNicknameInsufficientArguments(String nicknameInsufficientArguments) {
        Lang.nicknameInsufficientArguments = MessageUtils.format(nicknameInsufficientArguments);
    }

    public static String getRealnameInsufficientArguments() {
        return realnameInsufficientArguments;
    }

    public static void setRealnameInsufficientArguments(String realnameInsufficientArguments) {
        Lang.realnameInsufficientArguments = MessageUtils.format(realnameInsufficientArguments);
    }

    public static String getDoesNotExistOrIsOffline() {
        return doesNotExistOrIsOffline;
    }

    public static void setDoesNotExistOrIsOffline(String doesNotExistOrIsOffline) {
        Lang.doesNotExistOrIsOffline = MessageUtils.format(doesNotExistOrIsOffline);
    }

    public static String getRealnameSuccess() {
        return realnameSuccess;
    }

    public static void setRealnameSuccess(String realnameSuccess) {
        Lang.realnameSuccess = MessageUtils.format(realnameSuccess);
    }

    public static String formatRealnameSuccess(final String name, String nickName) {
        return MessageUtils.format(getRealnameSuccess().replace("{realname}", name)
                .replace("{nickname}", nickName));
    }

    public static String formatNickSuccess(final String nick) {
        return MessageUtils.format(getNickSuccess().replace("{nickname}", nick));
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Lang.prefix = MessageUtils.format(prefix);
    }

    public static String getNickSuccess() {
        return nickSuccess;
    }

    public static void setNickSuccess(String nickSuccess) {
        Lang.nickSuccess = nickSuccess;
    }
}
