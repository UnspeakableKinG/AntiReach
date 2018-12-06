package net.square.api;

import net.square.config.ConfigManager;
import net.square.main.AntiReach;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * Copyright © SquareCode 2018
 * created on: 03.12.2018 / 18:06
 * Project: AntiReach
 */

public class Module implements Listener
{
    private String name;
    private String eventname;
    private boolean enabled;

    public Module(final String name, final String eventname, final HackType type, String buchstaben) {
        this.name = name;
        this.eventname = eventname;
        if (ConfigManager.instance.valuesfileconf.getBoolean("Checks." + buchstaben + ".enable")) {
            Bukkit.getConsoleSender().sendMessage(API.instance.cpr + "§8> §c" + name + " §8(§c" + eventname + "§7, §c" + type + "§7, §c"+buchstaben+"§8)");
            Bukkit.getPluginManager().registerEvents(this, AntiReach.instance);
        }
        else {
            Bukkit.getConsoleSender().sendMessage(API.instance.cpr + "§8> §4" + name + " §8(§4" + eventname + "§7, §4" + type + "§7, §4"+buchstaben+"§8)");
        }
    }
}
