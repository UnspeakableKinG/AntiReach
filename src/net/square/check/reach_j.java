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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
                        final double YawDifference = Math.abs(180.0f - Math.abs(damaged.getLocation().getYaw() - player.getLocation().getYaw()));
                        double Difference = MathUtil.instance.getEyeLocation(player).distance(damaged.getEyeLocation()) - 0.35;
                        final int Ping = ((CraftPlayer) player).getHandle().ping;
                        double MaxReach = API.instance.MAX_REACH_J + damaged.getVelocity().length();
                        if (player.isSprinting()) {
                            MaxReach += 0.2;
                        }
                        if (player.getLocation().getY() > damaged.getLocation().getY()) {
                            Difference = player.getLocation().getY() - player.getLocation().getY();
                            MaxReach += Difference / 2.5;
                        } else if (player.getLocation().getY() > player.getLocation().getY()) {
                            Difference = player.getLocation().getY() - player.getLocation().getY();
                            MaxReach += Difference / 2.5;
                        }
                        for (final PotionEffect effect : player.getActivePotionEffects()) {
                            if (effect.getType() == PotionEffectType.SPEED) {
                                MaxReach += 0.2 * (effect.getAmplifier() + 1);
                            }
                        }
                        final double velocity = player.getVelocity().length() + damaged.getVelocity().length();
                        MaxReach += velocity * 1.5;
                        MaxReach += ((Ping < 250) ? (Ping * 0.00212) : (Ping * 0.031));
                        MaxReach += YawDifference * 0.008;
                        if (MaxReach < Difference) {
                            if (AimUtil.instance.lookingTo(player, damaged)) {
                                String ddistance = Double.toString(Difference).substring(0, 3);
                                int ping = ((CraftPlayer) player).getHandle().ping;
                                double tps = TPSManager.instance.getTPS();
                                event.setCancelled(true);
                                API.instance.pokeReach(player.getName(), "wrong heuristics + reach <"+API.instance.MAX_REACH_J, ddistance, ping, tps, ReachType.J, API.VLReach.get(player.getUniqueId()));
                            }
                        }
                    }
                }
            }
        }
    }
}
