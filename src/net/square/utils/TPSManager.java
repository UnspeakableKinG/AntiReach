package net.square.utils;

import net.square.main.AntiReach;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Copyright © SquareCode 2018
 * created on: 23.10.2018 / 20:14
 * Project: AntiReach
 */
public class TPSManager {

    public static TPSManager instance;

    public void setInstance() {
        instance = this;
    }

    public static double tps;

    public static void startTPSChecking() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) AntiReach.instance, (Runnable)new Runnable() {
            long seconds;
            long latestSeconds;
            int i;

            @Override
            public void run() {
                this.seconds = System.currentTimeMillis() / 1000L;
                if (this.latestSeconds == this.seconds) {
                    ++this.i;
                }
                else {
                    this.latestSeconds = this.seconds;
                    TPSManager.tps = ((TPSManager.tps == 0.0) ? this.i : ((TPSManager.tps + this.i) / 2.0));
                    this.i = 0;
                }
            }
        }, 1L, 1L);
    }

    public static double getTPS() {
        return (TPSManager.tps + 1.0 > 20.0) ? 20.0 : (TPSManager.tps + 1.0);
    }
}
