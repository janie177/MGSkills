package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Mining implements ISkill {
    private DetailedMPlayer mp;

    public Mining() {
    }

    public Mining(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getName() {
        return "Mining";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.MINING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.MINING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        double chance = 0.5 + (mp.getLevel(Skill.MINING) * 0.05);
        return "Chance to randomly find ores: " + ChatColor.GREEN + chance + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.MINING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
