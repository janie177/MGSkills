package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class DarknessPotionII extends PotionRecipe
{

    private static String name = ChatColor.BLACK + "Darkness Potion II";
    private static PotionType appearance = PotionType.SLOWNESS;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.BLINDNESS, 20 * 7, 0, false), new PotionEffect(PotionEffectType.SLOW, 20 * 20, 1, false)};
    private static int level = 93;
    private static int experience = 320;
    private static String[] ingredientNames = {"2 Web", "1 Obsidian", "4 Spider Eyes"};
    public static int[][] ingredients = {{30, 2, 0}, {49, 1,0 }, {375,4,0}};
    private static String requirement = "Light level < 7";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = true;
    private static int duration = 10;


    public DarknessPotionII()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }



    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return lab.getLightLevel() < 7;
    }
}
