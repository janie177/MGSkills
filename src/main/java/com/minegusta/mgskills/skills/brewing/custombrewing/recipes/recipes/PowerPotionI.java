package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PowerPotionI implements PotionRecipe {
    @Override
    public String getInfo() {
        return "&0&lPowerPotion I:" +
                "/n&0Level: &424" +
                "/n&0Ingredients:" +
                "/n&4 - 4 Feathers" +
                "/n&4 - 1 Healing Potion I" +
                "/n&4 - 1 Apple" +
                "/n&0Requirements:" +
                "/n&4 - Light level > 8" +
                "/n" +
                "/n&0Result:" +
                "/n&4 - Darkness potion";
    }

    @Override
    public String getPotionName() {
        return "Power Potion I";
    }

    @Override
    public int getTime() {
        return 14;
    }

    @Override
    public ItemStack getPotion() {

        //Making the potion

        Potion pot = new Potion(PotionType.FIRE_RESISTANCE); //The base potion type. Only defines what the potion looks like!!!
        pot.setSplash(true);
        pot.setLevel(1); //Always keep this 1. Otherwise it will throw errors at you ;-;

        //Convert to itemStack
        ItemStack potion = pot.toItemStack(1);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        //Here I set the potion meta.
        //Type duration amp ambient
        meta.setMainEffect(PotionEffectType.REGENERATION);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 17 * 20, 0, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 0, false), true);
        meta.setDisplayName(ChatColor.GOLD + "Power Potion I");
        potion.setItemMeta(meta);



        return potion;
    }

    @Override
    public int getExperience() {
        return 175;
    }

    @Override
    public Effect getFinishEffect() {
        return Effect.HEART;
    }

    @Override
    public Effect getBrewEffect() {
        return Effect.LARGE_SMOKE;
    }

    @Override
    public boolean hasConditions(Block lab) {
        return lab.getLightLevel() > 8;
    }

    @Override
    public int getLevelRequiredment() {
        return 24;
    }
}
