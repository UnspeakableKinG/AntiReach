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
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class reach_a implements Listener {

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

                            if (distance > API.instance.MAX_REACH_A) {
                                if(player.hasPotionEffect(PotionEffectType.SPEED)) {
                                    if(distance > API.instance.MAX_REACH_A + 1.0) {
                                        API.instance.pokeReach(player.getName(), "too high hit range < "+API.instance.MAX_REACH_A +1.0, ddistance, ping, tps, ReachType.A);
                                        event.setCancelled(true);
                                    }
                                } else {
                                    API.instance.pokeReach(player.getName(), "too high hit range < "+API.instance.MAX_REACH_A, ddistance, ping, tps, ReachType.A);
                                    event.setCancelled(true);
                                }
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
