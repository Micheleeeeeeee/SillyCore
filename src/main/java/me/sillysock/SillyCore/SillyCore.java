package me.sillysock.SillyCore;

import me.sillysock.SillyCore.API.Configuration.Config;
import me.sillysock.SillyCore.API.Configuration.Lang;
import me.sillysock.SillyCore.API.Configuration.PlayerData.DataHandler;
import me.sillysock.SillyCore.API.Configuration.PlayerData.PlayerDataListener;
import me.sillysock.SillyCore.API.UpdateChecker;
import me.sillysock.SillyCore.API.Util.MessageUtils;
import me.sillysock.SillyCore.Commands.Administrator.Vanish;
import me.sillysock.SillyCore.Commands.Member.FeedCommand;
import me.sillysock.SillyCore.Commands.Member.FlyCommand;
import me.sillysock.SillyCore.Commands.Member.HealCommand;
import me.sillysock.SillyCore.Commands.Member.MemberListCommand;
import me.sillysock.SillyCore.Commands.Miscellaneous.NicknameCommand;
import me.sillysock.SillyCore.Commands.Miscellaneous.RealnameCommand;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.Kick.KickCommand;
import me.sillysock.SillyCore.Commands.Moderator.Punishment.Kick.KickListeners;
import me.sillysock.SillyCore.Listeners.CancelOpCommand;
import me.sillysock.SillyCore.Listeners.PlayerJoinQuitEventHandlers;
import me.sillysock.SillyCore.Managers.NickManager;
import me.sillysock.SillyCore.Managers.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SillyCore
        extends JavaPlugin {

    /**
     * <h1 style="font-size: 20px;">SillyCore - The most advanced and easy-to-use Minecraft core plugin for server administrators.</h1>
     */

    private static Logger logger;
    private static PluginManager pluginManager;
    private static SillyCore instance;
    private static NickManager nicknameManager;
    private static File dataFolder;
    private static UpdateChecker checker;
    private static DataHandler dataHandler;


    @Override public void onEnable() {
        // Initialise static stuff
        logger = getLogger();
        pluginManager = getServer().getPluginManager();
        instance = this;
        nicknameManager = new NickManager();
        dataFolder = getDataFolder();
        checker = new UpdateChecker(this, 531961);
        dataHandler = new DataHandler();
        createPlayerDataFolder();

        // Check updates
        logger.log(Level.INFO, "Attempting to check for updates...");
        checker.getVersion(version -> {
            if (this.getDescription().getVersion().equals(version))
                logger.info("There is not a new update available.");
            else
                logger.info("There is a new update available, get it from https://www.spigotmc.org/threads/sillycore.531961/");

        });

        // Registration of events/commands
        registerEventsAndCommands();

        // Initialisation of config values
        this.saveDefaultConfig();
        Config.setValues();

        // Log...
        logger.log(Level.INFO, Lang.getStartupMessage());
    }

    @Override public void onDisable() {
        pluginManager = null;
        instance = null;
        dataFolder = null;
        nicknameManager = null;
        Config.clear();
        System.gc(); // stackoverflow gave me two answers i don't know if this is a good idea

        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(MessageUtils.format("&c&lThe server is being restarted or reloaded."));
            logger.info("If you reloaded the server, don't. You should know better!");
        }
        logger = null;
        System.out.print("The plugin has been disabled.\n");
    }

    private void registerEventsAndCommands() {
        // Registration of events/commands
        registerEvent("Join & Quit Events", new PlayerJoinQuitEventHandlers());
        registerEvent("Cancel OP Command", new CancelOpCommand());
        registerEvent("Kick Listeners", new KickListeners());
        registerEvent("Data Handler Helper", new PlayerDataListener());
        registerCommand("vanish", new Vanish());
        registerCommand("memberlist", new MemberListCommand());
        registerCommand("nick", new NicknameCommand());
        registerCommand("realname", new RealnameCommand());
        registerCommand("server", new ServerManager());
        registerCommand("feed", new FeedCommand());
        registerCommand("heal", new HealCommand());
        registerCommand("fly", new FlyCommand());
        registerCommand("kick", new KickCommand());
    }

    private void registerCommand(final String name,
                                 final CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
        logger.log(Level.INFO, "Command /" + name + " registered from " + executor);
    }

    private void registerEvent(final String name,
                               final Listener listener) {
        getPluginManager().registerEvents(listener, getInstance());
        logger.log(Level.INFO, "Event " + name + " registered from " + listener);
    }

    private void createPlayerDataFolder() {
        File playerData = new File(dataFolder, "PlayerData/");
        if (!playerData.exists()) playerData.mkdir();
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

    public static DataHandler getDataHandler() {
        return dataHandler;
    }
}
