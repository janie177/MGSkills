package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class DarknessPotionI extends PotionRecipe
{

    private static String name = ChatColor.BLACK + "Darkness Potion I";
    private static PotionType appearance = PotionType.WEAKNESS;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.BLINDNESS, 20 * 15, 0, false), new PotionEffect(PotionEffectType.WITHER, 20 * 15, 1, false)};
    private static int level = 69;
    private static int experience = 280;
    private static String[] ingredientNames = {"1 Wither Skull", "2 Gunpowder", "4 Bones"};
    public static int[][] ingredients = {{397, 1, 1}, {289, 2, 0}, {352,4,0}};
    private static String requirement = "Can only be made in the nether";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = true;
    private static int duration = 10;

    public DarknessPotionI()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }



    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return lab.getBiome() == Biome.HELL;
    }
}
