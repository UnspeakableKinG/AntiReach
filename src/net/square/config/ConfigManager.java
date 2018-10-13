package net.square.config;

import net.square.main.AntiReach;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    public static ConfigManager instance;

    public void setInstance() {
        instance = this;
    }

    private AntiReach plugin = AntiReach.instace;

    public FileConfiguration fileconfig = null;

    public void createConfig() {
        plugin.saveDefaultConfig();
        fileconfig = plugin.getConfig();
    }
}
