package net.square.utils;

import org.bukkit.Location;

/**
 * Copyright © SquareCode 2018
 * created on: 13.12.2018 / 16:13
 * Project: AntiReach
 */
public class DistanceUtil {

    private double xDiff;
    private double yDiff;
    private double zDiff;

    public DistanceUtil(final Location one, final Location two) {
        this.xDiff = Math.abs(one.getX() - two.getX());
        this.yDiff = Math.abs(one.getY() - two.getY());
        this.zDiff = Math.abs(one.getZ() - two.getZ());
    }

    public double getxDiff() {
        return xDiff;
    }

    public double getyDiff() {
        return yDiff;
    }

    public double getzDiff() {
        return zDiff;
    }
}
