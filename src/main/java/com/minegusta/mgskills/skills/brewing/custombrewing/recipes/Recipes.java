package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.DarknessPotion;

public enum Recipes {
    //Format: Material, Amount, DataValue
    R1(new int[][]{{98, 1, 0}, {4, 2, 0}, {1,1,0}}, new DarknessPotion());

    private int[][] i;
    private PotionRecipe r;

    private Recipes(int[][] i, PotionRecipe r) {
        this.i = i;
        this.r = r;
    }

    public int[][] getIngredients()
    {
        return i;
    }

    public int getIndex() {
        return Integer.parseInt(name().toString().replace("R", ""));
    }

    public PotionRecipe getRecipe() {
        return r;
    }
}
