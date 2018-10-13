package net.square.check;

import net.square.api.API;
import net.square.utils.Lag;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Check implements Listener {

    public static double getDistance3D(Location one, Location two) {
        double toReturn = 0.0D;
        double xSqr = (two.getX() - one.getX()) * (two.getX() - one.getX());
        double ySqr = (two.getY() - one.getY()) * (two.getY() - one.getY());
        double zSqr = (two.getZ() - one.getZ()) * (two.getZ() - one.getZ());
        double sqrt = Math.sqrt(xSqr + ySqr + zSqr);
        toReturn = Math.abs(sqrt);
        return toReturn;
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
                            double distance = getDistance3D(entityLoc, playerLoc);

                            /*-------------------------------[ ADDITIVE ]-------------------------------*/
                            int ping = ((CraftPlayer) player).getHandle().ping;
                            double tps = Lag.getTPS();
                            String ddistance = Double.toString(distance).substring(0, 3);
                            /*-------------------------------[ ADDITIVE ]-------------------------------*/

                            if (distance > 6) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "100%");
                                event.setCancelled(true);
                            } else if (distance > 5.5) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "100%");
                                event.setCancelled(true);
                            } else if (distance > 5) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "100%");
                                event.setCancelled(true);
                            } else if (distance > 4.5) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "80%");
                                event.setCancelled(true);
                            } else if (distance > 4.4) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "65%");
                                event.setCancelled(true);
                            } else if (distance > 4.3) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "50%");
                                event.setCancelled(true);
                            } else if (distance > 4.2) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "35%");
                                event.setCancelled(true);
                            } else if (distance > 4.1) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "20%");
                            } else if (distance > 4.0) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "10%");
                            } else if (distance > 3.9) {
                                API.instance.pokeReach(player.getName(), "too high hit range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, "5%");
                            }
                        }
                    }
                }
            }
        }
    }
}
