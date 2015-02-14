package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class MinerpotionI extends PotionRecipe
{
    private static String name = ChatColor.BLUE + "Miner Potion I";
    private static PotionType appearance = PotionType.FIRE_RESISTANCE;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 300, 1, false), new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 300, 0, false)};
    private static int level = 38;
    private static int experience = 150;
    private static String[] ingredientNames = {"1 Iron Pickaxe", "2 Coal", "2 Redstone"};
    public static int[][] ingredients = {{257, 1, 0}, {263, 2, 0}, {331,2,0}};
    private static String requirement = "Lava beneath Coal Block";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;



    public MinerpotionI()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return lab.getRelative(BlockFace.DOWN, 2).getType() == Material.STATIONARY_LAVA;
    }
}
