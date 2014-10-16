package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.DarknessPotion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum Recipes
{
   R1(new ItemStack[]{new ItemStack(Material.SPIDER_EYE, 1), new ItemStack(Material.WOOL, 1),new ItemStack(Material.WEB, 1)}, new DarknessPotion());

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
