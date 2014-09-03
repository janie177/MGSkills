package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Hunting implements ISkill {
    private DetailedMPlayer mp;

    public Hunting() {
    }

    public Hunting(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Hunting";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.HUNTING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.HUNTING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance to get double loot: " + ChatColor.GREEN + mp.getLevel(Skill.HUNTING) * 0.25 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.HUNTING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
