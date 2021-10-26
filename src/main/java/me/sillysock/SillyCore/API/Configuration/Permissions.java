package me.sillysock.SillyCore.API.Configuration;

import me.sillysock.SillyCore.SillyCore;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Permissions {
    
    private static FileConfiguration permissions;
    private static File permissionsFile;

    private static final File dataFolder = SillyCore.getSillyDataFolder();
    
    public static void setValues() {
        permissionsFile = new File(dataFolder, "permissions.yml");
        if (!permissionsFile.exists()) SillyCore.getInstance().saveResource("permissions.yml", false);


    }
}
