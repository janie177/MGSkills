package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Fishing implements ISkill {
    private DetailedMPlayer mp;

    public Fishing() {
    }

    public Fishing(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Fishing";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.FISHING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.FISHING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance to catch double fish: " + ChatColor.GREEN + mp.getLevel(Skill.FISHING) + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.FISHING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
