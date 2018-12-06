package net.square.utils;

import net.square.api.API;
import org.bukkit.Bukkit;

public class Utils {

    public static Utils instance;

    public void setInstance() {
        instance = this;
    }

    public void consoleMessage(Object message, TYPE type) {

        if (type.equals(TYPE.MESSAGE)) {
            Bukkit.getConsoleSender().sendMessage(""+message);
        } else if (type.equals(TYPE.ERROR)) {
            Bukkit.getConsoleSender().sendMessage("§cAn error has occured: " + message);
        } else if (type.equals(TYPE.EMPTY)) {
            Bukkit.getConsoleSender().sendMessage(API.instance.cpr+"");
        }
    }
}
