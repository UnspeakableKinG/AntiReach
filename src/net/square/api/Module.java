package net.square.api;

import net.square.config.ConfigManager;
import net.square.main.AntiReach;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Copyright © SquareCode 2018
 * created on: 03.12.2018 / 18:06
 * Project: AntiReach
 */

public class Module implements Listener {

    public static ArrayList<Module> registered = new ArrayList<Module>();
    public static Module instance;
    private String name;
    private String buchstaben;
    private String eventname;

    public Module(final String name, final String eventname, final HackType type, String buchstaben) {
        this.name = name;
        this.eventname = eventname;
        this.buchstaben = buchstaben;
        if (ConfigManager.instance.fileconfigfile.getBoolean("Checks." + buchstaben + ".enable")) {
            Bukkit.getConsoleSender().sendMessage(API.instance.cpr + " §8> §c" + name + " §8(§c" + eventname + "§7, §c" + type + "§7, §c" + buchstaben + "§8)");
            Bukkit.getPluginManager().registerEvents(this, AntiReach.instance);
            registered.add(this);
        } else {
            Bukkit.getConsoleSender().sendMessage(API.instance.cpr + " §8> §4" + name + " §8(§4" + eventname + "§7, §4" + type + "§7, §4" + buchstaben + "§8)");
        }
    }

    public String getName() {
        return name;
    }

    public String getEventname() {
        return eventname;
    }

    public String getBuchstaben() {
        return buchstaben;
    }
}
