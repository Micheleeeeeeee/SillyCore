package me.sillysock.SillyCore.API.Configuration;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Permissions {

    // Permissions.yml information
    private static FileConfiguration permissions;
    private static File permissionsFile;

    // Permissions.yml Contents

    private static String nick;
    private static String nickOthers;
    private static String nickFilterBypass;

    private static String vanish;
    private static String vanishOthers;
    private static String seeVanished;

    private static String broadcast;
    private static String chatFilterBypass;

    private static String mute;
    private static String ban;
    private static String kick;
    private static String warn;

    private static String reloadConfig;
    private static String op;

    private static String feed;
    private static String heal;
    private static String fly;
    private static String teleport;

    private static String inventoryView;


    // Final Variables (don't need to be refreshed)
    private static final File dataFolder = SillyCore.getSillyDataFolder();
    
    public static void setValues() {
        permissionsFile = new File(dataFolder, "permissions.yml");
        if (!permissionsFile.exists()) SillyCore.getInstance().saveResource("permissions.yml", false);

        permissions = YamlConfiguration.loadConfiguration(permissionsFile); // Load permissions.yml for usage

        setBan(get("ban"));
        setKick(get("kick"));
        setBroadcast(get("broadcast"));
        setMute(get("mute"));
        setNick(get("nick"));
        setNickOthers(get("nick_others"));
        setVanish(get("vanish"));
        setVanishOthers(get("vanish_others"));
        setChatFilterBypass(get("bypass_filter"));
        setReloadConfig(get("reload_config"));
        setOp(get("operator"));
        setNickFilterBypass(get("nick_filter_bypass"));
        setFeed(get("feed"));
        setHeal(get("heal"));
        setFly(get("fly"));
        setSeeVanished(get("see_vanished"));
        setTeleport(get("teleport"));
        setInventoryView(get("invview"));
    }

    public static void clear() {
        fly = null;
        heal = null;
        feed = null;
        nickFilterBypass = null;
        op = null;
        reloadConfig = null;
        chatFilterBypass = null;
        vanish = null;
        vanishOthers = null;
        nickOthers = null;
        nick = null;
        mute = null;
        broadcast = null;
        ban = null;
        kick = null;
    }
    
    private static String get(final String path) {
        return permissions.getString(path);
    }

    public static FileConfiguration getPermissions() {
        return permissions;
    }

    public static void setPermissions(FileConfiguration permissions) {
        Permissions.permissions = permissions;
    }

    public static File getPermissionsFile() {
        return permissionsFile;
    }

    public static void setPermissionsFile(File permissionsFile) {
        Permissions.permissionsFile = permissionsFile;
    }

    public static String getNick() {
        return nick;
    }

    public static void setNick(String nick) {
        Permissions.nick = nick;
    }

    public static String getNickOthers() {
        return nickOthers;
    }

    public static void setNickOthers(String nickOthers) {
        Permissions.nickOthers = nickOthers;
    }

    public static String getNickFilterBypass() {
        return nickFilterBypass;
    }

    public static void setNickFilterBypass(String nickFilterBypass) {
        Permissions.nickFilterBypass = nickFilterBypass;
    }

    public static String getVanish() {
        return vanish;
    }

    public static void setVanish(String vanish) {
        Permissions.vanish = vanish;
    }

    public static String getVanishOthers() {
        return vanishOthers;
    }

    public static void setVanishOthers(String vanishOthers) {
        Permissions.vanishOthers = vanishOthers;
    }

    public static String getBroadcast() {
        return broadcast;
    }

    public static void setBroadcast(String broadcast) {
        Permissions.broadcast = broadcast;
    }

    public static String getChatFilterBypass() {
        return chatFilterBypass;
    }

    public static void setChatFilterBypass(String chatFilterBypass) {
        Permissions.chatFilterBypass = chatFilterBypass;
    }

    public static String getMute() {
        return mute;
    }

    public static void setMute(String mute) {
        Permissions.mute = mute;
    }

    public static String getBan() {
        return ban;
    }

    public static void setBan(String ban) {
        Permissions.ban = ban;
    }

    public static String getKick() {
        return kick;
    }

    public static void setKick(String kick) {
        Permissions.kick = kick;
    }

    public static String getWarn() {
        return warn;
    }

    public static void setWarn(String warn) {
        Permissions.warn = warn;
    }

    public static String getReloadConfig() {
        return reloadConfig;
    }

    public static void setReloadConfig(String reloadConfig) {
        Permissions.reloadConfig = reloadConfig;
    }

    public static String getOp() {
        return op;
    }

    public static void setOp(String op) {
        Permissions.op = op;
    }

    public static String getFeed() {
        return feed;
    }

    public static void setFeed(String feed) {
        Permissions.feed = feed;
    }

    public static String getHeal() {
        return heal;
    }

    public static void setHeal(String heal) {
        Permissions.heal = heal;
    }

    public static String getFly() {
        return fly;
    }

    public static void setFly(String fly) {
        Permissions.fly = fly;
    }

    public static String getSeeVanished() {
        return seeVanished;
    }

    public static void setSeeVanished(String seeVanished) {
        Permissions.seeVanished = seeVanished;
    }

    public static String getTeleport() {
        return teleport;
    }

    public static void setTeleport(String teleport) {
        Permissions.teleport = teleport;
    }

    public static String getInventoryView() {
        return inventoryView;
    }

    public static void setInventoryView(String inventoryView) {
        Permissions.inventoryView = inventoryView;
    }
}
