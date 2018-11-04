package net.square.utils;

import net.square.api.API;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Copyright © SquareCode 2018
 * created on: 03.11.2018 / 00:59
 * Project: AntiReach
 */
public class AimUtil {

    public static boolean isLookingToEntity(final Player p, final Entity to) {
        boolean looking = false;
        final Vector n = to.getLocation().toVector().subtract(p.getLocation().toVector());
        final Vector vec$ = p.getLocation().toVector().subtract(to.getLocation().toVector());
        if ((p.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < API.instance.hitBoxLenght
                || to.getLocation().getDirection().normalize().crossProduct(vec$).lengthSquared() < API.instance.hitBoxLenght)
                && (n.normalize().dot(p.getLocation().getDirection().normalize()) >= API.instance.normX
                || vec$.normalize().dot(to.getLocation().getDirection().normalize()) >= API.instance.normY)) {
            looking = true;
        }
        return looking;
    }
}
