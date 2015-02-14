package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class LifePotionI extends PotionRecipe
{

    private static String name = ChatColor.GREEN + "Life Potion I";
    private static PotionType appearance = PotionType.REGEN;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.REGENERATION, 20 * 120, 0, false), new PotionEffect(PotionEffectType.ABSORPTION, 20 * 60, 0, false)};
    private static int level = 85;
    private static int experience = 210;
    private static String[] ingredientNames = {"4 Apples", "1 Glistening Melon", "1 Golden Ingot"};
    public static int[][] ingredients = {{260, 4, 0}, {382, 1, 0}, {266,1,0}};
    private static String requirement = "Light level > 8";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;

    public LifePotionI()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return lab.getLightLevel() > 8;
    }
}
