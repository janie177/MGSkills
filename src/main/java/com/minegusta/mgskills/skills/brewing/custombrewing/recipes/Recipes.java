package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.*;

public enum Recipes
{
    R1(new DarknessPotionI()), // level 10  //Exp = 120
    R2(new PowerPotionI()), // level 24
    R3(new SicknessPotionI()), //Level 32
    R4(new MinerPotionI()), //Level 38
    R5(new FeedingPotionI()), //Level 45
    R6(new DistortionPotionI()), //Level 54
    R7(new DefencePotionI()), //Level 62
    R8(new WitheringPotionI()), //Level 69
    R9(new FleeingPotionI()), //Level 76
    R10(new MinerPotionII()), //Level 80
    R11(new LifePotionI()), //Level 85
    R12(new PotionOfLuck()), //Level 86
    R13(new SicknessPotionII()), //Level 87
    R14(new DefencePotionII()), //Level 90
    R15(new DarknessPotionII()), //Level 92
    R16(new PowerPotionII()), //Level 97
    R17(new LifePotionII()); //Level 100   //Exp = 500

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
