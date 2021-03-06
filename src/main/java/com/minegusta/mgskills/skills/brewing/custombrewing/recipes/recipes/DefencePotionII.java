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

public class DefencePotionII extends PotionRecipe
{

    private static String name = ChatColor.BLUE + "Defense Potion II";
    private static PotionType appearance = PotionType.NIGHT_VISION;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60, 0, false), new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 120, 0, false)};
    private static int level = 90;
    private static int experience = 310;
    private static String[] ingredientNames = {"1 Sugar", "1 Cookie", "1 Diamond"};
    public static int[][] ingredients = {{353, 1, 0}, {357, 1, 0}, {264,1,0}};
    private static String requirement = "Anvil Near Lab";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;




    public DefencePotionII()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }



    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        for(BlockFace bf : BlockFace.values())
        {
            if(lab.getRelative(bf).getType().equals(Material.ANVIL))
            {
                return true;
            }
        }
        return false;
    }
}
