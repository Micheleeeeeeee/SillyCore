package me.sillysock.SillyCore.API.Configuration;

import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;

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
    private static String nameReset;
    private static String flyOn;
    private static String flyOff;
    private static String flyOtherOn;
    private static String flyOtherOff;
    private static String youHaveKicked;
    private static String typePunishmentReason;
    private static String kickInvalidArg;
    private static String banInvalidArg;
    private static String warnInvalidArg;
    private static String muteInvalidArg;

    private static String teleported;
    private static String teleportedToSelf;
    private static String teleportInvalidArg;

    private static String broadcast;
    private static String broadcastNoArgs;

    private static String invviewInvalidArgs;

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
        setFlyOn(lang.getString("fly_on"));
        setFlyOff(lang.getString("fly_off"));
        setFlyOtherOff(lang.getString("fly_other_off"));
        setFlyOtherOn(lang.getString("fly_other_on"));
        setNameReset(lang.getString("name_reset"));
        setYouHaveKicked(lang.getString("kicked"));
        setTypePunishmentReason(lang.getString("type_punishment_reason"));
        setKickInvalidArg(lang.getString("kick_invalidarg"));
        setTeleported(lang.getString("teleported"));
        setTeleportedToSelf(lang.getString("teleported_to_self"));
        setTeleportInvalidArg(lang.getString("teleport_invalidarg"));
        setBanInvalidArg(lang.getString("ban_invalidarg"));
        setMuteInvalidArg(lang.getString("mute_invalidarg"));
        setBroadcast(lang.getString("broadcast"));
        setBroadcastNoArgs(lang.getString("broadcast_invalidarg"));
        setInvviewInvalidArgs(lang.getString("invview_invalidarg"));
    }

    public static void clear() {
        nickSuccess = null;
        realnameSuccess = null;
        prefix = null;
        doesNotExistOrIsOffline = null;
        realnameInsufficientArguments = null;
        nicknameInsufficientArguments = null;
        opCommandDisabled = null;
        notNicked = null;
        noPermission = null;
        quitMessage = null;
        joinMessage = null;
        startupMessage = null;
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
        Lang.nickSuccess = MessageUtils.format(nickSuccess);
    }

    public static String getNameReset() {
        return nameReset;
    }

    public static void setNameReset(String nameReset) {
        Lang.nameReset = MessageUtils.format(nameReset);
    }

    public static String getFlyOn() {
        return flyOn;
    }

    public static void setFlyOn(String flyOn) {
        Lang.flyOn = MessageUtils.format(flyOn);
    }

    public static String getFlyOff() {
        return flyOff;
    }

    public static void setFlyOff(String flyOff) {
        Lang.flyOff = MessageUtils.format(flyOff);
    }

    public static String getFlyOtherOn() {
        return flyOtherOn;
    }

    public static void setFlyOtherOn(String flyOtherOn) {
        Lang.flyOtherOn = MessageUtils.format(flyOtherOn);
    }

    public static String getFlyOtherOff() {
        return flyOtherOff;
    }

    public static void setFlyOtherOff(String flyOtherOff) {
        Lang.flyOtherOff = MessageUtils.format(flyOtherOff);
    }

    public static String getTeleportInvalidArg() {
        return teleportInvalidArg;
    }

    public static void setTeleportInvalidArg(String teleportInvalidArg) {
        Lang.teleportInvalidArg = MessageUtils.format(teleportInvalidArg);
    }

    public static String getYouHaveKicked() {
        return youHaveKicked;
    }

    public static void setYouHaveKicked(String youHaveKicked) {
        Lang.youHaveKicked = MessageUtils.format(youHaveKicked);
    }

    public static String getTypePunishmentReason() {
        return typePunishmentReason;
    }

    public static void setTypePunishmentReason(String typePunishmentReason) {
        Lang.typePunishmentReason = MessageUtils.format(typePunishmentReason);
    }

    public static String getBroadcastNoArgs() {
        return broadcastNoArgs;
    }

    public static void setBroadcastNoArgs(String broadcastNoArgs) {
        Lang.broadcastNoArgs = MessageUtils.format(broadcastNoArgs);
    }

    public static String getKickInvalidArg() {
        return kickInvalidArg;
    }

    public static void setKickInvalidArg(String kickInvalidArg) {
        Lang.kickInvalidArg = MessageUtils.format(kickInvalidArg);
    }

    public static String getTeleported() {
        return teleported;
    }

    public static void setTeleported(String teleported) {
        Lang.teleported = MessageUtils.format(teleported);
    }

    public static String getTeleportedToSelf() {
        return teleportedToSelf;
    }

    public static void setTeleportedToSelf(String teleportedToSelf) {
        Lang.teleportedToSelf = MessageUtils.format(teleportedToSelf);
    }

    public static String getBanInvalidArg() {
        return banInvalidArg;
    }

    public static void setBanInvalidArg(String banInvalidArg) {
        Lang.banInvalidArg = banInvalidArg;
    }

    public static String getWarnInvalidArg() {
        return warnInvalidArg;
    }

    public static void setWarnInvalidArg(String warnInvalidArg) {
        Lang.warnInvalidArg = warnInvalidArg;
    }

    public static String getMuteInvalidArg() {
        return muteInvalidArg;
    }

    public static void setMuteInvalidArg(String muteInvalidArg) {
        Lang.muteInvalidArg = muteInvalidArg;
    }

    public static String getBroadcast() {
        return broadcast;
    }

    public static void setBroadcast(String broadcast) {
        Lang.broadcast = broadcast;
    }

    public static String getInvviewInvalidArgs() {
        return invviewInvalidArgs;
    }

    public static void setInvviewInvalidArgs(String invviewInvalidArgs) {
        Lang.invviewInvalidArgs = invviewInvalidArgs;
    }

    public static String formatBroadcast(final String message) {
        return MessageUtils.format(getBroadcast().replace("{announcement}", message));
    }

    public static String formatRealnameSuccess(final String name, String nickName) {
        return MessageUtils.format(getRealnameSuccess()
                .replace("{realname}", name)
                .replace("{nickname}", nickName));
    }

    public static String formatNickSuccess(final String nick) {
        return MessageUtils.format(getNickSuccess()
                .replace("{nickname}", nick));
    }

    public static String formatNameReset(final String name) {
        return MessageUtils.format(getNameReset()
                .replace("{name}", name));
    }

    public static String formatFlyOtherOn(final String name) {
        return MessageUtils.format(getFlyOtherOn()
                .replace("{name}", name));
    }

    public static String formatFlyOtherOff(final String name) {
        return MessageUtils.format(getFlyOtherOff()
                .replace("{name}", name));
    }

    public static String formatYouHaveKicked(final String name) {
        return MessageUtils.format(getYouHaveKicked()
                .replace("{name}", name));
    }

    public static String formatTeleported(final String name) {
        return MessageUtils.format(getTeleported()
                .replace("{name}", name));
    }

    public static String formatTeleportedToSelf(final String name) {
        return MessageUtils.format(getTeleportedToSelf()
                .replace("{name}", name));
    }
}
