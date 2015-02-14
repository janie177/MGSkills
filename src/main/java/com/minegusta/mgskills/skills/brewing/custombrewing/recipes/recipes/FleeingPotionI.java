package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class FleeingPotionI extends PotionRecipe
{

    private static String name = ChatColor.BLUE + "Fleeing Potion I";
    private static PotionType appearance = PotionType.SPEED;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.SPEED, 20 * 14, 2, false), new PotionEffect(PotionEffectType.JUMP, 20 * 20, 3, false)};
    private static int level = 76;
    private static int experience = 240;
    private static String[] ingredientNames = {"6 Feathers", "1 Speed (3:00) Potion", "1 Slimeball"};
    public static int[][] ingredients = {{288, 6, 0}, {373, 1, 8194}, {341,1,0}};
    private static String requirement = "Must be Raining";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = true;
    private static int duration = 10;

    public FleeingPotionI()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return lab.getWorld().hasStorm();
    }
}
