package net.square.main;

import net.square.api.API;
import net.square.api.AntiReachAPI;
import net.square.api.Metrics;
import net.square.api.ModuleManager;
import net.square.config.ConfigManager;
import net.square.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiReach extends JavaPlugin {

    public static AntiReach instance;

    public static long current;

    public void onEnable() {
        current = System.currentTimeMillis();

        /**
         * The main class in the system. However, it only serves to load the API start method in the onEnable.
         * Here, however, the three main classes are set as a fixed instance.
         */

        instance = this;
        new API().setInstance();
        new ConfigManager().setInstance();
        new Utils().setInstance();
        new AntiReachAPI().setInstance();
        new MathUtil().setInstance();
        new TPSManager().setInstance();
        new Metrics(this);
        new ModuleManager().setInstance();
        new IDGen().setInstance();

        try {
            API.instance.onStart();
        } catch (Exception error) {
            Utils.instance.consoleMessage("Cant load the main-class", TYPE.ERROR);
            error.printStackTrace();
        }
    }
}
