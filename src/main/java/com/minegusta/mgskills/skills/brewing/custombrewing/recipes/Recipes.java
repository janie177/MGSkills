package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.DarknessPotion;

import java.util.Arrays;

public enum Recipes
{
   R1(new int[]{98,1,4}, new DarknessPotion());

    private int[] i;
    private PotionRecipe r;

    private Recipes(int[] i, PotionRecipe r)
    {
        this.i = i;
        this.r = r;
    }

    public int[] getIngredients()
    {
        Arrays.sort(i);
        return i;
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
