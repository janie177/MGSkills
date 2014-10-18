package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DarknessPotion implements PotionRecipe {
    @Override
    public String getInfo() {
        return "&0DarknessPotion:" +
                "/n&4Level: &c10" +
                "/n&4Ingredients:" +
                "/n&c - 2 Spider Eyes" +
                "/n&c - 1 Slowness Potion | 1:30" +
                "/n&c - 1 Cobweb" +
                "/n&4Requirements:" +
                "/n&c - Light level < 7" +
                "/n" +
                "/n&4Result:" +
                "/n&c - Darkness potion";
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

        ItemStack potion = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        //Type duration amp ambient
        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30 * 20, 1, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 30 * 20, 1, false), true);
        meta.setDisplayName(ChatColor.BLACK + "Potion Of Darkness");

        //Applying the item meta and making it splash
        potion.setItemMeta(meta);
        Potion.fromItemStack(potion).setSplash(true);

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
