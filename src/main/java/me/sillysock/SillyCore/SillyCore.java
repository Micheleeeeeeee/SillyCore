package me.sillysock.SillyCore;

import me.sillysock.SillyCore.Commands.Administrator.Vanish;
import me.sillysock.SillyCore.Listeners.PlayerJoinQuitEventHandlers;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SillyCore
        extends JavaPlugin {

    private static Logger logger;
    private static PluginManager pluginManager;
    private static SillyCore instance;

    // BEGIN CONFIGURATION FILES

    private static FileConfiguration permissionsYml;
    private static FileConfiguration langYml;
    private static FileConfiguration config;
    private static File langFile;
    private static File permissionsFile;

    // lang.yml information

    private String startupMessage;
    private String noPermission;

    @Override public void onEnable() {
        // Initialise static stuff
        logger = getLogger();
        pluginManager = getServer().getPluginManager();
        instance = this;

        fileInitialisations();

        // Registration of events/commands
        registerEvent("Join & Quit Events", new PlayerJoinQuitEventHandlers());
        registerCommand("vanish", new Vanish());

        // Initialisation of config values
        startupMessage = getFromLangFile("startup_message");
        noPermission = ChatColor.translateAlternateColorCodes('&', getFromLangFile("no_permission"));

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
        pluginManager.registerEvents(listener, getInstance());
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

    public static Logger getLog() {
        return logger;
    }

    private void fileCheck() {

        if (langFile.exists() && permissionsFile.exists()) return;

        getInstance().saveResource("lang.yml", false);
        getInstance().saveResource("permissions.yml", false);
    }

    public static String getFromConfig(final String path) {
        return config.getString(path);
    }

    public static String getFromLangFile(final String path) {
        return langYml.getString(path);
    }

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
}
