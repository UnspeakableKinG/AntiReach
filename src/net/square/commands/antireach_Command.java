package net.square.commands;

import net.square.api.API;
import net.square.config.ConfigManager;
import net.square.main.AntiReach;
import net.square.utils.TPSManager;
import net.square.utils.TYPE;
import net.square.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class antireach_Command implements CommandExecutor {

    public static ArrayList<String> accepted = new ArrayList<>();

    static double tps = TPSManager.getTPS();
    static double tpps = Math.round((1.0D - tps / 20.0D) * 100.0D);

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(API.instance.admin)) {

                switch (args.length) {
                    case 0:
                        p.sendMessage(API.instance.prefix + " §8/§cac <reload>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <verbose>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <bypass> <name>");
                        break;
                    case 1:
                        if (args[0].equalsIgnoreCase("reload")) {

                            Bukkit.getPluginManager().disablePlugin(AntiReach.instance);
                            Bukkit.getPluginManager().enablePlugin(AntiReach.instance);

                            if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                Utils.instance.consoleMessage("[AntiReach] Server reloaded", TYPE.MESSAGE);
                            } else {
                                Utils.instance.consoleMessage("[AntiReach] Server neugeladen", TYPE.MESSAGE);
                            }
                            if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                p.sendMessage(API.instance.prefix + "§7 The server was sucessfully reloaded");
                            } else {
                                p.sendMessage(API.instance.prefix + "§7 Der Server wurde erfolgreich neugeladen");
                            }

                        } else if (args[0].equalsIgnoreCase("verbose")) {

                            if (API.instance.verbosemode.contains(p.getName())) {
                                API.instance.verbosemode.remove(p.getName());
                                if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                    p.sendMessage(API.instance.prefix + "§7 You have §cleft §7the verbose-mode");
                                } else {
                                    p.sendMessage(API.instance.prefix + "§7 Du hast den Verbose-mode §cverlassen");
                                }

                            } else {

                                API.instance.verbosemode.add(p.getName());
                                if (ConfigManager.instance.fileconfigfile.get("Language.lang").equals("EN")) {
                                    p.sendMessage(API.instance.prefix + "§7 You have §ajoin §7the verbose-mode");
                                } else {
                                    p.sendMessage(API.instance.prefix + "§7 Du hast den Verbose-mode §abetreten");
                                }

                            }
                        } else if(args[0].equalsIgnoreCase("bypass")) {

                            p.sendMessage(API.instance.prefix + " §8/§cac <bypass> <name>");

                        } else {
                            p.sendMessage(API.instance.prefix + " §8/§cac <reload>");
                            p.sendMessage(API.instance.prefix + " §8/§cac <verbose>");
                            p.sendMessage(API.instance.prefix + " §8/§cac <bypass> <name>");
                        }
                        break;

                    case 2:

                        Player target = Bukkit.getPlayer(args[1]);

                        if (target != null) {

                            if (API.instance.bypassmode.contains(target.getName())) {
                                API.instance.bypassmode.remove(target.getName());
                                p.sendMessage(API.instance.prefix + "§7 Player §e" + target.getName() + "§7 was §cremoved §7from bypass-list§8.");
                            } else {
                                API.instance.bypassmode.add(target.getName());
                                p.sendMessage(API.instance.prefix + "§7 Player §e" + target.getName() + "§7 was §aadded §7to bypass-list§8.");
                            }

                        } else {
                            p.sendMessage(API.instance.prefix + "§c The player does not exist!");
                        }

                        break;

                }

            } else {
                if (API.instance.ownmessage) {
                    p.sendMessage(API.instance.noperm);
                } else {
                    p.sendMessage(API.instance.prefix + " §7Running §cAntiReach §8(§c" + AntiReach.instance.getDescription().getVersion() + "§8)§7 by SquareCode");
                    p.sendMessage(API.instance.prefix + " §7Id§8: §7AntiReachDE / " + API.instance.ID.get(p.getUniqueId()));
                    p.sendMessage(API.instance.prefix + " §7Licensed to§8: §7" + API.instance.license);
                }
            }

        } else {
            sender.sendMessage(API.instance.Stripline);
            sender.sendMessage(API.instance.cpr + "§7Running §cAntiReach §8(§c" + AntiReach.instance.getDescription().getVersion() + "§8)§7 by SquareCode");
            sender.sendMessage(API.instance.cpr + "§7Id§8: §7AntiReachDE / CONSOLE");
            sender.sendMessage(API.instance.cpr + "§7Licensed to§8: §7" + API.instance.license);
            sender.sendMessage(API.instance.cpr + "§7Date/Time§8: §c" + API.getCurrentDate() + "§8/§c" + API.getCurrentTime());
            sender.sendMessage(API.instance.cpr + "§7TPS§8: §c" + TPSManager.getTPS());
            sender.sendMessage(API.instance.Stripline);
        }
        return true;
    }
}
