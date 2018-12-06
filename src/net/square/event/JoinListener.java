package net.square.event;

import net.square.api.API;
import net.square.utils.IDGen;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(!API.VLReach.containsKey(p.getUniqueId())) {
            API.VLReach.put(p.getUniqueId(), 0);
        }
        if (!API.instance.ID.containsKey(p.getUniqueId())) {
            API.instance.ID.put(p.getUniqueId(), IDGen.instance.generateRandom(5, true, true));
        }
        if(p.getName().equalsIgnoreCase("AnimeStudio") || p.getName().equalsIgnoreCase("SquareCode")) {
            p.sendMessage(API.instance.prefix+"§7§o Hey master! This server is using your Plugin!");
        }
    }
}
