package me.sillysock.SillyCore;

import me.sillysock.SillyCore.Commands.Administrator.Vanish;
import me.sillysock.SillyCore.Listeners.PlayerJoinQuitEventHandlers;
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
    private static File langFile;
    private static File permissionsFile;

    @Override public void onEnable() {
        // Initialise static stuff
        logger = getLogger();
        pluginManager = getServer().getPluginManager();
        instance = this;

        // Create files
        permissionsFile = new File("permissions.yml");
        langFile = new File("lang.yml");
        fileCheck();

        permissionsYml = YamlConfiguration.loadConfiguration(permissionsFile);
        langYml = YamlConfiguration.loadConfiguration(langFile);

        // Registration of events/commands
        registerEvent("Join & Quit Events", new PlayerJoinQuitEventHandlers());
        registerCommand("vanish", new Vanish());

        // Log...
        logger.log(Level.INFO, "Plugin startup finished. Enjoy.");
    }

    @Override public void onDisable() {
        logger = null;
        pluginManager = null;
        instance = null;

        System.out.printf("The plugin has been disabled.\n");
    }

    void registerCommand(final String name, final CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
        logger.log(Level.INFO, "Command /" + name + " registered from " + executor);
    }

    void registerEvent(final String name, final Listener listener) {
        pluginManager.registerEvents(listener, getInstance());
        logger.log(Level.INFO, "Event " + name + " registered from " + listener);
    }

    public static Logger getLog() {
        return logger;
    }

    void fileCheck() {
        if (!permissionsFile.exists())
            permissionsFile.mkdir();
        if (!langFile.exists())
            langFile.mkdir();
    }

    public static SillyCore getInstance() {
        return instance;
    }
}
