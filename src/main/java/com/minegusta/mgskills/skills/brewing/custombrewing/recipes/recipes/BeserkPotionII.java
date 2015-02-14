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

public class BeserkPotionII extends PotionRecipe
{
    private static String name = ChatColor.RED + "Beserk Potion II";
    private static PotionType appearance = PotionType.STRENGTH;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.REGENERATION, 30 * 20 , 1, false), new PotionEffect(PotionEffectType.SPEED, 120 * 20 , 1, false), new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90 * 20, 1, false)};
    private static int level = 72;
    private static int experience = 285;
    private static String[] ingredientNames = {"2 Blaze Powder", "2 Ghast tear", "2 Sugar", "3 Glowstone  Dust", "1 Redstone Dust"};
    public static int[][] ingredients = {{377, 2, 0}, {370, 2, 0}, {353,2,0},{348,3,0},{331,1,0}};
    private static String requirement = "Redstone Block underneath coal block";
    private static Effect brewEeffect = Effect.FLAME;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;

        public BeserkPotionII() {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }


    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab) {
        return lab.getRelative(BlockFace.DOWN, 2).getType() == Material.REDSTONE_BLOCK;
    }
}
