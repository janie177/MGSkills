package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.material.Cauldron;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class DistortionPotionII extends PotionRecipe
{

    private static String name = ChatColor.BLACK + "Distortion Potion II";
    private static PotionType appearance = PotionType.INSTANT_DAMAGE;
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.CONFUSION, 20 * 20, 1, false), new PotionEffect(PotionEffectType.POISON, 20 * 25, 1, false)};
    private static int level = 86;
    private static int experience = 380;
    private static String[] ingredientNames = {"6 Poisonous Potatoes", "2 Red Mushroom", "1 Slime block"};
    public static int[][] ingredients = {{394, 6, 0}, {40, 2, 0}, {165,2,0}};
    private static String requirement = "Cauldron needs water in it";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = true;
    private static int duration = 10;


    public DistortionPotionII()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        Cauldron cauldron = (Cauldron) lab.getState();
        return cauldron.isFull();
    }
}
