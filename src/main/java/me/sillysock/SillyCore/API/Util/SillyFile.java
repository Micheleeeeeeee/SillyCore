package me.sillysock.SillyCore.API.Util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SillyFile {
    
    private File file;
    private FileConfiguration fileConfiguration;
    private String path;
    private String pathAbsolute;
    private String pathCanonical;
    
    public SillyFile(final File file) {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        path = file.getPath();
        pathAbsolute = file.getAbsolutePath();
        try {
            pathCanonical = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File newFile) {
        file = newFile;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void setFileConfiguration(FileConfiguration newFileConfiguration) {
        fileConfiguration = newFileConfiguration;
    }
    
    public void update() {
        file = new File(path);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathAbsolute() {
        return pathAbsolute;
    }

    public void setPathAbsolute(String pathAbsolute) {
        this.pathAbsolute = pathAbsolute;
    }

    public String getPathCanonical() {
        return pathCanonical;
    }

    public void setPathCanonical(String pathCanonical) {
        this.pathCanonical = pathCanonical;
    }
}
