package net.square.check;

import net.square.api.API;
import net.square.utils.AimUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Copyright © SquareCode 2018
 * created on: 03.11.2018 / 01:01
 * Project: AntiReach
 */
public class hitdirection_a implements Listener {

    @EventHandler
    public void onHitEntity(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntityType().isAlive()) {
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    Player player = (Player) event.getDamager();
                    Player target = (Player) event.getEntity();
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

                            if(!AimUtil.isLookingToEntity(player, target)) {

                                /*-------------------------------[ ADDITIVE ]-------------------------------*/
                                int ping = ((CraftPlayer) player).getHandle().ping;
                                double tps = TPSManager.instance.getTPS();
                                /*-------------------------------[ ADDITIVE ]-------------------------------*/

                                API.instance.pokeHit(player.getName(), "wrong hitdirection (killaura?)", ping, tps);
                                event.setCancelled(true);

                            }
                        }
                    }
                }
            }
        }
    }
}
