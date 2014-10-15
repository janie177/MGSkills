package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum Recipes
{
   R1(null, null);

    private ItemStack[] i;
    private PotionRecipe r;

    private Recipes(ItemStack[] i, PotionRecipe r)
    {
        this.i = i;
        this.r = r;
    }

    public ItemStack[] getIngredients()
    {
        Arrays.sort(i);
        return  i;
    }

    public int getIndex()
    {
        return Integer.parseInt(name().toString());
    }

    public PotionRecipe getRecipe()
    {
        return r;
    }
}
