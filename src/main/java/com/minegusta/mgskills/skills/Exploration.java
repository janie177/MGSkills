package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Exploration implements ISkill {
    private DetailedMPlayer mp;

    public Exploration() {
    }

    public Exploration(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Exploration";
    }

    @Override
    public int getLevel() {
        return mp.getExplorationLevel();
    }

    @Override
    public int getExp() {
        return mp.getExploration();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Much explore, very wow: " + ChatColor.GREEN + mp.getExplorationLevel() * 2 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addExplorationlevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
