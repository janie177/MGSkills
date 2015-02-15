package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PowerPotionII extends PotionRecipe
{
    private static String name = ChatColor.GOLD + "Power Potion II";
    private static PotionType appearance = PotionType.STRENGTH;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 120, 1, false), new PotionEffect(PotionEffectType.SPEED, 20 * 120, 1,false), new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 120, 0, false)};
    private static int level = 63;
    private static int experience = 330;
    private static String[] ingredientNames = {"1 Emerald", "5 Golden Nuggets", "1 Iron Sword"};
    public static int[][] ingredients = {{338, 1, 0}, {371, 5, 0}, {267,1,0}};
    private static String requirement = "Light level > 8";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;


    public PowerPotionII()
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
