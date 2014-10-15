package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public interface PotionRecipe
{
    /**
     *
     * @return Get the recipe information for this potion.
     */
    public String getInfo();

    /**
     *
     * @return Get the name for the potion.
     */
    public String getPotionName();


    /**
     *
     * @return The time it takes in seconds to brew this potion.
     */
    public int getTime();

    /**
     *
     * @return Return the potion itself for the player to collect.
     */
    public ItemStack getPotion();


    /**
     *
     * @return The experience earned from making this potion.
     */
    public int getExperience();

    /**
     *
     * @return The effect to play when you finish brewing the potion.
     */
    public Effect getFinishEffect();

    /**
     *
     * @return The effect to play while brewing this potion.
     */
    public Effect getBrewEffect();

    /**
     *
     * @param lab The evil lab location.
     * @return Whether the lab meets potion-specific conditions.
     */
    public boolean hasConditions(Block lab);

    /**
     *
     * @return The required level for brewing this specific potion.
     */
    public int getLevelRequiredment();

}
