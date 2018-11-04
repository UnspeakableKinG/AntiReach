package net.square.config;

import net.square.main.AntiReach;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    public static ConfigManager instance;

    public void setInstance() {
        instance = this;
    }

    private AntiReach plugin = AntiReach.instance;
    public File fileconfig;
    public YamlConfiguration fileconfigfile;

    public void createConfig() {
        File file = new File("plugins/AntiReach/config.yml");
        if (!file.exists()) {
            plugin.saveDefaultConfig();
        }
        fileconfig = file;
        fileconfigfile = YamlConfiguration.loadConfiguration(file);
    }
}
