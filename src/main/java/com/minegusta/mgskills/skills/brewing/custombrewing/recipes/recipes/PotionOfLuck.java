package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import com.minegusta.mgskills.util.RandomNumber;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PotionOfLuck extends PotionRecipe
{

    private static String name = ChatColor.BLUE + "Potion of Luck";
    private static PotionType appearance = PotionType.NIGHT_VISION;
    private static PotionEffect[] effects = null;
    private static int level = 69;
    private static int experience = 300;
    private static String[] ingredientNames = {"4 Glowstone Dust", "10 Redstone", "2 Blaze Rods"};
    public static int[][] ingredients = {{348, 4, 0}, {331, 10, 0}, {369,2,0}};
    private static String requirement = "None! ";
    private static Effect brewEeffect = Effect.PARTICLE_SMOKE;
    private static Effect finishEffect = Effect.CLOUD;
    private static boolean splash = false;
    private static int duration = 10;




    public PotionOfLuck()
    {
        super(name, appearance, level, experience, ingredientNames, ingredients, requirement, effects, brewEeffect, finishEffect, splash, duration);
    }


    @Override
    public ItemStack getPotion()
    {
        //Making the potion

        Potion pot = new Potion(appearance); //The base potion type. Only defines what the potion looks like!!!
        pot.setSplash(splash);
        pot.setLevel(1); //Always keep this 1. Otherwise it will throw errors at you ;-;

        //Convert to itemStack
        ItemStack potion = pot.toItemStack(1);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        meta.addCustomEffect(new PotionEffect(PotionEffectType.getById(RandomNumber.get(23)), RandomNumber.get(15) * 20, RandomNumber.get(3) - 1, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.getById(RandomNumber.get(23)), RandomNumber.get(15) * 20, RandomNumber.get(3) - 1, false), true);

        meta.setDisplayName(name);
        potion.setItemMeta(meta);



        return potion;

    }

    @Override
    public String getInfo()
    {
        String ingredientsList = "";
        for(String s : ingredientNames)
        {
            ingredientsList = ingredientsList + "/n&4 - " + s;
        }
        return "&0&l" + ChatColor.stripColor(name) +
                "/n&0Level: &4" + Integer.toString(level) +
                "/n&0Ingredients:" +
                ingredientsList +
                "/n&0Requirements:" +
                "/n&4 - " + requirement +
                "/n&0Splash: &4???" +
                "/n&0Experience: &4" + experience +
                "/n&0Result: &4???";
    }

    //This method has to be coded in by hand for each potion. It depends on the requirement set.
    @Override
    public boolean hasConditions(Block lab)
    {
        return true;
    }
}
