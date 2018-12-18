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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Copyright © SquareCode 2018
 * created on: 16.12.2018 / 13:39
 * Project: AntiReach
 */
public class reach_i extends Module {

    public reach_i() {
        super("Reach", "EntityDamageByEntityEvent", HackType.COMBAT, "I");
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity().getType() == EntityType.PLAYER) {
                final Player player = (Player) event.getDamager();
                final Player damaged = (Player) event.getEntity();
                if (player.getGameMode() != GameMode.CREATIVE) {
                    if (!player.hasPermission(API.instance.bypass) || !player.hasPermission(API.instance.admin)) {

                        Double reach = MathUtil.instance.getDistance3D(player.getLocation(), damaged.getLocation());
                        if (API.instance.bypassmode.contains(player.getName())) {
                            return;
                        }
                        if(player.isSprinting()) {
                            reach += 0.2;
                        }
                        for (final PotionEffect effect : player.getActivePotionEffects()) {
                            if (effect.getType() == PotionEffectType.SPEED) {
                                reach += 0.2 * (effect.getAmplifier() + 1);
                            }
                        }

                        if (reach > API.instance.MAX_REACH_I) {
                            int ping = ((CraftPlayer) player).getHandle().ping;
                            double tps = TPSManager.instance.getTPS();
                            event.setCancelled(true);
                            API.instance.pokeReach(player.getName(), "higher range as < " + API.instance.MAX_REACH_I, String.valueOf(reach), ping, tps, ReachType.I, API.VLReach.get(player.getUniqueId()));
                        }
                    }
                }
            }
        }
    }
}
