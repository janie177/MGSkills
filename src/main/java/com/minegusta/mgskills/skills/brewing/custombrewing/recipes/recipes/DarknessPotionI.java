package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class DarknessPotionI extends PotionRecipe
{

    //The name of the potion
    private static String name = ChatColor.BLACK + "Darkness Potion I";

    //What should the potion look like? Does not change the effects of the potion, just looks
    private static PotionType appearance = PotionType.SLOWNESS;

    //The effects this potion will give. Format: Type, Duration(* 20 to make it in seconds), Amplifier, false
    private static PotionEffect[] effects = {new PotionEffect(PotionEffectType.BLINDNESS, 20 * 7, 0, false), new PotionEffect(PotionEffectType.SLOW, 20 * 5, 0, false)};

    //Level needed to brew it
    private static int level = 10;

    //Experience rewarded for brewing it
    private static int experience = 120;

    //All the ingredient names typed out. This has to be done this way because some items like potions use datavalue to be different. This stops me from automating it.
    private static String[] ingredientNames = {"1 Web", "SlownessPotion 1", "2 Spider Eyes"};

    //Format: {{ItemID,Amount,DataValue},{ID,Amount,Data},{ID,#,Data}}   All the items required to brew the potion.
    public static int[][] ingredients = {{30, 1, 0}, {373, 1, 8202}, {375,2,0}};

    //A custom requirement that has to be coded in by hand BELOW.
    private static String requirement = "Light level < 7";

    //The effect to play while brewing
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;

    //The effect to play when done
    private static Effect finishEffect = Effect.CLOUD;

    //Is it splash?
    private static boolean splash = true;

    //How long should brewing take?
    private static int duration = 10;

    public DarknessPotionI() {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab) {
        return lab.getLightLevel() < 7;
    }

}
