package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Healing implements ISkill {
    private DetailedMPlayer mp;

    public Healing() {
    }

    public Healing(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Healing";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.HEALING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.HEALING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "le le le: " + ChatColor.GREEN + mp.getLevel(Skill.HEALING) * 5 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.HEALING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
