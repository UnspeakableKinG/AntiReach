package net.square.check;

import net.square.api.API;
import net.square.api.HackType;
import net.square.api.Module;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Copyright © SquareCode 2018
 * created on: 06.12.2018 / 22:18
 * Project: AntiReach
 */
public class reach_f extends Module {

    public reach_f() {
        super("Reach", "PlayerInteractEvent", HackType.INTERACT, "F");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getClickedBlock() != null) {
            int ping = ((CraftPlayer) p).getHandle().ping;
            if (ping > 200) {
                ping = 200;
            }
            if (ping < 10) {
                ping = 10;
            }
            final double diff = e.getClickedBlock().getLocation().add(0.5, 0.5, 0.5).distance(p.getLocation().add(0.0, 1.6, 0.0));
            if (p.getGameMode() != GameMode.CREATIVE) {
                if (diff > API.instance.MAX_REACH_F + 0.1 + ping / 40) {
                    e.setCancelled(true);
                    API.instance.pokeReach(p.getName(), "interact too far away", String.valueOf(diff), ping, TPSManager.getTPS(), ReachType.F, API.VLReach.get(p.getUniqueId()));
                } else if (diff > API.instance.MAX_REACH_F) {
                    e.setCancelled(true);
                    API.instance.pokeReach(p.getName(), "interact too far away", String.valueOf(diff), ping, TPSManager.getTPS(), ReachType.F, API.VLReach.get(p.getUniqueId()));
                }
            } else if (diff > API.instance.MAX_REACH_F + 0.6 + ping / 40) {
                e.setCancelled(true);
                API.instance.pokeReach(p.getName(), "interact too far away", String.valueOf(diff), ping, TPSManager.getTPS(), ReachType.F, API.VLReach.get(p.getUniqueId()));
            } else if (diff > API.instance.MAX_REACH_F + 0.5) {
                e.setCancelled(true);
                API.instance.pokeReach(p.getName(), "interact too far away", String.valueOf(diff), ping, TPSManager.getTPS(), ReachType.F, API.VLReach.get(p.getUniqueId()));
            }
        }
    }
}
