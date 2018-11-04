package net.square.api;

import net.square.check.*;
import net.square.commands.antireach_Command;
import net.square.config.ConfigManager;
import net.square.event.JoinListener;
import net.square.event.QuitListener;
import net.square.main.AntiReach;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;


import java.util.ArrayList;

public class API {

    /*-------------------------------------------------------------------------------------------------------*/

    /**
     * This is the main class in the system.
     * Everything is controlled and everything set.
     * If this class is missing, the whole plugin will not work anymore.
     */

    public static API instance;
    public ArrayList<String> verbosemode = new ArrayList<>();
    public String cpr = "§8| §cINFO §8|  ";
    public String Stripline = "┌───────────────────────────────────────";
    public String Stripline2 = "└───────────────────────────────────────";
    public String bypass;
    public String admin;
    public String verbose;
    public String prefix;
    public String noperm;
    public String noplayer;
    public String plugin;
    public double MAX_REACH_A;
    public double MAX_REACH_B;
    public double MAX_REACH_C;
    public double MAX_REACH_D;
    public double MAX_REACH_E;
    public double hitBoxLenght;
    public double normX;
    public double normY;
    public boolean consolelog;
    public boolean ownmessage;
    public boolean resetpitch;
    public boolean REACH_A;
    public boolean REACH_B;
    public boolean REACH_C;
    public boolean REACH_D;
    public boolean REACH_E;
    public boolean HIT_DIRECTION;
    /*-------------------------------------------------------------------------------------------------------*/

    public void loadValues() {

        /**
         * From this method, all values ​​are loaded from the Config.yml.
         * The config is created in the ConfigManager.
         * (net.square.config.ConfigManager)
         */

        prefix = ConfigManager.instance.fileconfigfile.getString("Prefix").replace("<s>", "┃").replace("<p>", "●").replace("<pk>", "•").replace(">>", "»").replace("<<", "«").replace("<st>", "×").replace("&", "§").replace("<d>", "►").replace("<sb>", "▎");
        noperm = ConfigManager.instance.fileconfigfile.getString("General.NoPermissions").replace("&", "§").replace("%prefix%", prefix);
        noplayer = ConfigManager.instance.fileconfigfile.getString("General.NoPlayer").replace("&", "§").replace("%prefix%", prefix);
        bypass = ConfigManager.instance.fileconfigfile.getString("Permissions.bypass");
        admin = ConfigManager.instance.fileconfigfile.getString("Permissions.admin");
        verbose = ConfigManager.instance.fileconfigfile.getString("Permissions.verbose");
        plugin = ConfigManager.instance.fileconfigfile.getString("Messages.Plugin").replace("&", "§").replace("%prefix%", prefix);
        consolelog = ConfigManager.instance.fileconfigfile.getBoolean("Settings.console-log");
        ownmessage = ConfigManager.instance.fileconfigfile.getBoolean("General.own-permissions-message");
        resetpitch = ConfigManager.instance.fileconfigfile.getBoolean("Settings.reset-player-pitch");
        MAX_REACH_A = ConfigManager.instance.fileconfigfile.getDouble("Checks.A.maxreach");
        MAX_REACH_B = ConfigManager.instance.fileconfigfile.getDouble("Checks.B.maxreach");
        MAX_REACH_C = ConfigManager.instance.fileconfigfile.getDouble("Checks.C.maxreach");
        MAX_REACH_D = ConfigManager.instance.fileconfigfile.getDouble("Checks.D.maxreach");
        MAX_REACH_E = ConfigManager.instance.fileconfigfile.getDouble("Checks.E.maxreach");
        REACH_A = ConfigManager.instance.fileconfigfile.getBoolean("Checks.A.enable");
        REACH_B = ConfigManager.instance.fileconfigfile.getBoolean("Checks.B.enable");
        REACH_C = ConfigManager.instance.fileconfigfile.getBoolean("Checks.C.enable");
        REACH_D = ConfigManager.instance.fileconfigfile.getBoolean("Checks.D.enable");
        REACH_E = ConfigManager.instance.fileconfigfile.getBoolean("Checks.E.enable");
        HIT_DIRECTION = ConfigManager.instance.fileconfigfile.getBoolean("Checks.HitDirection.enable");
        hitBoxLenght = ConfigManager.instance.fileconfigfile.getDouble("Checks.HitDirection.hitBoxLenght");
        normX = ConfigManager.instance.fileconfigfile.getDouble("Checks.HitDirection.normX");
        normY = ConfigManager.instance.fileconfigfile.getDouble("Checks.HitDirection.normY");
    }

    public void onStart() {
        // on start methode
        Utils.instance.consoleMessage(Stripline, TYPE.MESSAGE);
        Utils.instance.consoleMessage("├ INFO | » §7Starting plugin...", TYPE.MESSAGE);
        ConfigManager.instance.createConfig();
        Utils.instance.consoleMessage("├ INFO | » §7Config loaded", TYPE.MESSAGE);
        this.startSession();
    }

