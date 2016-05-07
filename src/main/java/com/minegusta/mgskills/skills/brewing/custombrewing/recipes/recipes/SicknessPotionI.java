package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class SicknessPotionI extends PotionRecipe
{
    private static String name = ChatColor.GREEN + "Sickness Potion I";
    private static PotionType appearance = PotionType.INSTANT_DAMAGE;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.HUNGER, 20 * 25, 3, false), new PotionEffect(PotionEffectType.POISON, 20 * 23, 2, false)};
    private static int level = 50;
    private static int experience = 140;
    private static String[] ingredientNames = {"2 Bones", "1 Poisonious Potato", "4 Spider Eyes"};
    //Format: {{ItemID,Amount,DataValue},{ID,Amount,Data},{ID,#,Data}}   All the items required to brew the potion.
    public static int[][] ingredients = {{352, 2, 0}, {394, 1, 0}, {375,4,0}};
    private static String requirement = "Must be in a swamp";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = true;
    private static int duration = 10;

    public SicknessPotionI()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return lab.getBiome() == Biome.SWAMPLAND || lab.getBiome() == Biome.MUTATED_SWAMPLAND;
    }
}
