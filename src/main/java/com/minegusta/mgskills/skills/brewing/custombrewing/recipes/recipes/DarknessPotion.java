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

public class DarknessPotion implements PotionRecipe {
    @Override
    public String getInfo() {
        return "&0&lDarknessPotion:" +
                "/n&0Level: &410" +
                "/n&0Ingredients:" +
                "/n&4 - 2 Spider Eyes" +
                "/n&4 - 1 Slowness Potion | 1:30" +
                "/n&4 - 1 Cobweb" +
                "/n&0Requirements:" +
                "/n&4 - Light level < 7" +
                "/n" +
                "/n&0Result:" +
                "/n&4 - Darkness potion";
    }

    @Override
    public String getPotionName() {
        return "Darkness Potion";
    }

    @Override
    public int getTime() {
        return 11;
    }

    @Override
    public ItemStack getPotion() {

        //Making the potion

        Potion pot = new Potion(PotionType.SLOWNESS); //The base potion type. Only defines what the potion looks like!!!
        pot.setSplash(true);
        pot.setLevel(1); //Always keep this 1. Otherwise it will throw errors at you ;-;

        //Convert to itemStack
        ItemStack potion = pot.toItemStack(1);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        //Here I set the potion meta.
        //Type duration amp ambient
        meta.setMainEffect(PotionEffectType.SLOW);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30 * 20, 1, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 30 * 20, 1, false), true);
        meta.setDisplayName(ChatColor.BLACK + "Potion Of Darkness");
        potion.setItemMeta(meta);



        return potion;
    }

    @Override
    public int getExperience() {
        return 150;
    }

    @Override
    public Effect getFinishEffect() {
        return Effect.CLOUD;
    }

    @Override
    public Effect getBrewEffect() {
        return Effect.MOBSPAWNER_FLAMES;
    }

    @Override
    public boolean hasConditions(Block lab) {
        return lab.getLightLevel() < 7;
    }

    @Override
    public int getLevelRequiredment() {
        return 10;
    }
}
