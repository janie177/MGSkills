package com.minegusta.mgskills.skills.brewing.custombrewing.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes.*;

public enum Recipes {
    //Format: Material, Amount, DataValue
    R1(DarknessPotionI.ingredients, new DarknessPotionI()), // level 10  //Exp = 120
    R2(PowerPotionI.ingredients, new PowerPotionI()), // level 24
    R3(SicknessPotionI.ingredients, new SicknessPotionI()), //Level 32
    R4(MinerPotionI.ingredients, new MinerPotionI()), //Level 38
    R5(FeedingPotionI.ingredients, new FeedingPotionI()), //Level 45
    R6(DistortionPotionI.ingredients, new DistortionPotionI()), //Level 54
    R7(DefencePotionI.ingredients, new DefencePotionI()), //Level 62
    R8(WitheringPotionI.ingredients, new WitheringPotionI()), //Level 69
    R9(FleeingPotionI.ingredients, new FleeingPotionI()), //Level 76
    R10(MinerPotionII.ingredients, new MinerPotionII()), //Level 80
    R11(LifePotionI.ingredients, new LifePotionI()), //Level 85
    R12(PotionOfLuck.ingredients, new PotionOfLuck()), //Level 86
    R13(SicknessPotionII.ingredients, new SicknessPotionII()), //Level 87
    R14(DefencePotionII.ingredients, new DefencePotionII()), //Level 90
    R15(DarknessPotionII.ingredients, new DarknessPotionII()), //Level 92
    R16(PowerPotionII.ingredients, new PowerPotionII()), //Level 97
    R17(LifePotionII.ingredients, new LifePotionII()); //Level 100   //Exp = 500


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
