package com.minegusta.mgskills.skills.brewing.custombrewing.recipes.recipes;

import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;


public class LifePotionI implements PotionRecipe
{
    /**
     * The name of this potion in color!
     */
    private static String name = ChatColor.GOLD + "Life Potion I";

    /**
     * The looks of this potion.
     */
    private static PotionType appearance = PotionType.FIRE_RESISTANCE;

    /**
     * The level needed to brew this.
     */
    private static int level = 85;

    /**
     * Experience earned for brewing this potion.
     */
    private static int experience = 210;

    /**
     * Ingredients needed + amounts.
     */
    private static String ingredient1 = "4 Apples";
    private static String ingredient2 = "1 Healing I";
    private static String ingredient3 = "1 Gold Ingot";

    public static int[][] ingredients = {{260, 4, 0}, {373, 1, 8197}, {266,1,0}};

    /**
     * The special requirement needed to brew this potion. This has to coded by hand still!
     */
    private static String requirement = "Light level > 7";

    /**
     * Effect#1 of the potion + duration (Seconds) and amplifier (starts at 0).
     */
    private static PotionEffectType effect1 = PotionEffectType.REGENERATION;
    private static int effect1Ampliefier = 0;
    private static int effect1Duration = 15;

    /**
     * Effect#2 of the potion + duration and amplifier.
     */
    private static PotionEffectType effect2 = PotionEffectType.ABSORPTION;
    private static int effect2Ampliefier = 0;
    private static int effect2Duration = 15;

    /**
     * The brewEeffect to play while brewing.
     */
    private static Effect brewEeffect = Effect.VILLAGER_THUNDERCLOUD;

    /**
     * The brewEeffect to play when finished.
     */
    private static Effect finishEffect = Effect.HEART;

    /**
     * Is this a splash.
     */
    private static boolean splash = false;

    /**
     * How long does it take to brew this potion?
     */
    private static int duration = 12;


    @Override
    public boolean hasConditions(Block lab) {
        return lab.getLightLevel() > 7;
    }


    //----------------------------------------------------------------------------------------------------//



    @Override
    public String getInfo() {
        return "&0&l" + ChatColor.stripColor(name) +
                "/n&0Level: &4" + Integer.toString(level) +
                "/n&0Ingredients:" +
                "/n&4 - " + ingredient1 +
                "/n&4 - " + ingredient2 +
                "/n&4 - " + ingredient3 +
                "/n&0Requirements:" +
                "/n&4 - " + requirement +
                "/n&0Splash: &4" + splash +
                "/n&0Experience: &4" + experience +
                "/n&0Result:" +
                "/n&4 - " + effect1.getName() + " " + Integer.toString(effect1Ampliefier + 1) +
                "/n&4 - " + effect2.getName() + " " + Integer.toString(effect2Ampliefier + 1);
    }

    @Override
    public String getPotionName() {
        return name;
    }

    @Override
    public int getTime() {
        return duration;
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

        //Here I set the potion meta.
        //Type duration amp ambient
        meta.addCustomEffect(new PotionEffect(effect1, effect1Duration * 20, effect1Ampliefier, false), true);
        meta.addCustomEffect(new PotionEffect(effect2, effect2Duration * 20, effect2Ampliefier, false), true);
        meta.setDisplayName(name);
        potion.setItemMeta(meta);



        return potion;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public Effect getFinishEffect() {
        return finishEffect;
    }

    @Override
    public Effect getBrewEffect() {
        return brewEeffect;
    }

    @Override
    public int getLevelRequiredment() {
        return level;
    }
}