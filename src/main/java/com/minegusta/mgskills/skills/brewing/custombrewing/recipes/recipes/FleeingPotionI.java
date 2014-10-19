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


public class FleeingPotionI implements PotionRecipe
{
    /**
     * The name of this potion in color!
     */
    private static String name = ChatColor.DARK_AQUA + "Fleeing Potion I";

    /**
     * The looks of this potion.
     */
    private static PotionType appearance = PotionType.SPEED;

    /**
     * The level needed to brew this.
     */
    private static int level = 76;

    /**
     * Experience earned for brewing this potion.
     */
    private static int experience = 270;

    /**
     * Ingredients needed + amounts.
     */
    private static String ingredient1 = "6 Feathers";
    private static String ingredient2 = "1 Speed Potion I 3:00";
    private static String ingredient3 = "1 Slime Ball";

    public static int[][] ingredients = {{288, 6, 0}, {373, 1, 8194}, {341,1,0}};

    /**
     * The special requirement needed to brew this potion. This has to coded by hand still!
     */
    private static String requirement = "It has to rain.";

    /**
     * Effect#1 of the potion + duration (Seconds) and amplifier (starts at 0).
     */
    private static PotionEffectType effect1 = PotionEffectType.SPEED;
    private static int effect1Ampliefier = 3;
    private static int effect1Duration = 18;

    /**
     * Effect#2 of the potion + duration and amplifier.
     */
    private static PotionEffectType effect2 = PotionEffectType.JUMP;
    private static int effect2Ampliefier = 3;
    private static int effect2Duration = 18;

    /**
     * The brewEeffect to play while brewing.
     */
    private static Effect brewEeffect = Effect.POTION_SWIRL_TRANSPARENT;

    /**
     * The brewEeffect to play when finished.
     */
    private static Effect finishEffect = Effect.ENDER_SIGNAL;

    /**
     * Is this a splash.
     */
    private static boolean splash = true;


    @Override
    public boolean hasConditions(Block lab) {
        return lab.getWorld().hasStorm();
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
                "/n&4 - " + effect1.toString() +
                "/n&4 - " + effect2.toString();
    }

    @Override
    public String getPotionName() {
        return name;
    }

    @Override
    public int getTime() {
        return level;
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
