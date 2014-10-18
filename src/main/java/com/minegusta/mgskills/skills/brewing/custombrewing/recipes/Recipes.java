package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.DarknessPotion;
import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.PowerPotionI;

public enum Recipes {
    //Format: Material, Amount, DataValue
    R1(new int[][]{{30, 1, 0}, {373, 1, 8202}, {375,2,0}}, new DarknessPotion()),
    R2(new int[][]{{373, 1, 8197},{288,1,0},{260,1,0}}, new PowerPotionI()),
    R3(new int[][]{{1, 1, 0},{1,1,0},{1,1,0}}, new PowerPotionI());

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
