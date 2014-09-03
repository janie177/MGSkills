package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
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
        return mp.getLevel(Skill.FARMING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.FARMING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance for extra harvest: " + ChatColor.GREEN + mp.getLevel(Skill.FARMING) * 2 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.FARMING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
