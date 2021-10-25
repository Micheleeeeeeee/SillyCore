package me.sillysock.SillyCore;

import me.sillysock.SillyCore.API.Config;
import me.sillysock.SillyCore.Commands.Administrator.Vanish;
import me.sillysock.SillyCore.Commands.Member.MemberListCommand;
import me.sillysock.SillyCore.Commands.Miscellaneous.NicknameCommand;
import me.sillysock.SillyCore.Commands.Miscellaneous.RealnameCommand;
import me.sillysock.SillyCore.Listeners.CancelOpCommand;
import me.sillysock.SillyCore.Listeners.PlayerJoinQuitEventHandlers;
import me.sillysock.SillyCore.Managers.NickManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SillyCore
        extends JavaPlugin {

    private static Logger logger;
    private static PluginManager pluginManager;
    private static SillyCore instance;
    private static NickManager nicknameManager;

    // BEGIN CONFIGURATION FILES

    private static FileConfiguration permissionsYml;
    private static FileConfiguration langYml;
    private static FileConfiguration config;
    private static File langFile;
    private static File permissionsFile;

    // lang.yml information

    private static String startupMessage;
    private static String joinMessage;
    private static String quitMessage;
    private static String noPermission;
    private static String notNicked;
    private static String opCommandDisabled;
    private static String nicknameInsufficientArguments;
    private static String realnameInsufficientArguments;
    private static String doesNotExistOrIsOffline;

    private static boolean opCommandEnabled;

    @Override public void onEnable() {
        // Initialise static stuff
        logger = getLogger();
        pluginManager = getServer().getPluginManager();
        instance = this;
        nicknameManager = new NickManager();

        fileInitialisations();

        // Registration of events/commands
        registerEventsAndCommands();

        // Initialisation of config values
        refreshConfigValues();
        joinQuitMessages();

        // Log...
        logger.log(Level.INFO, startupMessage);
    }

    @Override public void onDisable() {
        logger = null;
        pluginManager = null;
        instance = null;



        System.out.printf("The plugin has been disabled.\n");
    }

    private void registerCommand(final String name, final CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
        logger.log(Level.INFO, "Command /" + name + " registered from " + executor);
    }

    private void registerEvent(final String name, final Listener listener) {
        getPluginManager().registerEvents(listener, getInstance());
        logger.log(Level.INFO, "Event " + name + " registered from " + listener);
    }

    private void fileInitialisations() {
        // Create files
        permissionsFile = new File(getDataFolder(), "permissions.yml");
        langFile = new File(getDataFolder(), "lang.yml");
        saveDefaultConfig();
        config = getConfig();
        fileCheck();

        permissionsYml = YamlConfiguration.loadConfiguration(permissionsFile);
        langYml = YamlConfiguration.loadConfiguration(langFile);
    }

    private void fileCheck() {

        if (langFile.exists()) return;
        if (permissionsFile.exists()) return;

        getInstance().saveResource("lang.yml", false);
        getInstance().saveResource("permissions.yml", false);
    }

    private void registerEventsAndCommands() {
        // Registration of events/commands
        registerEvent("Join & Quit Events", new PlayerJoinQuitEventHandlers());
        registerEvent("Cancel OP Command", new CancelOpCommand());
        registerCommand("vanish", new Vanish());
        registerCommand("memberlist", new MemberListCommand());
        registerCommand("nick", new NicknameCommand());
        registerCommand("realname", new RealnameCommand());
    }

    private void joinQuitMessages() {
        joinMessage = ChatColor.translateAlternateColorCodes('&', getFromLangFile("join_message"));
        quitMessage = ChatColor.translateAlternateColorCodes('&', getFromLangFile("quit_message"));
    }

    public static void refreshConfigValues() {
        Config.setJoinMessage();
    }

    public static void setInstance(SillyCore instance) {
        SillyCore.instance = instance;
    }

    public static void setPermissionsYml(FileConfiguration permissionsYml) {
        SillyCore.permissionsYml = permissionsYml;
    }

    public static void setLangYml(FileConfiguration langYml) {
        SillyCore.langYml = langYml;
    }

    public static void setConfig(FileConfiguration config) {
        SillyCore.config = config;
    }

    public static void setLangFile(File langFile) {
        SillyCore.langFile = langFile;
    }

    public static void setPermissionsFile(File permissionsFile) {
        SillyCore.permissionsFile = permissionsFile;
    }

    public static void setStartupMessage(String startupMessage) {
        SillyCore.startupMessage = startupMessage;
    }

    public static void setNoPermission(String noPermission) {
        SillyCore.noPermission = noPermission;
    }

    public static void setNicknameInsufficientArguments(String nicknameInsufficientArguments) {
        SillyCore.nicknameInsufficientArguments = nicknameInsufficientArguments;
    }

    public static void setJoinMessage(String joinMessage) {
        SillyCore.joinMessage = joinMessage;
    }

    public static void setQuitMessage(String quitMessage) {
        SillyCore.quitMessage = quitMessage;
    }

    public static String getNicknameInsufficientArguments() {
        return nicknameInsufficientArguments;
    }

    public static String getJoinMessage() {
        return joinMessage;
    }

    public static String getQuitMessage() {
        return quitMessage;
    }

    public static Logger getLog() {
        return logger;
    }

    public static String getFromConfig(final String path) {
        return config.getString(path);
    }

    public static String getFromLangFile(final String path) {
        return langYml.getString(path);
    }

    public static boolean getBooleanFromConfig(final String path) { return config.getBoolean(path); }

    public static String getFromPermissionsFile(final String path) {
        return permissionsYml.getString(path);
    }

    public static FileConfiguration getConfiguration() {
        return config;
    }

    public static FileConfiguration getPermissionsYml() {
        return permissionsYml;
    }

    public static FileConfiguration getLangYml() {
        return langYml;
    }

    public static File getLangFile() {
        return langFile;
    }

    public static File getPermissionsFile() {
        return permissionsFile;
    }

    public static SillyCore getInstance() {
        return instance;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static String getRealnameInsufficientArguments() {
        return realnameInsufficientArguments;
    }

    public static String getStartupMessage() {
        return startupMessage;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static void setRealnameInsufficientArguments(String realnameInsufficientArguments) {
        SillyCore.realnameInsufficientArguments = realnameInsufficientArguments;
    }

    public static String getDoesNotExistOrIsOffline() {
        return doesNotExistOrIsOffline;
    }

    public static void setDoesNotExistOrIsOffline(String doesNotExistOrIsOffline) {
        SillyCore.doesNotExistOrIsOffline = doesNotExistOrIsOffline;
    }

    public static boolean isOpCommandEnabled() {
        return opCommandEnabled;
    }

    public static void setOpCommandEnabled(boolean opCommandEnabled) {
        SillyCore.opCommandEnabled = opCommandEnabled;
    }

    public static String getOpCommandDisabled() {
        return opCommandDisabled;
    }

    public static void setOpCommandDisabled(String opCommandDisabled) {
        SillyCore.opCommandDisabled = opCommandDisabled;
    }

    public static NickManager getNicknameManager() {
        return nicknameManager;
    }

    public static String getNotNicked() {
        return notNicked;
    }

    public static void setNotNicked(String notNicked) {
        SillyCore.notNicked = notNicked;
    }
}
