package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Woodcutting implements ISkill {
    private DetailedMPlayer mp;

    public Woodcutting() {
    }

    public Woodcutting(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Woodcutting";
    }

    @Override
    public int getLevel() {
        return mp.getWoodcuttingLevel();
    }

    @Override
    public int getExp() {
        return mp.getWoodcutting();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Fast woodcutting boost strength: " + ChatColor.GREEN + mp.getWoodcuttingLevel() / 20 + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addWoodcuttinglevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
