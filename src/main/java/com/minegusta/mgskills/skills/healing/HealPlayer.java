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

    public static int healEntities(Player p, double amount, int radius, boolean protection, boolean speed, boolean healSelf)
    {
        int exp = 20;
        SendMessage.send(p, ChatColor.DARK_RED + "You start healing the entities around you.");
        if (healSelf) {
            healEntity(p, amount, speed, protection, p);
        }
        for (Entity e : p.getNearbyEntities(radius, radius, radius)) {
            exp = healEntity(e, amount, speed, protection, p);
        }

        if(exp > 10000) exp = 10000;
        return exp;
    }

    private static void playHearts(Entity e, String name) {
        e.getWorld().spigot().playEffect(e.getLocation(), Effect.HEART, 0, 0, 1, 1, 1, 1, 14, 14);
        if (e instanceof Player)
            SendMessage.send((Player) e, ChatColor.LIGHT_PURPLE + "You have been healed by " + name + "!");
    }

    private static int healEntity(Entity e, double amount, boolean speed, boolean protection, Player p)
    {
        int exp = 20;
        if (e instanceof LivingEntity) {
            double maxHealed = ((LivingEntity) e).getMaxHealth() - ((LivingEntity) e).getHealth();

            //You can heal to your full extend.
            if (maxHealed >= amount) {
                exp = exp + (6 * (int) amount);
                ((LivingEntity) e).setHealth(((LivingEntity) e).getHealth() + amount);
            //The entity has too high health to be fully healed.
            } else {
                exp = exp + (6 * (int) maxHealed);
                ((LivingEntity) e).setHealth(((LivingEntity) e).getMaxHealth());
            }
            if (e instanceof Player) {
                if (protection)
                    ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0, false));
                if (speed) ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 0, false));
            }
            playHearts(e, p.getName());
        }
        return exp;
    }


}
