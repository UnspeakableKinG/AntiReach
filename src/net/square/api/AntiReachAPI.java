package net.square.api;

import org.bukkit.entity.Player;


/**
 * Copyright © SquareCode 2018
 * created on: 14.10.2018 / 14:38
 * Project: AntiReach
 */
public class AntiReachAPI {

    public static AntiReachAPI instance;

    public void setInstance() {
        instance = this;
    }

    public static Integer getVL(final Player player) {
        return API.instance.VL.get(player.getUniqueId());
    }

    public static void createPlayer(final Player player) {
        API.instance.VL.put(player.getUniqueId(), 0);
    }

    public static void addVL(final Player player) {
        API.instance.VL.put(player.getUniqueId(), API.instance.VL.get(player.getUniqueId()) +1);
    }

    public static void clearMap() {
        API.instance.VL.clear();
    }

    public static void removePlayer(final Player player) {
        API.instance.VL.remove(player.getUniqueId());
    }

    public static void setVL(final Player player, int level) {
        API.instance.VL.replace(player.getUniqueId(), API.instance.VL.get(player.getUniqueId()), level);
    }
}