    public void startSession() {
        // start session method
        this.loadValues();
        Utils.instance.consoleMessage("├ INFO | » §7Values loaded", TYPE.MESSAGE);
        this.register();
        Utils.instance.consoleMessage("├ INFO | » §7Commands registred", TYPE.MESSAGE);
        this.registerChecks();
        Utils.instance.consoleMessage("├ INFO | » §7Checks registred", TYPE.MESSAGE);
        Utils.instance.consoleMessage(Stripline2, TYPE.MESSAGE);
    }

    public void registerChecks() {
        if (REACH_A) {
            Bukkit.getPluginManager().registerEvents(new reach_a(), AntiReach.instance);
            Utils.instance.consoleMessage("├ INFO | » §f(§eENABLE§f) §7Reach A §f(§a" + MAX_REACH_A + "§f)", TYPE.MESSAGE);
        } else {
            Utils.instance.consoleMessage("├ INFO | » §f(§cDISABLE§f) §7Reach A", TYPE.MESSAGE);
        }
        if (REACH_B) {
            Bukkit.getPluginManager().registerEvents(new reach_b(), AntiReach.instance);
            Utils.instance.consoleMessage("├ INFO | » §f(§eENABLE§f) §7Reach B §f(§a" + MAX_REACH_B + "§f)", TYPE.MESSAGE);
        } else {
            Utils.instance.consoleMessage("├ INFO | » §f(§cDISABLE§f) §7Reach B ", TYPE.MESSAGE);
        }
        if (REACH_C) {
            Bukkit.getPluginManager().registerEvents(new reach_c(), AntiReach.instance);
            Utils.instance.consoleMessage("├ INFO | » §f(§eENABLE§f) §7Reach C §f(§a" + MAX_REACH_C + "§f)", TYPE.MESSAGE);
        } else {
            Utils.instance.consoleMessage("├ INFO | » §f(§cDISABLE§f) §7Reach C", TYPE.MESSAGE);
        }
        if (REACH_D) {
            Bukkit.getPluginManager().registerEvents(new reach_d(), AntiReach.instance);
            Utils.instance.consoleMessage("├ INFO | » §f(§eENABLE§f) §7Reach D §f(§a" + MAX_REACH_D + "§f)", TYPE.MESSAGE);
        } else {
            Utils.instance.consoleMessage("├ INFO | » §f(§cDISABLE§f) §7Reach D", TYPE.MESSAGE);
        }
        if (REACH_E) {
            Bukkit.getPluginManager().registerEvents(new reach_e(), AntiReach.instance);
            Utils.instance.consoleMessage("├ INFO | » §f(§eENABLE§f) §7Reach E §f(§a" + MAX_REACH_E + "§f)", TYPE.MESSAGE);
        } else {
            Utils.instance.consoleMessage("├ INFO | » §f(§cDISABLE§f) §7Reach E", TYPE.MESSAGE);
        }
        if (HIT_DIRECTION) {
            Bukkit.getPluginManager().registerEvents(new hitdirection_a(), AntiReach.instance);
            Utils.instance.consoleMessage("├ INFO | » §f(§eENABLE§f) §7HitDirection A", TYPE.MESSAGE);
        } else {
            Utils.instance.consoleMessage("├ INFO | » §f(§cDISABLE§f) §7HitDirection A", TYPE.MESSAGE);
        }
    }

    public void register() {

        /**
         * Here are all Commands & Events in
         * imported to the plugin.
         */
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), AntiReach.instance);
        pm.registerEvents(new QuitListener(), AntiReach.instance);

        AntiReach.instance.getCommand("antireach").setExecutor(new antireach_Command());
    }

    public void setInstance() {
        instance = this;
    }

    public void pokeReach(String player, String description, String distance, int ping, double tps, ReachType type) {

        if (API.instance.resetpitch) {
            Location loc = Bukkit.getPlayer(player).getLocation();
            loc.setPitch(-90F);
            loc.setYaw(-90F);
            Bukkit.getPlayer(player).teleport(loc);

            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    if (consolelog) {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + "]");
                        }
                        Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + "]");
                    } else {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + "]");
                        }
                    }
                }
            }

        } else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    if (consolelog) {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + "]");
                        }
                        Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + "]");
                    } else {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + "]");
                        }
                    }
                }
            }
        }
    }
    public void pokeHit (String player, String description, int ping, double tps) {


        if (API.instance.resetpitch) {
            Location loc = Bukkit.getPlayer(player).getLocation();
            loc.setPitch(-90F);
            loc.setYaw(-90F);
            Bukkit.getPlayer(player).teleport(loc);

            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    if (consolelog) {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for HitDirection: " + description + " [Ping:" + ping + " TPS:" + tps + "]");
                        }
                        Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for HitDirection: " + description + " [Ping:" + ping + " TPS:" + tps + "]");
                    } else {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for HitDirection: " + description + " [Ping:" + ping + " TPS:" + tps + "]");
                        }
                    }
                }
            }
        } else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    if (consolelog) {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for HitDirection: " + description + " [Ping:" + ping + " TPS:" + tps + "]");
                        }
                        Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for HitDirection: " + description + " [Ping:" + ping + " TPS:" + tps + "]");
                    } else {
                        if (API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for HitDirection: " + description + " [Ping:" + ping + " TPS:" + tps + "]");
                        }
                    }
                }
            }
        }
    }
}
