package me.sillysock.SillyCore;

import me.sillysock.SillyCore.API.Configuration.Config;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.Commands.Administrator.Vanish;
import me.sillysock.SillyCore.Commands.Member.FeedCommand;
import me.sillysock.SillyCore.Commands.Member.HealCommand;
import me.sillysock.SillyCore.Commands.Member.MemberListCommand;
import me.sillysock.SillyCore.Commands.Miscellaneous.NicknameCommand;
import me.sillysock.SillyCore.Commands.Miscellaneous.RealnameCommand;
import me.sillysock.SillyCore.Listeners.CancelOpCommand;
import me.sillysock.SillyCore.Listeners.PlayerJoinQuitEventHandlers;
import me.sillysock.SillyCore.Managers.NickManager;
import me.sillysock.SillyCore.Managers.ServerManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SillyCore
        extends JavaPlugin {

    private static Logger logger;
    private static PluginManager pluginManager;
    private static SillyCore instance;
    private static NickManager nicknameManager;
    private static File dataFolder;

    @Override public void onEnable() {
        // Initialise static stuff
        logger = getLogger();
        pluginManager = getServer().getPluginManager();
        instance = this;
        nicknameManager = new NickManager();
        dataFolder = getDataFolder();

        // Registration of events/commands
        registerEventsAndCommands();

        // Initialisation of config values
        reloadConfig();
        Config.setValues();

        // Log...
        logger.log(Level.INFO, Lang.getStartupMessage());
    }

    @Override public void onDisable() {
        logger = null;
        pluginManager = null;
        instance = null;

        System.out.print("The plugin has been disabled.\n");
    }

    private void registerEventsAndCommands() {
        // Registration of events/commands
        registerEvent("Join & Quit Events", new PlayerJoinQuitEventHandlers());
        registerEvent("Cancel OP Command", new CancelOpCommand());
        registerCommand("vanish", new Vanish());
        registerCommand("memberlist", new MemberListCommand());
        registerCommand("nick", new NicknameCommand());
        registerCommand("realname", new RealnameCommand());
        registerCommand("server", new ServerManager());
        registerCommand("feed", new FeedCommand());
        registerCommand("heal", new HealCommand());
    }

    private void registerCommand(final String name, final CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
        logger.log(Level.INFO, "Command /" + name + " registered from " + executor);
    }

    private void registerEvent(final String name, final Listener listener) {
        getPluginManager().registerEvents(listener, getInstance());
        logger.log(Level.INFO, "Event " + name + " registered from " + listener);
    }

    public static void setInstance(SillyCore instance) {
        SillyCore.instance = instance;
    }

    public static Logger getLog() {
        return logger;
    }

    public static SillyCore getInstance() {
        return instance;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static NickManager getNicknameManager() {
        return nicknameManager;
    }

    public static File getSillyDataFolder() {
        return dataFolder;
    }
}
