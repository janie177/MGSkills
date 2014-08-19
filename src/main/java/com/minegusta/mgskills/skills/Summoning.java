package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Summoning implements ISkill {
    private DetailedMPlayer mp;

    public Summoning() {
    }

    public Summoning(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Summoning";
    }

    @Override
    public int getLevel() {
        return mp.getSummoningLevel();
    }

    @Override
    public int getExp() {
        return mp.getSummoning();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Who knows: " + ChatColor.GREEN + mp.getSummoningLevel() / 2 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addSummoninglevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
