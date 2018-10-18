package net.square.utils;

import net.square.api.API;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    public static Utils instance;

    public void setInstance() {
        instance = this;
    }

    public void consoleMessage(Object message, TYPE type) {

        String date = dateFormat.format(new Date());

        if (type.equals(TYPE.MESSAGE)) {
            Bukkit.getConsoleSender().sendMessage("§7[§9" + date + "§7] " + API.instance.cpr + message);
        } else if (type.equals(TYPE.ERROR)) {
            Bukkit.getConsoleSender().sendMessage("§7[§9" + date + "§7] " + API.instance.cprF + "§cAn error has occured: " + message);
        } else if (type.equals(TYPE.EMPTY)) {
            Bukkit.getConsoleSender().sendMessage("§7[§9" + date + "§7] " + API.instance.cpr + "");
        }
    }
}
