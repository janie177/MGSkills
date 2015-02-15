package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.*;

public enum Recipes
{
    R1(new BeserkPotionI()),
    R2(new BeserkPotionII()),
    R3(new CannonFodderI()),
    R4(new DarknessPotionI()),
    R5(new DarknessPotionII()),
    R6(new DefencePotionI()),
    R7(new DefencePotionII()),
    R8(new DistortionPotionI()),
    R9(new FeedingPotionI()),
    R10(new FleeingPotionI()),
    R11(new LifePotionI()),
    R12(new MinerpotionI()),
    R13(new MinerpotionII()),
    R14(new PowerPotionI()),
    R15(new PowerPotionII()),
    R16(new SicknessPotionI()),
    R17(new CannonFodderII()),
    R18(new DistortionPotionII()),
    R19(new LifePotionII()),
    R20(new PotionOfLuck());



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
