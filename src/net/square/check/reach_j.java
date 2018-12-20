package net.square.check;

import net.square.api.API;
import net.square.api.HackType;
import net.square.api.Module;
import net.square.utils.AimUtil;
import net.square.utils.MathUtil;
import net.square.utils.TPSManager;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Copyright © SquareCode 2018
 * created on: 20.12.2018 / 22:22
 * Project: AntiReach
 */
public class reach_j extends Module {

    public reach_j() {
        super("Reach", "EntityDamageByEntityEvent", HackType.COMBAT, "J");
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity().getType() == EntityType.PLAYER) {
                final Player player = (Player) event.getDamager();
                final Player damaged = (Player) event.getEntity();
                if (player.getGameMode() != GameMode.CREATIVE) {
                    if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {
                        if (API.instance.bypassmode.contains(player.getName())) {
                            return;
                        }
                        if (AimUtil.instance.lookingTo(player, damaged)) {
                            double Difference = MathUtil.instance.getEyeLocation(player).distance(damaged.getEyeLocation()) - 0.35;
                            String ddistance = Double.toString(Difference).substring(0, 3);
                            int ping = ((CraftPlayer) player).getHandle().ping;
                            double tps = TPSManager.instance.getTPS();
                            event.setCancelled(true);
                            API.instance.pokeReach(player.getName(), "wrong heuristics", ddistance, ping, tps, ReachType.J, API.VLReach.get(player.getUniqueId()));
                        }
                    }
                }
            }
        }
    }
}
