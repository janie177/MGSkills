package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Farming implements ISkill {
    private DetailedMPlayer mp;

    public Farming() {
    }

    public Farming(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Farming";
    }

    @Override
    public int getLevel() {
        return mp.getFarmingLevel();
    }

    @Override
    public int getExp() {
        return mp.getFarming();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance for extra harvest: " + ChatColor.GREEN + mp.getFarmingLevel() * 2 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addFarminglevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
