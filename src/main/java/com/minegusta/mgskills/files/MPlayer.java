package com.minegusta.mgskills.files;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface MPlayer extends ConfigurationSerializable {
    //Getters
    public Player getPlayer();

    public UUID getUUID();

    public int getFishing();

    public int getMining();

    public int getCooking();

    public int getSummoning();

    public int getFarming();

    public int getHunting();

    public int getWoodcutting();

    public int getDigging();

    public int getBrewing();

    public int getHealing();

    public int getExploration();

    public int getFishingLevel();

    public int getMiningLevel();

    public int getCookingLevel();

    public int getSummoningLevel();

    public int getFarmingLevel();

    public int getHuntingLevel();

    public int getWoodcuttingLevel();

    public int getDiggingLevel();

    public int getBrewingLevel();

    public int getHealingLevel();

    public int getExplorationLevel();

    public int getAll();


    //Setters

    public void addFishing(int expAdded);

    public void addMining(int expAdded);

    public void addCooking(int expAdded);

    public void addSummoning(int expAdded);

    public void addFarming(int expAdded);

    public void addHunting(int expAdded);

    public void addWoodcutting(int expAdded);

    public void addDigging(int expAdded);

    public void addBrewing(int expAdded);

    public void addHealing(int expAdded);

    public void addExploration(int expAdded);

    public void addFishingLevel();

    public void addMininglevel();

    public void addCookinglevel();

    public void addSummoninglevel();

    public void addFarminglevel();

    public void addHuntinglevel();

    public void addWoodcuttinglevel();

    public void addDigginglevel();

    public void addBrewinglevel();

    public void addHealinglevel();

    public void addExplorationlevel();
}
