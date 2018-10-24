package net.square.event;

import net.square.api.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (API.instance.VL.containsKey(p.getUniqueId())) {
            API.instance.VL.remove(p.getUniqueId(), API.instance.VL.get(p.getUniqueId()));
        }
        if (API.instance.verbosemode.contains(p.getName())) {
            API.instance.verbosemode.remove(p.getName());
        }
    }
}
