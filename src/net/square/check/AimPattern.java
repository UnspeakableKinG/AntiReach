package net.square.check;

import net.square.api.API;
import net.square.utils.AimUtil;
import net.square.utils.TPSManager;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Copyright © SquareCode 2018
 * created on: 02.11.2018 / 18:56
 * Project: AntiReach
 */
public class AimPattern implements Listener {

    @EventHandler
    public void on(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            final Player p = (Player) e.getDamager();
            if (!AimUtil.isLookingToEntity(p, e.getEntity())) {

                /*-------------------------------[ ADDITIVE ]-------------------------------*/
                int ping = ((CraftPlayer) p).getHandle().ping;
                double tps = TPSManager.instance.getTPS();
                /*-------------------------------[ ADDITIVE ]-------------------------------*/

                e.setCancelled(true);
                API.instance.pokeAimPattern(p.getName(), "wrong aim in move", ping, tps);
            }
        }
    }
}
