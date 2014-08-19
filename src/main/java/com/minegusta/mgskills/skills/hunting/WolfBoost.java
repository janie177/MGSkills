package com.minegusta.mgskills.skills.hunting;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.CoolDown;
import com.minegusta.mgskills.util.SendMessage;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.WorldCheck;
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

public class WolfBoost implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args)
    {
        if(!(s instanceof Player))return true;

        Player p = (Player) s;
        DetailedMPlayer mp = TempData.pMap.get(p.getUniqueId());

        if(!(new WorldCheck(p.getWorld()).check()))
        {
            new SendMessage(p, Lists.newArrayList("You cannot use that in this world!"));
            return true;
        }

        if(mp.getHuntingLevel() >= 72)
        {
            if(!CoolDown.cooledDown(p.getUniqueId(), TempData.wolfMap, 60 * 15))
            {
                new SendMessage(p, Lists.newArrayList("You have to wait another " + (60 * 15 - CoolDown.getRemainingTime(p.getUniqueId(), TempData.wolfMap)) + " seconds."));
                return true;
            }
            //Summon wolf
            new SendMessage(p, Lists.newArrayList("You summoned a wolf companion.", "May Hircine be with you!"));
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
            CoolDown.newCooldown(p.getUniqueId(), TempData.wolfMap);
            mp.addHunting(100);
            return true;
        }
        new SendMessage(p, Lists.newArrayList("You need hunting level 70 to summon a wolf companion."));
        return true;
    }
}
