package net.square.utils;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Copyright © SquareCode 2018
 * created on: 23.10.2018 / 19:58
 * Project: AntiReach
 */
public class MathUtil {

    public static MathUtil instance;

    public void setInstance() {
        instance = this;
    }

    @Getter
    public double getDistance3D(Location one, Location two) {
        double toReturn = 0.0D;
        double xSqr = (two.getX() - one.getX()) * (two.getX() - one.getX());
        double ySqr = (two.getY() - one.getY()) * (two.getY() - one.getY());
        double zSqr = (two.getZ() - one.getZ()) * (two.getZ() - one.getZ());
        double sqrt = Math.sqrt(xSqr + ySqr + zSqr);
        toReturn = Math.abs(sqrt);
        return toReturn;
    }

    @Getter
    public static double getHorizontalDistance(final Location to, final Location from) {
        final double x = Math.abs(Math.abs(to.getX()) - Math.abs(from.getX()));
        final double z = Math.abs(Math.abs(to.getZ()) - Math.abs(from.getZ()));
        return Math.sqrt(x * x + z * z);
    }
    @Getter
    public Location getEyeLocation(final Player player) {
        final Location eye = player.getLocation();
        eye.setY(eye.getY() + player.getEyeHeight());
        return eye;
    }
}
