package net.square.utils;

import org.bukkit.Location;

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

    public double getDistance3D(Location one, Location two) {
        double toReturn = 0.0D;
        double xSqr = (two.getX() - one.getX()) * (two.getX() - one.getX());
        double ySqr = (two.getY() - one.getY()) * (two.getY() - one.getY());
        double zSqr = (two.getZ() - one.getZ()) * (two.getZ() - one.getZ());
        double sqrt = Math.sqrt(xSqr + ySqr + zSqr);
        toReturn = Math.abs(sqrt);
        return toReturn;
    }
}
