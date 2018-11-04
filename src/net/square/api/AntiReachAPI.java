package net.square.api;


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

    public void clearReachVL() {
        API.VLReach.clear();
    }
    public void clearHitDirectionVL() {
        API.VLHitDirection.clear();
    }
}
