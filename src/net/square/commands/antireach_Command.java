package net.square.commands;

import net.square.api.API;
import net.square.main.AntiReach;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class antireach_Command implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(API.instance.admin)) {
                if (args.length == 0) {

                    p.sendMessage(API.instance.prefix + " §8/§cac <clear>");
                    p.sendMessage(API.instance.prefix + " §8/§cac <reload>");

                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("clear")) {

                        API.instance.VL.clear();
                        p.sendMessage(API.instance.list);

                    } else if (args[0].equalsIgnoreCase("reload")) {

                        Bukkit.getPluginManager().disablePlugin(AntiReach.instance);
                        Bukkit.getPluginManager().enablePlugin(AntiReach.instance);
                        p.sendMessage(API.instance.plugin);

                    } else {

                        p.sendMessage(API.instance.prefix + " §8/§cac <clear>");
                        p.sendMessage(API.instance.prefix + " §8/§cac <reload>");

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
