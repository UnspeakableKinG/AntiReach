package net.square.event;

import net.square.api.API;
import net.square.commands.antireach_Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (API.instance.verbosemode.contains(p.getName())) {
            API.instance.verbosemode.remove(p.getName());
        }
        if(antireach_Command.accepted.contains(p.getName())) {
            antireach_Command.accepted.remove(p.getName());
        }
        if(API.VLReach.containsKey(p.getUniqueId())) {
            API.VLReach.remove(p.getUniqueId());
        }
        if (API.instance.ID.containsKey(p.getUniqueId())) {
            API.instance.ID.remove(p.getUniqueId());
        }
    }
}
