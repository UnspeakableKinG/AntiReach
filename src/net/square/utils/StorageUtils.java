package net.square.utils;

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
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("[AntiReach] Warning: Unable to write log for " + player.getName() + "!");
        }
    }
}
