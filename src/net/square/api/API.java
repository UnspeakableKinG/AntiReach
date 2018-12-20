package net.square.api;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.square.check.*;
import net.square.commands.antireach_Command;
import net.square.config.ConfigManager;
import net.square.event.JoinListener;
import net.square.event.QuitListener;
import net.square.main.AntiReach;
import net.square.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
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
    public static HashMap<UUID, Integer> VLReach = new HashMap<UUID, Integer>();
    public ArrayList<String> verbosemode = new ArrayList<>();
    public ArrayList<String> bypassmode = new ArrayList<>();
    public String cpr = "§8| §cINFO §8|  ";
    public String Stripline = cpr + "§8=========================( §cANTIREACH §8)=========================";
    public String Stripline2 = cpr + "§8=========================( §cANTIREACH §8)=========================";
    public String bypass;
    public String admin;
    public String verbose;
    public String prefix;
    public String noperm;
    public String check;
    public String reachcommand;
    public Integer reachlevel;
    public double MAX_REACH_A;
    public double MAX_REACH_B;
    public double MAX_REACH_C;
    public double MAX_REACH_D;
    public double MAX_REACH_E;
    public double MAX_REACH_F;
    public double MAX_REACH_G;
    public double MAX_REACH_H;
    public double MAX_REACH_I;
    public boolean consolelog;
    public boolean startlog;
    public boolean ownmessage;
    public boolean resetpitch;
    public boolean REACH_A;
    public boolean REACH_B;
    public boolean REACH_C;
    public boolean REACH_D;
    public boolean REACH_E;
    public boolean REACH_F;
    public boolean REACH_G;
    public boolean REACH_H;
    public boolean REACH_I;
    public boolean logFile;
    public boolean OWN_KICK_COMMAND;
    public boolean checkUpdate;
    public String license;
    public String licenseMain = "public";
    public String address;

    public void loadValues() {

        /**
         * From this method, all values ​​are loaded from the Config.yml.
         * The config is created in the ConfigManager.
         * (net.square.config.ConfigManager)
         */

        prefix = ConfigManager.instance.fileconfigfile.getString("Prefix").replace("<s>", "┃").replace("<p>", "●").replace("<pk>", "•").replace(">>", "»").replace("<<", "«").replace("<st>", "×").replace("&", "§").replace("<d>", "►").replace("<sb>", "▎").replace("<e>", "▪");
        noperm = ConfigManager.instance.fileconfigfile.getString("General.NoPermissions").replace("&", "§").replace("%prefix%", prefix);
        bypass = ConfigManager.instance.fileconfigfile.getString("Permissions.bypass");
        admin = ConfigManager.instance.fileconfigfile.getString("Permissions.admin");
        verbose = ConfigManager.instance.fileconfigfile.getString("Permissions.verbose");
        check = ConfigManager.instance.fileconfigfile.getString("Messages.check").replace("&", "§").replace("%prefix%", prefix);;
        consolelog = ConfigManager.instance.fileconfigfile.getBoolean("Settings.console-log");
        ownmessage = ConfigManager.instance.fileconfigfile.getBoolean("General.own-permissions-message");
        resetpitch = ConfigManager.instance.fileconfigfile.getBoolean("Settings.reset-player-pitch");
        MAX_REACH_A = ConfigManager.instance.fileconfigfile.getDouble("Checks.A.maxreach");
        MAX_REACH_B = ConfigManager.instance.fileconfigfile.getDouble("Checks.B.maxreach");
        MAX_REACH_C = ConfigManager.instance.fileconfigfile.getDouble("Checks.C.maxreach");
        MAX_REACH_D = ConfigManager.instance.fileconfigfile.getDouble("Checks.D.maxreach");
        MAX_REACH_E = ConfigManager.instance.fileconfigfile.getDouble("Checks.E.maxreach");
        MAX_REACH_F = ConfigManager.instance.fileconfigfile.getDouble("Checks.F.maxinteract");
        MAX_REACH_G = ConfigManager.instance.fileconfigfile.getDouble("Checks.G.maxreach");
        MAX_REACH_H = ConfigManager.instance.fileconfigfile.getDouble("Checks.H.maxreach");
        MAX_REACH_I = ConfigManager.instance.fileconfigfile.getDouble("Checks.I.maxreach");
        REACH_A = ConfigManager.instance.fileconfigfile.getBoolean("Checks.A.enable");
        REACH_B = ConfigManager.instance.fileconfigfile.getBoolean("Checks.B.enable");
        REACH_C = ConfigManager.instance.fileconfigfile.getBoolean("Checks.C.enable");
        REACH_D = ConfigManager.instance.fileconfigfile.getBoolean("Checks.D.enable");
        REACH_E = ConfigManager.instance.fileconfigfile.getBoolean("Checks.E.enable");
        REACH_F = ConfigManager.instance.fileconfigfile.getBoolean("Checks.F.enable");
        REACH_G = ConfigManager.instance.fileconfigfile.getBoolean("Checks.G.enable");
        REACH_H = ConfigManager.instance.fileconfigfile.getBoolean("Checks.H.enable");
        REACH_I = ConfigManager.instance.fileconfigfile.getBoolean("Checks.I.enable");
        logFile = ConfigManager.instance.fileconfigfile.getBoolean("Settings.logFile");
        startlog = ConfigManager.instance.fileconfigfile.getBoolean("Settings.startLog");
        checkUpdate = ConfigManager.instance.fileconfigfile.getBoolean("Settings.checkUpdate");
        OWN_KICK_COMMAND = ConfigManager.instance.fileconfigfile.getBoolean("Settings.Reach.own-kick-command");
        reachlevel = ConfigManager.instance.fileconfigfile.getInt("Settings.Reach.min-level-to-kick");
        reachcommand = ConfigManager.instance.fileconfigfile.getString("Settings.Reach.kick-command");
    }

    public void onStart() throws UnknownHostException {
        // on start methode
        try {
            license = read("http://blackception.com/spigot/lizenzen/public.html");
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }

        if (license.equals(licenseMain)) {
            try {
                address = InetAddress.getLocalHost().getHostAddress().toString();
            } catch (UnknownHostException exception) {
            }
            Utils.instance.consoleMessage(Stripline, TYPE.MESSAGE);
            Utils.instance.consoleMessage(cpr + "§7Trying to load §cAntiReach§8...", TYPE.MESSAGE);
            ConfigManager.instance.createConfig();
            Utils.instance.consoleMessage(cpr + "§7Load files§8...", TYPE.MESSAGE);
            checkProtocolLib();
            Utils.instance.consoleMessage(cpr + "", TYPE.EMPTY);
            starts();
            if (checkUpdate) {
                checkUpdate();
            }
        } else {
            Utils.instance.consoleMessage(cpr + "§cThe License is wrong!", TYPE.MESSAGE);
            Utils.instance.consoleMessage(cpr + "§cPlease contact the system-developer if you believe that is an error", TYPE.MESSAGE);
            Utils.instance.consoleMessage(cpr + "§cLicense: " + license, TYPE.MESSAGE);
            Bukkit.getPluginManager().disablePlugin(AntiReach.instance);
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
            }
        }
    }

    private void checkUpdate() {
        try {
            final URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=58495");
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                final String lastVersion = reader.readLine();
                if (!AntiReach.instance.getDescription().getVersion().equalsIgnoreCase(lastVersion)) {
                    Utils.instance.consoleMessage(cpr + "§7A new version is available! Latets-version is §c" + lastVersion + " §7and you are on §c" + AntiReach.instance.getDescription().getVersion(), TYPE.MESSAGE);
                    Utils.instance.consoleMessage(cpr + "§7You can download it on: §chttps://www.spigotmc.org/resources/%E2%97%8F-antireach-advanced-antireach-solution.58495/", TYPE.MESSAGE);
                }
            }
        } catch (IOException ex) {
        }
    }

    public void checkUpdateClient(final Player player) {
        try {
            final URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=58495");
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                final String lastVersion = reader.readLine();
                if (!AntiReach.instance.getDescription().getVersion().equalsIgnoreCase(lastVersion)) {

                    TextComponent spigotmc = new TextComponent(prefix + "§7 You can download it on: §cSpigotMC");
                    spigotmc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(prefix + " §7§oClick me to open your Browser").create()));
                    spigotmc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/%E2%97%8F-antireach-advanced-antireach-solution.58495/"));

                    player.sendMessage(prefix + "§7 New Update! Latest§8: §c" + lastVersion + " §7Your§8: §c" + AntiReach.instance.getDescription().getVersion());
                    player.spigot().sendMessage(spigotmc);
                }
            }
        } catch (IOException ex) {
        }
    }

    public void starts() {
        if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("DE") || ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {

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
            Utils.instance.consoleMessage(cpr + "§7License§8: §c" + license + " §7bound to§8: §c" + address, TYPE.MESSAGE);
            Utils.instance.consoleMessage(cpr + "§cAntiReach §7loaded sucessfully§8! §8(§c" + ConfigManager.instance.fileconfigfile.get("Language.lang") + "§7, §c" + AntiReach.current + "ms§7, §c" + this.getCurrentTime() + "§7, §c" + this.getCurrentDate() + "§8)", TYPE.MESSAGE);
            Utils.instance.consoleMessage(Stripline2, TYPE.MESSAGE);
            if (startlog) {
                String date = API.getCurrentDate();
                String time = API.getCurrentTime().replace(":", "-");
                StorageUtils.logStart(date + "/" + time + " | AntiReach started sucessfully in " + String.valueOf(AntiReach.current) + "ms!");
                StorageUtils.logStart(date + "/" + time + " | Enabled Checks: " + getLoaded().replace("§c", "").replace("§8", ""));
                StorageUtils.logStart(date + "/" + time + " | Loaded on License: " + license);
                StorageUtils.logStart(date + "/" + time + " | ProtocolLib-State: " + ProtocolLIBStatus());
                StorageUtils.logStart(date + "/" + time + " | Loaded-language: " + ConfigManager.instance.fileconfigfile.get("Language.lang"));
                StorageUtils.logStart(date + "/" + time + " | Date: " + date);
                StorageUtils.logStart(date + "/" + time + " | Time: " + time);
                StorageUtils.logStart("");
            }
            setDefaults();
            startTask();
            TPSManager.startTPSChecking();

        } else {

            Utils.instance.consoleMessage("§cPlugin cant load! Uknown Language§8: §c" + ConfigManager.instance.fileconfigfile.get("Language.lang"), TYPE.ERROR);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException error) {
            }
            Bukkit.shutdown();
        }
    }

    public String getLoaded() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Module.registered.size(); i++) {
            final Module module = Module.registered.get(i);
            builder.append(String.format("§c%s%s", i != 0 ? "§8, §c" : "", module.getBuchstaben()));
        }
        return builder.toString();
    }

    @Getter
    public static String getCurrentTime() {
        final SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");
        final Date now = new Date();
        final String strDate = sdfDate.format(now);
        return strDate;
    }

    public void setDefaults() {
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (!ID.containsKey(all.getUniqueId())) {
                ID.put(all.getUniqueId(), "ar-"+IDGen.instance.generateRandom(5, true, false)+"-"+IDGen.instance.generateRandom(5, true, false)+"-"+IDGen.instance.generateRandom(5, true, false));
            }
            if (!API.VLReach.containsKey(all.getUniqueId())) {
                API.VLReach.put(all.getUniqueId(), 0);
            }
        }
    }

    @Getter
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

    @Getter
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

    private static String read(final String url) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final URLConnection connection = new URL(url).openConnection();

        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            int i;
            while ((i = reader.read()) != -1) builder.append((char) i);
        } finally {
            return builder.toString();
        }
    }

    public void kickPlayer(String player, String reason, String distance, ReachType type) {
        Player p = Bukkit.getPlayer(player);
        int ping = ((CraftPlayer) Bukkit.getPlayer(player)).getHandle().ping;
        double tps = TPSManager.getTPS();

        try {
            if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                p.kickPlayer(
                        prefix + " §7Connection refused by server\n" +
                                "§8§m---------------------------------------\n" +
                                "\n" +
                                "§7Reason §8➜ §c" + reason + " §8(§c" + type + "§8)\n" +
                                "§7Ping §8➜ §c" + ping + "\n" +
                                "§7TPS §8➜ §c" + String.valueOf(tps).substring(0, 4) + "\n" +
                                "§7Distance §8➜ §c" + distance + "\n" +
                                "\n" +
                                "§8§m---------------------------------------\n" +
                                "§cAntiReach §8(§c" + AntiReach.instance.getDescription().getVersion() + "§8) §7by SquareCode");
            } else {
                p.kickPlayer(
                        prefix + " §7Verbindung zum Server unterbrochen\n" +
                                "§8§m---------------------------------------\n" +
                                "\n" +
                                "§7Grund §8➜ §c" + reason + " §8(§c" + type + "§8)\n" +
                                "§7Ping §8➜ §c" + ping + "\n" +
                                "§7TPS §8➜ §c" + String.valueOf(tps).substring(0, 4) + "\n" +
                                "§7Distanz §8➜ §c" + distance + "\n" +
                                "\n" +
                                "§8§m---------------------------------------\n" +
                                "§cAntiReach §8(§c" + AntiReach.instance.getDescription().getVersion() + "§8) §7von SquareCode");
            }
        } catch (Exception error) {
            Utils.instance.consoleMessage("§cCant kick player!", TYPE.ERROR);
            error.printStackTrace();
        }
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
                                if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                    all.sendMessage(prefix + " §7VL level has ben resettet");
                                } else {
                                    all.sendMessage(prefix + " §7Das VL-Level wurde zurückgesetzt");
                                }
                            }
                        }
                    }
                    try {
                        license = read("http://blackception.com/spigot/lizenzen/public.html");
                    } catch (IOException ex1) {
                        ex1.printStackTrace();
                    }
                    if (!license.equals(licenseMain)) {
                        Utils.instance.consoleMessage("§cPlugin disabled! License was changed!", TYPE.ERROR);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission(admin)) {
                                if (API.instance.verbosemode.contains(all.getName())) {
                                    if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                        all.sendMessage(prefix + " §7Plugin disabled! License was changed!");
                                    } else {
                                        all.sendMessage(prefix + " §7Das Plugin wurde deaktiviert! Lizenz wurde geändert!");
                                    }
                                }
                            }
                        }
                        PluginManager pm = Bukkit.getPluginManager();
                        pm.disablePlugin(AntiReach.instance);
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
                    if (logFile) {
                        StorageUtils.log(Bukkit.getPlayer(player), API.getCurrentDate() + " / " + API.getCurrentTime() + " | " + Bukkit.getPlayer(player).getName() + " was kicked for Reach. His last reach was " + distance + " on ping " + ping + " and tps " + tps);
                        if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                            Utils.instance.consoleMessage(prefix + " §a[CONSOLE] §7Sucessfully writing log for " + player, TYPE.MESSAGE);
                        } else {
                            Utils.instance.consoleMessage(prefix + " §a[KONSOLE] §7Log für " + player + "wurde erfolgreich geschrieben", TYPE.MESSAGE);
                        }
                    }
                    VLReach.remove(Bukkit.getPlayer(player).getUniqueId());
                    if (OWN_KICK_COMMAND) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reachcommand.replace("%name%", player).replace("%prefix%", prefix).replace("&", "§").replace("%reach%", distance));
                    } else {
                        kickPlayer(player, "Reach", distance, type);
                    }
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(admin)) {
                            if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                all.sendMessage(prefix + "§a [CONSOLE] §e" + player + "§7 was kicked for Reach");
                            } else {
                                all.sendMessage(prefix + "§a [KONSOLE] §e" + player + "§7 wurde für Reach gekickt");
                            }
                        }
                    }
                    if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
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
                                    all.sendMessage(check.replace("%player%", player).replace("%description%", description).replace("%reach%", distance).replace("%ping%", String.valueOf(ping)).replace("%tps%", String.valueOf(tps)).replace("%type%", String.valueOf(type)).replace("%vl%", String.valueOf(VL)));
                                }
                                Bukkit.getConsoleSender().sendMessage(check.replace("%player%", player).replace("%description%", description).replace("%reach%", distance).replace("%ping%", String.valueOf(ping)).replace("%tps%", String.valueOf(tps)).replace("%type%", String.valueOf(type)).replace("%vl%", String.valueOf(VL)));
                            } else {
                                if (API.instance.verbosemode.contains(all.getName())) {
                                    all.sendMessage(check.replace("%player%", player).replace("%description%", description).replace("%reach%", distance).replace("%ping%", String.valueOf(ping)).replace("%tps%", String.valueOf(tps)).replace("%type%", String.valueOf(type)).replace("%vl%", String.valueOf(VL)));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
