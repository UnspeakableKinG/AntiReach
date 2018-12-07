package net.square.config;

import net.square.main.AntiReach;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public static ConfigManager instance;

    public void setInstance() {
        instance = this;
    }

    private AntiReach plugin = AntiReach.instance;
    public File fileconfig;
    public YamlConfiguration fileconfigfile;
    public File valuesfile;
    public YamlConfiguration valuesfileconf;
    public File langfile;
    public YamlConfiguration langfileconf;

    public void createConfig() {
        File file = new File("plugins/AntiReach/config.yml");
        if (!file.exists()) {
            plugin.saveDefaultConfig();
        }
        fileconfig = file;
        fileconfigfile = YamlConfiguration.loadConfiguration(file);
    }
    public void loadvaluesFile() {
        final File file = new File("plugins/AntiReach/values.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {}
        }
        this.valuesfile = file;
        this.valuesfileconf = YamlConfiguration.loadConfiguration(file);
    }

    public void loadlanguageFile() {
        final File file = new File("plugins/AntiReach/language.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {}
        }
        this.langfile = file;
        this.langfileconf = YamlConfiguration.loadConfiguration(file);
    }

    public void setDefaultLanguages() {
        this.langfileconf.addDefault("Language.info", "You can change the language to: DE, EN");
        this.langfileconf.addDefault("Language.lang", "EN");
        this.langfileconf.options().copyDefaults(true);
        try {
            this.langfileconf.save(this.langfile);
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void setDefaultValues() {
        this.valuesfileconf.addDefault("Checks.A.enable", true);
        this.valuesfileconf.addDefault("Checks.A.maxreach", 4.3);
        this.valuesfileconf.addDefault("Checks.B.enable", true);
        this.valuesfileconf.addDefault("Checks.B.maxreach", 4.3);
        this.valuesfileconf.addDefault("Checks.C.enable", true);
        this.valuesfileconf.addDefault("Checks.C.maxreach", 4.3);
        this.valuesfileconf.addDefault("Checks.D.enable", true);
        this.valuesfileconf.addDefault("Checks.D.maxreach", 4.3);
        this.valuesfileconf.addDefault("Checks.E.enable", true);
        this.valuesfileconf.addDefault("Checks.E.maxreach", 4.3);
        this.valuesfileconf.addDefault("Checks.F.enable", true);
        this.valuesfileconf.addDefault("Checks.F.maxinteract", 5.4);
        this.valuesfileconf.options().copyDefaults(true);
        try {
            this.valuesfileconf.save(this.valuesfile);
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}
