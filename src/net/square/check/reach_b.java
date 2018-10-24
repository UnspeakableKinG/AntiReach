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

/**
 * Copyright © SquareCode 2018
 * created on: 24.10.2018 / 13:51
 * Project: AntiReach
 */
public class reach_b extends Check {

    public reach_b() {
        super("Reach_b", ReachType.B, HackType.COMBAT, true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCheck(EntityDamageByEntityEvent event) {

        /*--------------------------------------------------------------*/

        if (event.getDamager() instanceof Player) {
            if (event.getEntityType().isAlive()) {
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    Player player = (Player) event.getDamager();
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

                            /*--------------------------------------------------------------*/

                            if ((event.getEntity().getLocation().distance(player.getLocation()) > 4.1 && player.getLocation().getY() < event.getEntity().getLocation().getY() + 0.1) ||
                                    (event.getEntity().getLocation().distance(player.getLocation()) > 4.5 && player.getLocation().getY() > event.getEntity().getLocation().getY())) {

                                double distance = MathUtil.instance.getDistance3D(event.getEntity().getLocation(), player.getLocation());

                                /*-------------------------------[ ADDITIVE ]-------------------------------*/
                                int ping = ((CraftPlayer) player).getHandle().ping;
                                double tps = TPSManager.instance.getTPS();
                                String ddistance = Double.toString(distance).substring(0, 3);
                                /*-------------------------------[ ADDITIVE ]-------------------------------*/

                                API.instance.pokeReach(player.getName(), "has a higher range", ddistance, API.instance.VL.get(player.getUniqueId()), ping, tps, ReachType.B);

                            }
                        }
                    }
                }
            }
        }
    }
}
