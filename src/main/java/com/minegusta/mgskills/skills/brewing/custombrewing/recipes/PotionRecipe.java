package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

public abstract class PotionRecipe {

    /**
     * The name of this potion in color!
     */
    private String name;

    /**
     * The looks of this potion.
     */
    private PotionType appearance;

    /**
     * The level needed to brew this.
     */
    private int level;

    /**
     * Experience earned for brewing this potion.
     */
    private int experience;

    /**
     * Ingredients needed + amounts.
     */
    private String[] ingredientNames;

    public int[][] ingredients;

    /**
     * The special requirement needed to brew this potion. This has to coded by hand still!
     */
    private String requirement;

    /**
     * Effect#1 of the potion + duration (Seconds) and amplifier (starts at 0).
     */
    private PotionEffect[] effects;

    /**
     * The brewEeffect to play while brewing.
     */
    private Effect brewEeffect;

    /**
     * The brewEeffect to play when finished.
     */
    private Effect finishEffect;

    /**
     * Is this a splash.
     */
    private boolean splash;

    /**
     * How long does it take to brew this potion?
     */
    private int duration;

    public PotionRecipe(String name, PotionType appearance, int level, int experience, String[] ingredientNames, int[][] ingredients, String requirement, PotionEffect[] effects, Effect brewEeffect, Effect finishEffect, boolean splash, int duration)
    {
        this.name = name;
        this.appearance = appearance;
        this.level = level;
        this.experience = experience;
        this.ingredientNames = ingredientNames;
        this.ingredients = ingredients;
        this.requirement = requirement;
        this.effects = effects;
        this.brewEeffect = brewEeffect;
        this.finishEffect = finishEffect;
        this.splash = splash;
        this.duration = duration;

    }

    /**
     * @return Get the recipe information for this potion.
     */
    public String getInfo()
    {
        String ingredientsList = "";
        String results = "";
        for(String s : ingredientNames)
        {
            ingredientsList = ingredientsList + "/n&4 - " + s;
        }
        for(PotionEffect e : effects)
        {
            results = results + "/n&4 - " + e.getType().getName() + " " + Integer.toString(e.getAmplifier() + 1);
        }
        return "&0&l" + ChatColor.stripColor(name) +
                "/n&0Level: &4" + Integer.toString(level) +
                "/n&0Ingredients:" +
                ingredientsList +
                "/n&0Requirements:" +
                "/n&4 - " + requirement +
                "/n&0Splash: &4" + splash +
                "/n&0Experience: &4" + experience +
                "/n&0Result:" +
                results;
    }

    /**
     * @return Get the name for the potion.
     */
    public String getPotionName()
    {
        return name;
    }

    public PotionType getAppearance()
    {
        return appearance;
    }

    /**
     * @return The time it takes in seconds to brew this potion.
     */
    public int getTime()
    {
        return duration;
    }

    /**
     * @return Return the potion itself for the player to collect.
     */
    public ItemStack getPotion()
    {
        //Making the potion
        ItemStack potion = new ItemStack(splash ? Material.SPLASH_POTION : Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        PotionData data = new PotionData(appearance);

        //Convert to itemStack

        for(PotionEffect e : effects)
        {
            meta.addCustomEffect(new PotionEffect(e.getType(), e.getDuration(), e.getAmplifier(), false), true);
        }

        meta.setDisplayName(name);

        meta.setBasePotionData(data);
        potion.setItemMeta(meta);



        return potion;

    }

    /**
     * @return The experience earned from making this potion.
     */
    public int getExperience()
    {
        return experience;
    }

    /**
     * @return The effect to play when you finish brewing the potion.
     */
    public Effect getFinishEffect()
    {
        return finishEffect;
    }

    /**
     * Get the ingredients for this potion
     * @return the ingredients needed to brew this potion.
     */
    public int[][] getIngredients()
    {
        return ingredients;
    }

    /**
     * @return The effect to play while brewing this potion.
     */
    public Effect getBrewEffect()
    {
        return brewEeffect;
    }

    /**
     * @param lab The evil lab location.
     * @return Whether the lab meets potion-specific conditions.
     */
    public abstract boolean hasConditions(Block lab);

    /**
     * @return The required level for brewing this specific potion.
     */
    public int getLevelRequiredment()
    {
        return level;
    }

}
