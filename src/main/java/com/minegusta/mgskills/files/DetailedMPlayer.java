package com.minegusta.mgskills.files;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.util.ProgressBar;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class DetailedMPlayer implements MPlayer
{
    //All skills available
    private UUID uuid;
    private int fishing,mining,cooking,summoning,farming,hunting,woodcutting,digging,brewing,healing,exploration,all;
    private int fishingLevel,miningLevel,cookingLevel,summoningLevel,farmingLevel,huntingLevel,woodcuttingLevel,diggingLevel,brewingLevel,healingLevel,explorationLevel;

    public DetailedMPlayer(FileConfiguration conf, UUID uuid)
    {
        this.uuid = uuid;
        fishing = conf.getInt("fishing", 0);
        mining = conf.getInt("mining", 0);
        cooking = conf.getInt("cooking", 0);
        summoning = conf.getInt("summoning", 0);
        farming = conf.getInt("farming", 0);
        hunting = conf.getInt("hunting", 0);
        woodcutting = conf.getInt("woodcutting", 0);
        digging = conf.getInt("digging", 0);
        brewing = conf.getInt("brewing", 0);
        healing = conf.getInt("healing", 0);
        exploration = conf.getInt("exploration", 0);
        
        fishingLevel = conf.getInt("fishingLevel", 1);
        miningLevel = conf.getInt("miningLevel", 1);
        cookingLevel = conf.getInt("cookingLevel", 1);
        summoningLevel = conf.getInt("summoningLevel", 1);
        farmingLevel = conf.getInt("farmingLevel", 1);
        huntingLevel = conf.getInt("huntingLevel", 1);
        woodcuttingLevel = conf.getInt("woodcuttingLevel", 1);
        diggingLevel = conf.getInt("diggingLevel", 1);
        brewingLevel = conf.getInt("brewingLevel", 1);
        healingLevel = conf.getInt("healingLevel", 1);
        explorationLevel = conf.getInt("explorationLevel", 1);
        
        all = fishingLevel+miningLevel+cookingLevel+summoningLevel+farmingLevel+huntingLevel+woodcuttingLevel+diggingLevel+brewingLevel+healingLevel+explorationLevel;
    }


    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    //Getters
    @Override
    public int getFishing()
    {
        return fishing;
    }
    @Override
    public int getMining()
    {
        return mining;
    }
    @Override
    public int getCooking()
    {
        return cooking;
    }
    @Override
    public int getSummoning()
    {
        return summoning;
    }
    @Override
    public int getFarming()
    {
        return farming;
    }
    @Override
    public int getHunting()
    {
        return hunting;
    }
    @Override
    public int getWoodcutting()
    {
        return woodcutting;
    }
    @Override
    public int getDigging()
    {
        return digging;
    }
    @Override
    public int getBrewing()
    {
        return brewing;
    }
    @Override
    public int getHealing()
    {
        return healing;
    }
    @Override
    public int getAll()
    {
        return all;
    }
    @Override
    public int getExploration()
    {
        return exploration;
    }

    @Override
    public int getFishingLevel() {
        return fishingLevel;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getCookingLevel() {
        return cookingLevel;
    }

    @Override
    public int getSummoningLevel() {
        return summoningLevel;
    }

    @Override
    public int getFarmingLevel() {
        return farmingLevel;
    }

    @Override
    public int getHuntingLevel() {
        return huntingLevel;
    }

    @Override
    public int getWoodcuttingLevel() {
        return woodcuttingLevel;
    }

    @Override
    public int getDiggingLevel() {
        return diggingLevel;
    }

    @Override
    public int getBrewingLevel() {
        return brewingLevel;
    }

    @Override
    public int getHealingLevel() {
        return healingLevel;
    }

    @Override
    public int getExplorationLevel() {
        return explorationLevel;
    }


    //Setters
    @Override
    public void addFishing(int expAdded)
    {
        fishing = fishing + expAdded;
    }

    @Override
    public void setAll() {
        all = fishingLevel+miningLevel+cookingLevel+summoningLevel+farmingLevel+huntingLevel+woodcuttingLevel+diggingLevel+brewingLevel+healingLevel+explorationLevel;
    }
    @Override
    public void addMining(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Mining");
        mining = mining + expAdded;
    }
    @Override
    public void addCooking(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Cooking");
        cooking = cooking + expAdded;
    }
    @Override
    public void addSummoning(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Summoning");
        summoning = summoning + expAdded;
    }
    @Override
    public void addFarming(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Farming");
        farming = farming + expAdded;
    }
    @Override
    public void addHunting(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Hunting");
        hunting = hunting + expAdded;
    }
    @Override
    public void addWoodcutting(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Woodcutting");
        woodcutting = woodcutting + expAdded;
    }
    @Override
    public void addDigging(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Digging");
        digging = digging + expAdded;
    }
    @Override
    public void addBrewing(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Brewing");
        brewing = brewing + expAdded;
    }
    @Override
    public void addHealing(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Healing");
        healing = healing + expAdded;
    }
    @Override
    public void addExploration(int expAdded)
    {
        new ProgressBar(expAdded, getPlayer(), "Exploration");
        exploration = exploration + expAdded;
    }

    @Override
    public void addFishingLevel() {
        fishingLevel++;
    }

    @Override
    public void addMininglevel() {
        miningLevel++;
    }

    @Override
    public void addCookinglevel() {
        cookingLevel++;
    }

    @Override
    public void addSummoninglevel() {
        summoningLevel++;
    }

    @Override
    public void addFarminglevel() {
        farmingLevel++;
    }

    @Override
    public void addHuntinglevel() {
        huntingLevel++;
    }

    @Override
    public void addWoodcuttinglevel() {
        woodcuttingLevel++;
    }

    @Override
    public void addDigginglevel() {
        diggingLevel++;
    }

    @Override
    public void addBrewinglevel() {
        brewingLevel++;
    }

    @Override
    public void addHealinglevel() {
        healingLevel++;
    }

    @Override
    public void addExplorationlevel() {
        explorationLevel++;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("fishing", fishing);
        map.put("mining", mining);
        map.put("cooking", cooking);
        map.put("summoning", summoning);
        map.put("farming", farming);
        map.put("hunting", hunting);
        map.put("woodcutting", woodcutting);
        map.put("digging", digging);
        map.put("brewing", brewing);
        map.put("healing", healing);
        map.put("exploration", exploration);

        map.put("fishingLevel", fishingLevel);
        map.put("miningLevel", miningLevel);
        map.put("cookingLevel", cookingLevel);
        map.put("summoningLevel", summoningLevel);
        map.put("farmingLevel", farmingLevel);
        map.put("huntingLevel", huntingLevel);
        map.put("woodcuttingLevel", woodcuttingLevel);
        map.put("diggingLevel", diggingLevel);
        map.put("brewingLevel", brewingLevel);
        map.put("healingLevel", healingLevel);
        map.put("explorationLevel", explorationLevel);
        map.put("all", all);
        return map;
    }
}
