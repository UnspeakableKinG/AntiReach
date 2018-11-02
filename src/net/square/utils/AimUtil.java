package net.square.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Copyright © SquareCode 2018
 * created on: 02.11.2018 / 18:40
 * Project: AntiReach
 */
public class AimUtil {

    private static double hitBoxLenght;

    public static boolean isLookingToEntity(final Player p, final Entity to) {
        boolean looking = false;
        final Vector n = to.getLocation().toVector().subtract(p.getLocation().toVector());
        final Vector vec$ = p.getLocation().toVector().subtract(to.getLocation().toVector());
        if ((p.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < AimUtil.hitBoxLenght ||
                to.getLocation().getDirection().normalize().crossProduct(vec$).lengthSquared() < AimUtil.hitBoxLenght) &&
                (n.normalize().dot(p.getLocation().getDirection().normalize()) >= 0.0 ||
                vec$.normalize().dot(to.getLocation().getDirection().normalize()) >= 0.0)) {
            looking = true;
        }
        return looking;
    }
}
