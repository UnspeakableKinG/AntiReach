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
        if (!API.instance.VL.containsKey(p.getUniqueId())) {
            API.instance.VL.put(p.getUniqueId(), 0);
        }
    }
}
