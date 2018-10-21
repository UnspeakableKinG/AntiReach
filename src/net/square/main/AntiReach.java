package net.square.main;

import net.square.api.API;
import net.square.api.AntiReachAPI;
import net.square.config.ConfigManager;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiReach extends JavaPlugin {

    public static AntiReach instace;

    public void onEnable() {

        /**
         * The main class in the system. However, it only serves to load the API start method in the onEnable.
         * Here, however, the three main classes are set as a fixed instance.
         */

        instace = this;
        new API().setInstance();
        new ConfigManager().setInstance();
        new Utils().setInstance();
        new AntiReachAPI().setInstance();

        try {
            API.instance.onStart();
        } catch (Exception error) {
            Utils.instance.consoleMessage("Cant load the main-class", TYPE.ERROR);
            error.printStackTrace();
        }
    }
}
