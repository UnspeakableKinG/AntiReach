package net.square.check;

import net.square.main.AntiReach;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * Copyright © SquareCode 2018
 * created on: 23.10.2018 / 21:49
 * Project: AntiReach
 */
public abstract class Check implements Listener {

    public String name;
    public ReachType type;
    public boolean enabled;

    public Check(final String name, ReachType type, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
        this.type = type;

        if(enabled) {
            Bukkit.getPluginManager().registerEvents(this, AntiReach.instance);
        }
    }
}
