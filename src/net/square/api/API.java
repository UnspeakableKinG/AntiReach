package net.square.api;

import net.square.check.reach_a;
import net.square.commands.antireach_Command;
import net.square.config.ConfigManager;
import net.square.event.JoinListener;
import net.square.event.QuitListener;
import net.square.main.AntiReach;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class API {

    /*-------------------------------------------------------------------------------------------------------*/

    /**
     * This is the main class in the system.
     * Everything is controlled and everything set.
     * If this class is missing, the whole plugin will not work anymore.
     */

    public static API instance;
    public HashMap<UUID, Integer> VL = new HashMap<>();
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
    public String kickcommand;
    public String list;
    public String plugin;
    public int Leveltokick;
    public boolean consolelog;
    public boolean ownmessage;
    /*-------------------------------------------------------------------------------------------------------*/

    public void loadValues() {

        /**
         * From this method, all values ​​are loaded from the Config.yml.
         * The config is created in the ConfigManager.
         * (net.square.config.ConfigManager)
         */

        prefix = ConfigManager.instance.fileconfig.getString("Prefix").replace("<s>", "┃").replace("<p>", "●").replace("<pk>", "•").replace(">>", "»").replace("<<", "«").replace("<st>", "×").replace("&", "§");
        noperm = ConfigManager.instance.fileconfig.getString("General.NoPermissions").replace("&", "§").replace("%prefix%", prefix);
        noplayer = ConfigManager.instance.fileconfig.getString("General.NoPlayer").replace("&", "§").replace("%prefix%", prefix);
        kickcommand = ConfigManager.instance.fileconfig.getString("General.kick-command").replace("&", "§").replace("%prefix%", prefix);
        bypass = ConfigManager.instance.fileconfig.getString("Permissions.bypass");
        admin = ConfigManager.instance.fileconfig.getString("Permissions.admin");
        verbose = ConfigManager.instance.fileconfig.getString("Permissions.verbose");
        list = ConfigManager.instance.fileconfig.getString("Messages.List").replace("&", "§").replace("%prefix%", prefix);
        plugin = ConfigManager.instance.fileconfig.getString("Messages.Plugin").replace("&", "§").replace("%prefix%", prefix);
        consolelog = ConfigManager.instance.fileconfig.getBoolean("Settings.console-log");
        ownmessage = ConfigManager.instance.fileconfig.getBoolean("General.own-permissions-message");
        Leveltokick = ConfigManager.instance.fileconfig.getInt("General.level-to-kick");
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
        this.setDefault();
        Utils.instance.consoleMessage("├ INFO | » §7defaults set", TYPE.MESSAGE);
        this.register();
        Utils.instance.consoleMessage("├ INFO | » §7Commands registred", TYPE.MESSAGE);
        this.registerChecks();
        Utils.instance.consoleMessage("├ INFO | » §7Checks registred", TYPE.MESSAGE);
        Utils.instance.consoleMessage(Stripline2, TYPE.MESSAGE);
    }

    public void registerChecks() {
        Bukkit.getPluginManager().registerEvents(new reach_a(), AntiReach.instance);
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

    public void setDefault() {

        /**
         * here the default values ​​for each player are set in the hashMap.
         */
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!VL.containsKey(all.getUniqueId())) {
                VL.put(all.getUniqueId(), 0);
            }
        }
    }

    public void setInstance() {
        instance = this;
    }

    public void pokeReach(String player, String description, String distance, int VL, int ping, double tps) {

        if (API.instance.VL.get(Bukkit.getPlayer(player).getUniqueId()) == Leveltokick) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    if (consolelog) {
                       if(API.instance.verbosemode.contains(all.getName())) {
                           all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " (Range:" + distance + ") [Ping:" + ping + " TPS:" + tps + "] VL:" + VL);
                       }
                        Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " (Range:" + distance + ") [Ping:" + ping + " TPS:" + tps + "] VL:" + VL);
                    } else {
                        if(API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " (Range:" + distance + ") [Ping:" + ping + " TPS:" + tps + "] VL:" + VL);
                        }
                    }
                }
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    all.sendMessage(prefix + "§a [CONSOLE] §7" + player + " §7was kicked for Reach hacking§8. §7VL§8: §7" + API.instance.VL.get(Bukkit.getPlayer(player).getUniqueId()));
                    Bukkit.getConsoleSender().sendMessage(prefix + "§a [CONSOLE] §7" + player + " §7was kicked for Reach hacking§8. §7VL§8: §7" + API.instance.VL.get(Bukkit.getPlayer(player).getUniqueId()));
                }
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kickcommand.replace("%name%", player).replace("%prefix%", prefix).replace("%reach%", distance));
        } else {
            API.instance.VL.put(Bukkit.getPlayer(player).getUniqueId(), API.instance.VL.get(Bukkit.getPlayer(player).getUniqueId()) + 1);

            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                    if (consolelog) {
                        if(API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " (Range:" + distance + ") [Ping:" + ping + " TPS:" + tps + "] VL:" + VL);
                        }
                        Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " (Range:" + distance + ") [Ping:" + ping + " TPS:" + tps + "] VL:" + VL);
                    } else {
                        if(API.instance.verbosemode.contains(all.getName())) {
                            all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " (Range:" + distance + ") [Ping:" + ping + " TPS:" + tps + "] VL:" + VL);
                        }
                    }
                }
            }
        }
    }
}
