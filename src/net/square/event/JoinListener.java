package net.square.event;

import net.square.api.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(!API.instance.VLHitDirection.containsKey(p.getUniqueId())) {
            API.VLHitDirection.put(p.getUniqueId(), 0);
        }
        if(!API.VLReach.containsKey(p.getUniqueId())) {
            API.VLReach.put(p.getUniqueId(), 0);
        }
    }
}
