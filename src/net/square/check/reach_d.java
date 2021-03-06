package net.square.check;

import net.square.api.API;
import net.square.api.HackType;
import net.square.api.Module;
import net.square.utils.MathUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
 * created on: 25.10.2018 / 19:35
 * Project: AntiReach
 */
public class reach_d extends Module {

    public reach_d() {
        super("Reach", "EntityDamageByEntityEvent", HackType.COMBAT, "D");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity().getType() == EntityType.PLAYER) {
                Player player = (Player) event.getDamager();
                if (player.getGameMode() != GameMode.CREATIVE) {
                    if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

                        if(API.instance.bypassmode.contains(player.getName())) {
                            return;
                        }
                        Player P = (Player) event.getDamager();
                        Location loc = player.getLocation();
                        Location loc2 = event.getEntity().getLocation();

                        double distance = MathUtil.instance.getDistance3D(player.getLocation(), event.getEntity().getLocation());

                        /*-------------------------------[ ADDITIVE ]-------------------------------*/
                        int ping = ((CraftPlayer) player).getHandle().ping;
                        double tps = TPSManager.instance.getTPS();
                        String ddistance = Double.toString(distance).substring(0, 3);
                        /*-------------------------------[ ADDITIVE ]-------------------------------*/

                        if (distance > API.instance.MAX_REACH_D) {
                            if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                                if (distance > API.instance.MAX_REACH_D + 1) {
                                    if (player != null) {
                                        API.instance.pokeReach(player.getName(), "over max reach < " + API.instance.MAX_REACH_D + 1, ddistance, ping, tps, ReachType.D, API.VLReach.get(player.getUniqueId()));
                                        event.setCancelled(true);
                                    } else {
                                        event.setCancelled(false);
                                    }
                                }
                            } else {
                                if (player != null) {
                                    API.instance.pokeReach(player.getName(), "over max reach < " + API.instance.MAX_REACH_D, ddistance, ping, tps, ReachType.D, API.VLReach.get(player.getUniqueId()));
                                    event.setCancelled(true);
                                } else {
                                    event.setCancelled(false);
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
