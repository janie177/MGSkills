package com.minegusta.mgskills.skills.brewing.custombrewing;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.PotionRecipe;
import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.Recipes;
import com.minegusta.mgskills.util.CompareRow;
import com.minegusta.mgskills.util.SendMessage;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class BrewingProcess {
    private int[][] i;
    private Block lab;
    private int TASK;
    private int PARTICLES;
    private int time;
    private int recipeID;
    private ItemStack potion;
    private String potionName;
    private int experience;
    private Effect finishEffect;
    private Effect brewEffect;
    private boolean hasConditions;
    private int requiredLevel;
    private int playerLevel;

    public BrewingProcess(Block lab, int[][] i, int playerLevel)
    {
        this.lab = lab;
        this.i = i;
        this.playerLevel = playerLevel;
    }

    private int isRecipe() {
        {
            for (Recipes r : Recipes.values()) {
                if (i.length != r.getIngredients().length)
                {
                    continue;
                }

                else if(Arrays.deepEquals(CompareRow.order(i), CompareRow.order(r.getIngredients())))
                {
                    return r.getIndex();
                }
            }
            return 0;
        }
    }

    private void applyRecipe() {
        PotionRecipe r = Recipes.valueOf("R" + Integer.toString(recipeID)).getRecipe();
        this.time = r.getTime();
        this.potion = r.getPotion();
        this.experience = r.getExperience();
        this.finishEffect = r.getFinishEffect();
        this.brewEffect = r.getBrewEffect();
        this.potionName = r.getPotionName();
        this.requiredLevel = r.getLevelRequiredment();
        this.hasConditions = r.hasConditions(lab);
    }

    public String start() {
        if (!(i.length == 0)) {
            if(BrewingData.hasBrewingLab(lab.getLocation()))
            {
                returnItems();
                return "Someone else already started brewing here!";
            }
            recipeID = isRecipe();
            if (recipeID == 0) {
                returnItems();
                return "That is not a valid recipe!";
            }

            applyRecipe();

            if (playerLevel < requiredLevel)
            {
                returnItems();
                return "You do not yet know how to make that potion!";
            }

            if (!hasConditions)
            {
                returnItems();
                return "The conditions for brewing this potion are not met!";
            }

            BrewingData.addLocation(lab.getLocation(), this);
            TASK = brewTask();
            PARTICLES = playEffect();
            return "You start brewing a potion!";
        }
        return "You close your cauldron..";
    }


    public void cancelBrew() {
        //Cancel the brewing
        Bukkit.getScheduler().cancelTask(TASK);
        //Cancel the effects
        Bukkit.getScheduler().cancelTask(PARTICLES);
        //Remove from the brewMap
        BrewingData.removeFromMap(lab.getLocation());
        //Return the items to the player.
        returnItems();
    }

    private void returnItems() {
        for (int[] item : i) {
            lab.getWorld().dropItemNaturally(lab.getLocation().add(0, 1, 0), new ItemStack(Material.getMaterial(item[0]), item[1], (short) item[2]));
        }
    }

    private int playEffect() {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
            @Override
            public void run() {
                lab.getWorld().spigot().playEffect(lab.getLocation(), brewEffect, 0, 0, 1, 1, 1, 1, 10, 14);
                lab.getWorld().playSound(lab.getLocation(), Sound.LAVA_POP, 1, 1);
            }
        }, 0, 20);
    }

    private int brewTask() {
        return Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
            @Override
            public void run() {
                //Cancel the effects
                Bukkit.getScheduler().cancelTask(PARTICLES);

                //FinishBrew
                lab.getWorld().dropItemNaturally(lab.getLocation().add(0, 1, 0), potion);
                lab.getWorld().spigot().playEffect(lab.getLocation(), finishEffect, 0, 0, 1, 1, 1, 1, 5, 20);

                //Apply exp to the people around the potion.
                Entity temp = lab.getWorld().spawnEntity(lab.getLocation(), EntityType.EXPERIENCE_ORB);
                for (Entity ent : temp.getNearbyEntities(15, 15, 15)) {
                    if (ent instanceof Player) {
                        SendMessage.send((Player) ent, "You successfully made a " + potionName + ".");
                        TempData.getMPlayer((Player) ent).addExp(Skill.BREWING, experience);
                    }
                }
                temp.remove();
                BrewingData.removeFromMap(lab.getLocation());

            }
        }, time * 20);
    }
}
