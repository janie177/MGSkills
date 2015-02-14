package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class FeedingPotionI extends PotionRecipe {

    private static String name = ChatColor.GREEN + "Feeding Potion I";
    private static PotionType appearance = PotionType.SPEED;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.SATURATION, 20 * 15, 1, false), new PotionEffect(PotionEffectType.SPEED, 20 * 300, 0, false)};
    private static int level = 45;
    private static int experience = 169;
    private static String[] ingredientNames = {"1 Steak", "1 Melon Slice", "1 Normal Cooked Fish"};
    public static int[][] ingredients = {{364, 1, 0}, {360, 1, 0}, {350, 1, 0}};
    private static String requirement = "None!";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;

    public FeedingPotionI() {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return true;
    }
}
