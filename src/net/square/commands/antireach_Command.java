package net.square.commands;

import net.square.api.API;
import net.square.config.ConfigManager;
import net.square.main.AntiReach;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class antireach_Command implements CommandExecutor {

    public static ArrayList<String> accepted = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(API.instance.admin)) {
                if (args.length == 0) {

                    p.sendMessage(API.instance.prefix + " §8/§cac <reload>");
                    p.sendMessage(API.instance.prefix + " §8/§cac <verbose>");

                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {

                        Bukkit.getPluginManager().disablePlugin(AntiReach.instance);
                        Bukkit.getPluginManager().enablePlugin(AntiReach.instance);
                        Utils.instance.consoleMessage("[AntiReach] Server reloaded", TYPE.MESSAGE);
                        p.sendMessage(API.instance.plugin);

                    } else if (args[0].equalsIgnoreCase("verbose")) {

                        if (API.instance.verbosemode.contains(p.getName())) {
                            API.instance.verbosemode.remove(p.getName());
                            p.sendMessage(API.instance.prefix + "§7 You have §cleft §7the verbose-mode");

                        } else {

                            API.instance.verbosemode.add(p.getName());
                            p.sendMessage(API.instance.prefix + "§7 You have §ajoin §7the verbose-mode");

                        }
                    } else{

                        p.sendMessage(API.instance.prefix + " §8/§cac <reload>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <verbose>");

                    }
                }
            } else {
                if (API.instance.ownmessage) {
                    p.sendMessage(API.instance.noperm);
                } else {
                    p.sendMessage(API.instance.prefix + " §7Running §cAntiReach §8(§c" + AntiReach.instance.getDescription().getVersion() + "§8)§7 by SquareCode");
                    p.sendMessage(API.instance.prefix + " §7Id§8: §7AntiReachDE / " + API.instance.ID.get(p.getUniqueId()));
                    p.sendMessage(API.instance.prefix + " §7Licensed to§8: §7mc.golicraft.net");
                }
            }

        } else {
            sender.sendMessage(API.instance.noplayer);
        }

        return true;
    }
}
