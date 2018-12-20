package net.square.api;

import net.square.check.*;

/**
 * Copyright © SquareCode 2018
 * created on: 03.12.2018 / 18:14
 * Project: AntiReach
 */
public class ModuleManager {
    public static ModuleManager instance;

    public void loadModules() {
        new reach_a();
        new reach_b();
        new reach_c();
        new reach_d();
        new reach_e();
        new reach_f();
        new reach_g();
        new reach_h();
        new reach_i();
        new reach_j();
    }

    public void setInstance() {
        ModuleManager.instance = this;
    }
}
