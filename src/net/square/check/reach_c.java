package net.square.check;

import net.square.api.API;
import net.square.utils.MathUtil;
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
 * created on: 25.10.2018 / 15:22
 * Project: AntiReach
 */
public class reach_c implements Listener {

    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {

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

                            if (event.getEntity().getLocation().distance(player.getLocation()) > API.instance.MAX_REACH_C && player.getLocation().getY() < event.getEntity().getLocation().getY() + 0.1) {
                                API.instance.pokeReach(player.getName(), "higher range as max < "+API.instance.MAX_REACH_C, ddistance,  ping, tps, ReachType.C);
                                    event.setCancelled(true);

                            }
                            if (event.getEntity().getLocation().distance(player.getLocation()) > API.instance.MAX_REACH_C && player.getLocation().getY() > event.getEntity().getLocation().getY()) {
                                API.instance.pokeReach(player.getName(), "higher range as max < "+API.instance.MAX_REACH_C, ddistance,  ping, tps, ReachType.C);
                                    event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
