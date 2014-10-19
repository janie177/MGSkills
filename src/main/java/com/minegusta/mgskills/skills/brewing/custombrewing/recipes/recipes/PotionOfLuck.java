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


public class PotionOfLuck implements PotionRecipe
{
    /**
     * The name of this potion in color!
     */
    private static String name = ChatColor.GOLD + "Potion Of Luck";

    /**
     * The looks of this potion.
     */
    private static PotionType appearance = PotionType.NIGHT_VISION;

    /**
     * The level needed to brew this.
     */
    private static int level = 86;

    /**
     * Experience earned for brewing this potion.
     */
    private static int experience = 250;

    /**
     * Ingredients needed + amounts.
     */
    private static String ingredient1 = "4 Glow Stone";
    private static String ingredient2 = "10 Red Stone";
    private static String ingredient3 = "2 Blaze Rod";

    public static int[][] ingredients = {{89, 4, 0}, {331, 10, 0}, {369,2,0}};

    /**
     * The special requirement needed to brew this potion. This has to coded by hand still!
     */
    private static String requirement = "Nothing!";

    /**
     * Effect#1 of the potion + duration (Seconds) and amplifier (starts at 0).
     */
    private static String effect1 = "???";

    /**
     * Effect#2 of the potion + duration and amplifier.
     */
    private static String effect2 = "???";

    /**
     * The brewEeffect to play while brewing.
     */
    private static Effect brewEeffect = Effect.MOBSPAWNER_FLAMES;

    /**
     * The brewEeffect to play when finished.
     */
    private static Effect finishEffect = Effect.FLYING_GLYPH;

    /**
     * Is this a splash.
     */
    private static String splash = "???";

    /**
     * How long does it take to brew this potion?
     */
    private static int duration = 15;

    @Override
    public boolean hasConditions(Block lab) {
        return true;
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
                "/n&4 - " + effect1 +
                "/n&4 - " + effect2;
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
        pot.setSplash(RandomNumber.getBoolean());
        pot.setLevel(1); //Always keep this 1. Otherwise it will throw errors at you ;-;

        //Convert to itemStack
        ItemStack potion = pot.toItemStack(1);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        //Here I set the potion meta.
        //Type duration amp ambient
        meta.addCustomEffect(new PotionEffect(PotionEffectType.getById(RandomNumber.get(23)), RandomNumber.get(35) * 20, RandomNumber.get(3) - 1, false), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.getById(RandomNumber.get(23)), RandomNumber.get(35) * 20, RandomNumber.get(3) - 1, false), true);
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
