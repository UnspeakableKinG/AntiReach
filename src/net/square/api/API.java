package net.square.api;

import net.square.check.Check;
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


import java.util.HashMap;
import java.util.UUID;

public class API {

    public static API instance;
    public HashMap<UUID, Integer> VL = new HashMap<>();
    public String cpr = "§8| §cINFO §8|  ";
    public String cprF = "§8| §cERROR §8|  ";
    public String Stripline = "§8=========================| §cANTIREACH §8|=========================";
    public String bypass;
    public String admin;
    public String verbose;
    public String prefix;
    public String noperm;
    public String noplayer;
    public String kickMessage;
    public String list;
    public String plugin;
    public String allmessagem;
    public boolean consolelog;
    public boolean allmessage;
    public boolean ownmessage;

    public void loadValues() {
        prefix = ConfigManager.instance.fileconfig.getString("Prefix").replace("<s>", "┃").replace("<p>", "●").replace("<pk>", "•").replace(">>", "»").replace("<<", "«").replace("<st>", "×").replace("&", "§");
        noperm = ConfigManager.instance.fileconfig.getString("General.NoPermissions").replace("&", "§").replace("%prefix%", prefix);
        noplayer = ConfigManager.instance.fileconfig.getString("General.NoPlayer").replace("&", "§").replace("%prefix%", prefix);
        kickMessage = ConfigManager.instance.fileconfig.getString("General.kickMessage").replace("&", "§").replace("%prefix%", prefix);
        bypass = ConfigManager.instance.fileconfig.getString("Permissions.bypass");
        admin = ConfigManager.instance.fileconfig.getString("Permissions.admin");
        verbose = ConfigManager.instance.fileconfig.getString("Permissions.verbose");
        list = ConfigManager.instance.fileconfig.getString("Messages.List").replace("&", "§").replace("%prefix%", prefix);
        plugin = ConfigManager.instance.fileconfig.getString("Messages.Plugin").replace("&", "§").replace("%prefix%", prefix);
        consolelog = ConfigManager.instance.fileconfig.getBoolean("Settings.console-log");
        allmessage = ConfigManager.instance.fileconfig.getBoolean("Settings.all-message");
        allmessagem = ConfigManager.instance.fileconfig.getString("Settings.all-message-message").replace("&", "§").replace("%prefix%", prefix);
        ownmessage = ConfigManager.instance.fileconfig.getBoolean("General.own-permissions-message");
    }

    public void onStart() {
        // on start methode
        Utils.instance.consoleMessage(Stripline, TYPE.MESSAGE);
        Utils.instance.consoleMessage("§7Plugin trying to start...", TYPE.MESSAGE);
        ConfigManager.instance.createConfig();
        this.startSession();
    }

    public void startSession() {
        // start session method
        this.loadValues();
        this.setDefault();
        this.register();

        Utils.instance.consoleMessage("§7Plugin sucessfully loaded", TYPE.MESSAGE);
        Utils.instance.consoleMessage(Stripline, TYPE.MESSAGE);
    }

    public void register() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), AntiReach.instace);
        pm.registerEvents(new QuitListener(), AntiReach.instace);
        pm.registerEvents(new Check(), AntiReach.instace);

        AntiReach.instace.getCommand("antireach").setExecutor(new antireach_Command());
    }

    public void setDefault() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!VL.containsKey(all.getUniqueId())) {
                VL.put(all.getUniqueId(), 0);
            }
        }
    }

    public void setInstance() {
        instance = this;
    }

    public void pokeReach(String player, String description, String distance, int VL, int ping, double tps, String safe) {
        if (API.instance.VL.get(Bukkit.getPlayer(player).getUniqueId()).equals(5)) {
            if (consolelog) {
                if (allmessage) {
                    Bukkit.getPlayer(player).kickPlayer(kickMessage.replace("%reach%", distance));
                    Bukkit.getConsoleSender().sendMessage(cpr + "§e" + player + "§7 was kicked for reach. (reach:" + distance + ")");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(allmessagem.replace("%name%", player).replace("%reach%", distance));
                    }
                } else {
                    Bukkit.getPlayer(player).kickPlayer(kickMessage.replace("%reach%", distance));
                    Bukkit.getConsoleSender().sendMessage(cpr + "§e" + player + "§7 was kicked for reach. (reach:" + distance + ")");
                }
            } else {
                if (allmessage) {
                    Bukkit.getPlayer(player).kickPlayer(kickMessage.replace("%reach%", distance));
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(allmessagem.replace("%name%", player).replace("%reach%", distance));
                    }
                } else {
                    Bukkit.getPlayer(player).kickPlayer(kickMessage.replace("%reach%", distance));
                }
            }

        } else {
            API.instance.VL.put(Bukkit.getPlayer(player).getUniqueId(), API.instance.VL.get(Bukkit.getPlayer(player).getUniqueId()) + 1);
        }
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission(API.instance.verbose) || all.hasPermission(API.instance.admin)) {
                if (consolelog) {
                    all.sendMessage(prefix + " §7" + player + "§7 failed Reach: " + description + " (Range:" + distance + ") [ping:" + ping + " tps:" + tps + " safe:" + safe + "] VL:" + VL);
                    Bukkit.getConsoleSender().sendMessage(cpr + " §7" + player + "§7 failed Reach: " + description + " (Range:" + distance + ") [ping:" + ping + " tps:" + tps + " safe:" + safe + "] VL:" + VL);
                } else {
                    all.sendMessage(prefix + " §7" + player + "§7 failed Reach: " + description + " (Range:" + distance + ") [ping:" + ping + " tps:" + tps + " safe:" + safe + "] VL:" + VL);
                }
            }
        }
    }
}
