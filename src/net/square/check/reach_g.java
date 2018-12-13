package net.square.check;

import net.square.api.API;
import net.square.api.HackType;
import net.square.api.Module;
import net.square.utils.DistanceUtil;
import net.square.utils.MathUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * Copyright © SquareCode 2018
 * created on: 13.12.2018 / 15:59
 * Project: AntiReach
 */
public class reach_g extends Module {

    public reach_g() {
        super("Reach", "EntityDamageByEntityEvent", HackType.COMBAT, "G");
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
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

                        DistanceUtil util = new DistanceUtil(player.getLocation(), event.getEntity().getLocation());

                        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                            if (util.getxDiff() > API.instance.MAX_REACH_G + 1 || util.getzDiff() > API.instance.MAX_REACH_G + 1) {
                                if (player != null) {
                                    event.setCancelled(true);
                                    API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_G, ddistance, ping, tps, ReachType.G, API.VLReach.get(player.getUniqueId()));
                                } else {
                                    event.setCancelled(false);
                                }
                            }
                        } else {
                            if (util.getxDiff() > API.instance.MAX_REACH_G || util.getzDiff() > API.instance.MAX_REACH_G) {
                                if (player != null) {
                                    event.setCancelled(true);
                                    API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_G, ddistance, ping, tps, ReachType.G, API.VLReach.get(player.getUniqueId()));
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
