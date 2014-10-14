package com.minegusta.mgskills.skills.healing;

import com.minegusta.mgskills.util.SendMessage;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealPlayer {
    private static int exp = 0;

    public static int healEntities(Player p, double amount, int radius, boolean protection, boolean speed, boolean healSelf) {
        SendMessage.send(p, ChatColor.DARK_RED + "You start healing the entities around you.");
        if (healSelf) {
            healEntity(p, amount, speed, protection, p);
        }
        for (Entity e : p.getNearbyEntities(radius, radius, radius)) {
            healEntity(e, amount, speed, protection, p);
        }
        return exp;
    }

    private static void playHearts(Entity e, String name) {
        e.getWorld().spigot().playEffect(e.getLocation(), Effect.HEART, 0, 0, 1, 1, 1, 1, 14, 14);
        if (e instanceof Player)
            SendMessage.send((Player) e, ChatColor.LIGHT_PURPLE + "You have been healed by " + name + "!");
    }

    private static void healEntity(Entity e, double amount, boolean speed, boolean protection, Player p) {
        if (e instanceof LivingEntity) {
            double maxHealed = ((LivingEntity) e).getMaxHealth() - ((LivingEntity) e).getHealth();

            if (maxHealed < amount) {
                exp = exp + (14 * (int) maxHealed);
                ((LivingEntity) e).setHealth(((LivingEntity) e).getMaxHealth());
            } else {
                exp = exp + 14 * (int) amount;
                ((LivingEntity) e).setHealth(((LivingEntity) e).getHealth() + amount);
            }
            if (e instanceof Player) {
                if (protection)
                    ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0, false));
                if (speed) ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 0, false));
            }
            playHearts(e, p.getName());
        }
    }


}
