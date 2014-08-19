package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Brewing implements ISkill {
    private DetailedMPlayer mp;

    public Brewing() {
    }

    public Brewing(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Brewing";
    }

    @Override
    public int getLevel() {
        return mp.getBrewingLevel();
    }

    @Override
    public int getExp() {
        return mp.getBrewing();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Moonshine: " + ChatColor.GREEN + mp.getBrewingLevel() * 6 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addBrewinglevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
