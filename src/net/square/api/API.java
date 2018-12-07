package net.square.api;

import net.square.check.*;
import net.square.commands.antireach_Command;
import net.square.config.ConfigManager;
import net.square.event.JoinListener;
import net.square.event.QuitListener;
import net.square.main.AntiReach;
import net.square.utils.IDGen;
import net.square.utils.TPSManager;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.omg.CORBA.INTERNAL;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class API {

    /*-------------------------------------------------------------------------------------------------------*/

    /**
     * This is the main class in the system.
     * Everything is controlled and everything set.
     * If this class is missing, the whole plugin will not work anymore.
     */

    public static API instance;
    public HashMap<UUID, String> ID = new HashMap<>();
    public ArrayList<String> verbosemode = new ArrayList<>();
    public static HashMap<UUID, Integer> VLReach = new HashMap<UUID, Integer>();
    public String cpr = "§8| §cINFO §8|  ";
    public String Stripline = cpr + "§8=========================( §cANTIREACH §8)=========================";
    public String Stripline2 = cpr + "§8=========================( §cANTIREACH §8)=========================";
    public String bypass;
    public String admin;
    public String verbose;
    public String prefix;
    public String noperm;
    public String reachcommand;
    public Integer reachlevel;
    public double MAX_REACH_A;
    public double MAX_REACH_B;
    public double MAX_REACH_C;
    public double MAX_REACH_D;
    public double MAX_REACH_E;
    public double MAX_REACH_F;
    public boolean consolelog;
    public boolean ownmessage;
    public boolean resetpitch;
    public boolean REACH_A;
    public boolean REACH_B;
    public boolean REACH_C;
    public boolean REACH_D;
    public boolean REACH_E;
    public boolean REACH_F;
    /*-------------------------------------------------------------------------------------------------------*/

    public void loadValues() {

        /**
         * From this method, all values ​​are loaded from the Config.yml.
         * The config is created in the ConfigManager.
         * (net.square.config.ConfigManager)
         */

        prefix = ConfigManager.instance.fileconfigfile.getString("Prefix").replace("<s>", "┃").replace("<p>", "●").replace("<pk>", "•").replace(">>", "»").replace("<<", "«").replace("<st>", "×").replace("&", "§").replace("<d>", "►").replace("<sb>", "▎");
        noperm = ConfigManager.instance.fileconfigfile.getString("General.NoPermissions").replace("&", "§").replace("%prefix%", prefix);
        bypass = ConfigManager.instance.fileconfigfile.getString("Permissions.bypass");
        admin = ConfigManager.instance.fileconfigfile.getString("Permissions.admin");
        verbose = ConfigManager.instance.fileconfigfile.getString("Permissions.verbose");
        consolelog = ConfigManager.instance.fileconfigfile.getBoolean("Settings.console-log");
        ownmessage = ConfigManager.instance.fileconfigfile.getBoolean("General.own-permissions-message");
        resetpitch = ConfigManager.instance.fileconfigfile.getBoolean("Settings.reset-player-pitch");
        MAX_REACH_A = ConfigManager.instance.valuesfileconf.getDouble("Checks.A.maxreach");
        MAX_REACH_B = ConfigManager.instance.valuesfileconf.getDouble("Checks.B.maxreach");
        MAX_REACH_C = ConfigManager.instance.valuesfileconf.getDouble("Checks.C.maxreach");
        MAX_REACH_D = ConfigManager.instance.valuesfileconf.getDouble("Checks.D.maxreach");
        MAX_REACH_E = ConfigManager.instance.valuesfileconf.getDouble("Checks.E.maxreach");
        MAX_REACH_F = ConfigManager.instance.valuesfileconf.getDouble("Checks.F.maxinteract");
        REACH_A = ConfigManager.instance.valuesfileconf.getBoolean("Checks.A.enable");
        REACH_B = ConfigManager.instance.valuesfileconf.getBoolean("Checks.B.enable");
        REACH_C = ConfigManager.instance.valuesfileconf.getBoolean("Checks.C.enable");
        REACH_D = ConfigManager.instance.valuesfileconf.getBoolean("Checks.D.enable");
        REACH_E = ConfigManager.instance.valuesfileconf.getBoolean("Checks.E.enable");
        REACH_F = ConfigManager.instance.valuesfileconf.getBoolean("Checks.F.enable");
        reachlevel = ConfigManager.instance.fileconfigfile.getInt("Settings.Reach.min-level-to-kick");
        reachcommand = ConfigManager.instance.fileconfigfile.getString("Settings.Reach.kick-command");
    }

    public void onStart() {
        // on start methode
        Utils.instance.consoleMessage(Stripline, TYPE.MESSAGE);
        Utils.instance.consoleMessage(cpr + "§7Trying to load §cAntiReach§8...", TYPE.MESSAGE);
        ConfigManager.instance.createConfig();
        ConfigManager.instance.loadlanguageFile();
        ConfigManager.instance.setDefaultLanguages();
        ConfigManager.instance.loadvaluesFile();
        ConfigManager.instance.setDefaultValues();
        Utils.instance.consoleMessage(cpr + "§7Load files§8...", TYPE.MESSAGE);
        checkProtocolLib();
        Utils.instance.consoleMessage(cpr + "", TYPE.EMPTY);
        starts();
    }

    public void starts() {
        if(ConfigManager.instance.langfileconf.get("Language.lang").equals("DE") || ConfigManager.instance.langfileconf.get("Language.lang").equals("EN")) {

            this.loadValues();
            this.setDefault();
            this.register();
            Utils.instance.consoleMessage(cpr + "§7Starting §cModuleManager§8...", TYPE.MESSAGE);
            Utils.instance.consoleMessage(cpr + "", TYPE.EMPTY);
            ModuleManager.instance.loadModules();
            Utils.instance.consoleMessage(cpr + "", TYPE.EMPTY);
            Utils.instance.consoleMessage(cpr + "§7Stopping §cModuleManager§8...", TYPE.MESSAGE);
            Utils.instance.consoleMessage(cpr + "", TYPE.EMPTY);
            AntiReach.instance.current = System.currentTimeMillis() - AntiReach.instance.current;
            Utils.instance.consoleMessage(cpr + "§cAntiReach §7loaded sucessfully§8! §8(§c" + ConfigManager.instance.langfileconf.get("Language.lang") + "§7, §c" + AntiReach.current + "ms§7, §c" + this.getCurrentTime() + "§7, §c" + this.getCurrentDate() + "§8)", TYPE.MESSAGE);
            Utils.instance.consoleMessage(Stripline2, TYPE.MESSAGE);
            setDefaults();
            startTask();
            TPSManager.startTPSChecking();

        } else {

            Utils.instance.consoleMessage("§cPlugin cant load! Uknown Language§8: §c"+ConfigManager.instance.langfileconf.get("Language.lang"), TYPE.ERROR);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch(InterruptedException error) {}
            Bukkit.shutdown();
        }
    }

    public static String getCurrentTime() {
        final SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        final Date now = new Date();
        final String strDate = sdfDate.format(now);
        return strDate;
    }

    public void setDefaults() {
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (!ID.containsKey(all.getUniqueId())) {
                ID.put(all.getUniqueId(), IDGen.instance.generateRandom(5, true, true));
            }
            if (!API.VLReach.containsKey(all.getUniqueId())) {
                API.VLReach.put(all.getUniqueId(), 0);
            }
        }
    }

    public static String getCurrentDate() {
        final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        final Date now = new Date();
        final String strDate = sdfDate.format(now);
        return strDate;
    }

    public void checkProtocolLib() {
        if (this.ProtocolLIBStatus()) {
            Bukkit.getConsoleSender().sendMessage(cpr + "§7Found ProtocolLib!");
        } else {
            Bukkit.getConsoleSender().sendMessage(cpr + "§7ProtocolLib was not found!");
        }
    }

    private boolean ProtocolLIBStatus() {
        return Bukkit.getPluginManager().getPlugin("ProtocolLib") != null;
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

    private void startTask() {
        Bukkit.getScheduler().runTaskTimer(AntiReach.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    API.VLReach.clear();
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(admin)) {
                            if (API.instance.verbosemode.contains(all.getName())) {
                                if(ConfigManager.instance.langfileconf.get("Language.lang").equals("EN")) {
                                    all.sendMessage(prefix + " §7VL level has ben resettet");
                                } else {
                                    all.sendMessage(prefix + " §7Das VL-Level wurde zurückgesetzt");
                                }
                            }
                        }
                    }

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!VLReach.containsKey(all.getUniqueId())) {
                            VLReach.put(all.getUniqueId(), 0);
                        }
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        }, 0, 1200);
    }

    public void setDefault() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!API.VLReach.containsKey(all.getUniqueId())) {
                API.VLReach.put(all.getUniqueId(), 0);
            }
        }
    }

    public void setInstance() {
        instance = this;
    }

    public void pokeReach(String player, String description, String distance, int ping, double tps, ReachType type, Integer VL) {
        if (Bukkit.getPlayer(player) != null) {
            if (API.VLReach.containsKey(Bukkit.getPlayer(player).getUniqueId())) {
                if (API.VLReach.get(Bukkit.getPlayer(player).getUniqueId()) == ConfigManager.instance.fileconfigfile.getInt("Settings.Reach.min-level-to-kick")) {
                    VLReach.remove(Bukkit.getPlayer(player).getUniqueId());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reachcommand.replace("%name%", player).replace("%prefix%", prefix).replace("&", "§").replace("%reach%", distance));
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(admin)) {
                            if(ConfigManager.instance.langfileconf.get("Language.lang").equals("EN")) {
                                all.sendMessage(prefix + "§a [CONSOLE] §e" + player + "§7 was kicked for Reach");
                            } else {
                                all.sendMessage(prefix + "§a [KONSOLE] §e" + player + "§7 wurde für Reach gekickt");
                            }
                        }
                    }
                    if(ConfigManager.instance.langfileconf.get("Language.lang").equals("EN")) {
                        Bukkit.getConsoleSender().sendMessage(prefix + "§a [CONSOLE] §e" + player + "§7 was kicked for Reach");
                    } else {
                        Bukkit.getConsoleSender().sendMessage(prefix + "§a [KONSOLE] §e" + player + "§7 wurde für Reach gekickt");
                    }


                } else {

                    API.VLReach.put(Bukkit.getPlayer(player).getUniqueId(), VLReach.get(Bukkit.getPlayer(player).getUniqueId()) + 1);

                    if (API.instance.resetpitch) {
                        Location loc = Bukkit.getPlayer(player).getLocation();
                        loc.setPitch(-90F);
                        loc.setYaw(-90F);
                        Bukkit.getPlayer(player).teleport(loc);
                    }

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(admin) || all.hasPermission(verbose)) {
                            if (consolelog) {
                                if (API.instance.verbosemode.contains(all.getName())) {
                                    all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + " VL: " + VL + "]");
                                }
                                Bukkit.getConsoleSender().sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + " VL: " + VL + "]");
                            } else {
                                if (API.instance.verbosemode.contains(all.getName())) {
                                    all.sendMessage(prefix + " §7" + player + " §7suspected for Reach: " + description + " [Range:" + distance + " Ping:" + ping + " TPS:" + tps + " Check: " + type + " VL: " + VL + "]");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
