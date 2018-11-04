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
                    p.sendMessage(API.instance.prefix + " §8/§cac <checkState>");
                    p.sendMessage(API.instance.prefix + " §8/§cac <resetConfig>");

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

                    } else if(args[0].equalsIgnoreCase("checkstate")) {

                        p.sendMessage(API.instance.prefix+" §7§oCheck states....");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch(InterruptedException error) {
                            error.printStackTrace();
                        }
                        p.sendMessage(API.instance.prefix+" §8§m--------------------");
                        if(API.instance.REACH_A) {
                            p.sendMessage(API.instance.prefix+" §7Reach A §f(§aENABLE§f)");
                            p.sendMessage(API.instance.prefix+" §8 ➥ §f(§c"+API.instance.MAX_REACH_A+"§f)");
                        } else {
                            p.sendMessage(API.instance.prefix+" §7Reach A §f(§cDISABLE§f)");
                        }
                        if(API.instance.REACH_B) {
                            p.sendMessage(API.instance.prefix+" §7Reach B §f(§aENABLE§f)");
                            p.sendMessage(API.instance.prefix+" §8 ➥ §f(§c"+API.instance.MAX_REACH_B+"§f)");
                        } else {
                            p.sendMessage(API.instance.prefix+" §7Reach B §f(§cDISABLE§f)");
                        }
                        if(API.instance.REACH_C) {
                            p.sendMessage(API.instance.prefix+" §7Reach C §f(§aENABLE§f)");
                            p.sendMessage(API.instance.prefix+" §8 ➥ §f(§c"+API.instance.MAX_REACH_C+"§f)");
                        } else {
                            p.sendMessage(API.instance.prefix+" §7Reach C §f(§cDISABLE§f)");
                        }
                        if(API.instance.REACH_D) {
                            p.sendMessage(API.instance.prefix+" §7Reach D §f(§aENABLE§f)");
                            p.sendMessage(API.instance.prefix+" §8 ➥ §f(§c"+API.instance.MAX_REACH_D+"§f)");
                        } else {
                            p.sendMessage(API.instance.prefix+" §7Reach D §f(§cDISABLE§f)");
                        }
                        if(API.instance.REACH_E) {
                            p.sendMessage(API.instance.prefix+" §7Reach E §f(§aENABLE§f)");
                            p.sendMessage(API.instance.prefix+" §8 ➥ §f(§c"+API.instance.MAX_REACH_E+"§f)");
                        } else {
                            p.sendMessage(API.instance.prefix+" §7Reach E §f(§cDISABLE§f)");
                        }
                        p.sendMessage(API.instance.prefix+" §8§m--------------------");


                    } else if (args[0].equalsIgnoreCase("resetConfig") || args[0].equalsIgnoreCase("accept")){

                        if(accepted.contains(p.getName())) {
                            try {
                                ConfigManager.instance.fileconfig.delete();
                            } catch (Exception error) {
                                error.printStackTrace();
                            }
                            p.sendMessage(API.instance.prefix+" §7§oSuccesfully resetet the Config-file");
                            try {
                                Bukkit.reload();
                            } catch(Exception error) {
                                error.printStackTrace();
                            }
                            accepted.remove(p.getName());
                        } else {
                            p.sendMessage(API.instance.prefix+ " §7§oAre your sure that you want to delete your config?");
                            p.sendMessage(API.instance.prefix+ " §7§oIf yes, type this command again");
                            accepted.add(p.getName());
                        }


                    } else {
                        p.sendMessage(API.instance.prefix + " §8/§cac <reload>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <verbose>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <checkState>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <resetConfig>");
                    }
                }

            } else {
                if (API.instance.ownmessage) {
                    p.sendMessage(API.instance.noperm);
                } else {
                    p.sendMessage(" ");
                    p.sendMessage("§8§m--------------------------------------------------------");
                    p.sendMessage(API.instance.prefix + " §7Antireach-system by §e§oSquareCode");
                    p.sendMessage(API.instance.prefix + " §7Version§8: §e§o" + AntiReach.instance.getDescription().getVersion());
                    p.sendMessage(API.instance.prefix + " §7§oa free and high quality antireach solution");
                    p.sendMessage("§8§m--------------------------------------------------------");
                    p.sendMessage(" ");
                }
            }

        } else sender.sendMessage(API.instance.noplayer);

        return true;
    }
}
