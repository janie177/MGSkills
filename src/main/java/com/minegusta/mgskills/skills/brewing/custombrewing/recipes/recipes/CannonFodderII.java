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

public class CannonFodderII extends PotionRecipe
{
    private static String name = ChatColor.BLUE + "Cannon Fodder II";
    private static PotionType appearance = PotionType.SPEED;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.REGENERATION, 45 * 20, 1, false), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 45* 20, 1, false), new PotionEffect(PotionEffectType.WEAKNESS, 45 * 20, 3), new PotionEffect(PotionEffectType.SLOW, 45 * 20, 1, false)};
    private static int level = 75;
    private static int experience = 400;
    private static String[] ingredientNames = {"2 Golden Apple", "SlownessPotion 1", "1 Diamond Boots"};
    public static int[][] ingredients = {{322, 2, 0}, {373, 1, 8202}, {313,1,0}};
    private static String requirement = "Iron block underneath coal block";
    private static Effect brewEeffect = Effect.HEART;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;



    public CannonFodderII() {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }



    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab) {
        return lab.getRelative(BlockFace.DOWN, 2).getType() == Material.IRON_BLOCK;
    }
}
