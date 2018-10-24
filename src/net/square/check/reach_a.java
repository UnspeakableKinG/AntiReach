package net.square.check;

import net.square.api.API;
import net.square.utils.MathUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class reach_a extends Check {

    public reach_a() {
        super("Reach_A", ReachType.A, HackType.COMBAT, true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHit(EntityDamageByEntityEvent event) {

        /*--------------------------------------------------------------*/

        if (event.getDamager() instanceof Player) {
            if (event.getEntityType().isAlive()) {
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    Player player = (Player) event.getDamager();
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

        /*--------------------------------------------------------------*/

                            LivingEntity damaged = (LivingEntity) event.getEntity();
                            Location entityLoc = damaged.getLocation().add(0.0D, damaged.getEyeHeight(), 0.0D);
                            Location playerLoc = player.getLocation().add(0.0D, player.getEyeHeight(), 0.0D);
                            double distance = MathUtil.instance.getDistance3D(entityLoc, playerLoc);

                            /*-------------------------------[ ADDITIVE ]-------------------------------*/
                            int ping = ((CraftPlayer) player).getHandle().ping;
                            double tps = TPSManager.instance.getTPS();
                            String ddistance = Double.toString(distance).substring(0, 3);
                            /*-------------------------------[ ADDITIVE ]-------------------------------*/

                            if (distance > 6) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 5.5) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 5) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 4.5) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 4.4) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 4.3) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 4.2) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                                event.setCancelled(true);
                            } else if (distance > 4.1) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                            } else if (distance > 4.0) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                            } else if (distance > 3.9) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.A);
                            }
                        }
                    }
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }
}
