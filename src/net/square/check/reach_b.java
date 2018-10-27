package net.square.check;

import net.square.api.API;
import net.square.utils.MathUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Copyright © SquareCode 2018
 * created on: 25.10.2018 / 14:52
 * Project: AntiReach
 */
public class reach_b implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            if (event.getEntityType().isAlive()) {
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    Player player = (Player) event.getDamager();
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

                            double distance = MathUtil.instance.getHorizontalDistance(player.getLocation(), event.getEntity().getLocation()) - 0.3;
                            double maxReach = 3.4;
                            final double yawDifference = 0.01 - Math.abs(Math.abs(player.getEyeLocation().getYaw()) - Math.abs(event.getEntity().getLocation().getYaw()));
                            maxReach += Math.abs(player.getVelocity().length() + event.getEntity().getVelocity().length()) * 2.5;
                            maxReach += yawDifference * 0.01;
                            if (maxReach < 3.4) {
                                maxReach = 3.4;
                            }
                            if (distance > maxReach) {
                                /*-------------------------------[ ADDITIVE ]-------------------------------*/
                                int ping = ((CraftPlayer) player).getHandle().ping;
                                double tps = TPSManager.instance.getTPS();
                                String ddistance = Double.toString(distance).substring(0, 3);
                                /*-------------------------------[ ADDITIVE ]-------------------------------*/

                                if (distance > API.instance.MAX_REACH_B) {
                                    API.instance.pokeReach(player.getName(), "over max reach < "+API.instance.MAX_REACH_B, ddistance,  ping, tps, ReachType.B);
                                        event.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
