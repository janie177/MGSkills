package com.minegusta.mgskills.skills.hunting;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.*;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WolfBoost implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) return true;

        Player p = (Player) s;
        DetailedMPlayer mp = TempData.getMPlayer(p);

        if (!WorldCheck.check(p.getWorld().getName())) {
            SendMessage.send(p, "You cannot use that in this world!");
            return true;
        }

        if (mp.getLevel(Skill.HUNTING) >= 72) {
            if (!CoolDown.cooledDown(p.getUniqueId().toString(), TempData.wolfMap, 60 * 15)) {
                SendMessage.send(p, "You have to wait another " + (60 * 15 - CoolDown.getRemainingTime(p.getUniqueId().toString(), TempData.wolfMap)) + " seconds.");
                return true;
            }
            //Summon wolf
            SendMessage.send(p, "You summoned a wolf companion.", "May Hircine be with you!");
            Entity ent = p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
            Wolf wolf = (Wolf) ent;
            wolf.getWorld().spigot().playEffect(wolf.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 1, 1, 1, 1, 1, 25, 15);
            wolf.setCustomNameVisible(true);
            wolf.setTamed(true);
            wolf.setOwner(p);
            wolf.setCustomName(ChatColor.DARK_RED + p.getName() + "'s Wolf");
            wolf.setCollarColor(DyeColor.CYAN);
            wolf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 180, 1));
            wolf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 180, 1));
            wolf.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 60 * 180, 0));
            wolf.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 180, 0));
            wolf.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 60 * 180, 0));

            //Cooldown
            CoolDown.newCooldown(p.getUniqueId().toString(), TempData.wolfMap);
            mp.addExp(Skill.HUNTING, 100);
            return true;
        }
        SendMessage.send(p, "You need hunting level 70 to summon a wolf companion.");
        return true;
    }
}
