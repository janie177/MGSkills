package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.BeserkPotionI;

public enum Recipes
{
    R1(new BeserkPotionI());

    private PotionRecipe r;

    private Recipes(PotionRecipe r) {
        this.r = r;
    }

    public int[][] getIngredients()
    {
        return r.getIngredients();
    }

    public int getIndex() {
        return Integer.parseInt(name().replace("R", ""));
    }

    public PotionRecipe getRecipe() {
        return r;
    }
}
