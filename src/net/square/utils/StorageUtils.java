package net.square.utils;

import net.square.api.API;
import org.bukkit.entity.*;

import java.io.*;

/**
 * Copyright © SquareCode 2018
 * created on: 09.12.2018 / 13:37
 * Project: AntiReach
 */
public class StorageUtils {

    public static StorageUtils instance;

    public void setInstance() {
        StorageUtils.instance = this;
    }

    public static void log(final Player player, final String log) {
        final String fileName = "plugins/AntiReach/Logs/" + player.getName().toLowerCase() + "/logs.log/";
        final File file = new File(fileName.replace(fileName.split("/")[fileName.split("/").length - 1], ""));
        if (!file.exists()) {
            file.mkdirs();
        }
        try (final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("plugins/AntiReach/Logs/" + player.getName().toLowerCase() + "/logs.log", true)))) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[AntiReach] Warning: Unable to write log for " + player.getName() + "!");
        }
    }

    public static void logStart(final String log) {
        String date = API.getCurrentDate();
        String time = API.getCurrentTime().replace(":", "_");
        final String fileName = "plugins/AntiReach/logs/antireach"+date+"-"+time+".log";
        final File file = new File(fileName.replace(fileName.split("/")[fileName.split("/").length - 1], ""));
        if (!file.exists()) {
            file.mkdirs();
        }
        try (final PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("plugins/AntiReach/logs/antireach"+date+"-"+time+".log", true)))) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[AntiReach] Warning: Unable to write log for " + API.getCurrentDate() + API.getCurrentTime() + "!");
        }
    }
}
