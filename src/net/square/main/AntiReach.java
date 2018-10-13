package net.square.main;

import net.square.api.API;
import net.square.config.ConfigManager;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiReach extends JavaPlugin {

    public static AntiReach instace;

    public void onEnable() {

        instace = this;
        new API().setInstance();
        new ConfigManager().setInstance();
        new Utils().setInstance();

        try {
            API.instance.onStart();
        } catch (Exception error) {
            Utils.instance.consoleMessage("Cant load the main-class", TYPE.ERROR);
            error.printStackTrace();
        }
    }
}
