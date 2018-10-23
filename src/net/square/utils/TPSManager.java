package net.square.utils;

/**
 * Copyright © SquareCode 2018
 * created on: 23.10.2018 / 20:14
 * Project: AntiReach
 */
public class TPSManager {

    public static TPSManager instance;

    public void setInstance() {
        instance = this;
    }

    public static int TICK_COUNT = 0;
    public static long[] TICKS = new long[600];

    public double getTPS() {
        return getTPS(100);
    }

    public double getTPS(int ticks) {
        if (TICK_COUNT < ticks) {
            return 20.0D;
        }
        int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
        long elapsed = System.currentTimeMillis() - TICKS[target];

        return ticks / (elapsed / 1000.0D);
    }
}
