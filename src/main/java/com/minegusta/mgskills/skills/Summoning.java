package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
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
        return mp.getLevel(Skill.SUMMONING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.SUMMONING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Who knows: " + ChatColor.GREEN + mp.getLevel(Skill.SUMMONING) / 2 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.SUMMONING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
