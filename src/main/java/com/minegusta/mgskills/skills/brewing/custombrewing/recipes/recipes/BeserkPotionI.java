package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class BeserkPotionI extends PotionRecipe
{

    private static String name = ChatColor.RED + "Beserk Potion I";
    private static PotionType appearance = PotionType.STRENGTH;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.REGENERATION, 45 * 20 , 0, false),     new PotionEffect(PotionEffectType.SPEED, 45 * 20 , 0, false), new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 45 * 20, 0, false)};
    private static int level = 35;
    private static int experience = 165;
    private static String[] ingredientNames = {"1 Blaze Powder", "1 Ghast tear", " 1 Sugar"};
    public static int[][] ingredients = {{377, 1, 0}, {370, 1, 0}, {353,1,0}};
    private static String requirement = "None!";
    private static Effect brewEeffect = Effect.MOBSPAWNER_FLAMES;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;

    public BeserkPotionI() {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab) {
        return true;
    }
}
