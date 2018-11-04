package net.square.check;

import net.square.api.API;
import net.square.utils.MathUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * Copyright © SquareCode 2018
 * created on: 02.11.2018 / 18:23
 * Project: AntiReach
 */
public class reach_e implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHit(EntityDamageByEntityEvent event) {

        /*--------------------------------------------------------------*/

        if (event.getDamager() instanceof Player) {
            if (event.getEntityType().isAlive()) {
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    Player player = (Player) event.getDamager();
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

                            double distance = MathUtil.instance.getDistance3D(player.getLocation(), event.getEntity().getLocation());

                            /*-------------------------------[ ADDITIVE ]-------------------------------*/
                            int ping = ((CraftPlayer) player).getHandle().ping;
                            double tps = TPSManager.instance.getTPS();
                            String ddistance = Double.toString(distance).substring(0, 3);
                            /*-------------------------------[ ADDITIVE ]-------------------------------*/

                            double dist = event.getEntity().getLocation().distance(player.getLocation());

                            if (dist > API.instance.MAX_REACH_E && event.getEntity().getLocation().getBlockY() == player.getLocation().getBlockY()) {
                                event.setCancelled(true);
                                API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_E, ddistance, ping, tps, ReachType.E);
                            } else if ((dist > API.instance.MAX_REACH_E + 0.3
                                    || dist > API.instance.MAX_REACH_E + 0.4
                                    || dist > API.instance.MAX_REACH_E + 0.5
                                    || dist > API.instance.MAX_REACH_E + 0.6
                                    || dist > API.instance.MAX_REACH_E + 0.7
                                    || dist > API.instance.MAX_REACH_E + 0.8
                                    || dist > API.instance.MAX_REACH_E + 0.9
                                    || dist > API.instance.MAX_REACH_E + 1.0
                                    || dist > API.instance.MAX_REACH_E + 1.1
                                    || dist > API.instance.MAX_REACH_E + 1.2
                                    || dist > API.instance.MAX_REACH_E + 1.3)
                                    && event.getEntity().getLocation().getBlockY() > player.getLocation().getBlockY()) {
                                if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                                    if ((dist > API.instance.MAX_REACH_E + 1 + 0.3
                                            || dist > API.instance.MAX_REACH_E + 1 + 0.4
                                            || dist > API.instance.MAX_REACH_E + 1 + 0.5
                                            || dist > API.instance.MAX_REACH_E + 1 + 0.6
                                            || dist > API.instance.MAX_REACH_E + 1 + 0.7
                                            || dist > API.instance.MAX_REACH_E + 1 + 0.8
                                            || dist > API.instance.MAX_REACH_E + 1 + 0.9
                                            || dist > API.instance.MAX_REACH_E + 1 + 1.0
                                            || dist > API.instance.MAX_REACH_E + 1 + 1.1
                                            || dist > API.instance.MAX_REACH_E + 1 + 1.2
                                            || dist > API.instance.MAX_REACH_E + 1 + 1.3)
                                            && event.getEntity().getLocation().getBlockY() > player.getLocation().getBlockY()) {
                                        API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_E + 1, ddistance, ping, tps, ReachType.E);
                                        event.setCancelled(true);
                                    }
                                } else {
                                    API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_E, ddistance, ping, tps, ReachType.E);
                                    event.setCancelled(true);
                                }

                            } else if (dist > API.instance.MAX_REACH_E + 0.2 && event.getEntity().getLocation().getBlockY() < player.getLocation().getBlockY()) {
                                if(player.hasPotionEffect(PotionEffectType.SPEED)) {
                                    if (dist > API.instance.MAX_REACH_E + 1 + 0.2 && event.getEntity().getLocation().getBlockY() < player.getLocation().getBlockY()) {
                                        API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_E + 1, ddistance, ping, tps, ReachType.E);
                                        event.setCancelled(true);
                                    }
                                } else {
                                    API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_E, ddistance, ping, tps, ReachType.E);
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
