package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Digging implements ISkill {
    private DetailedMPlayer mp;

    public Digging() {
    }

    public Digging(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Digging";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.DIGGING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.DIGGING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Bonus experience: " + ChatColor.GREEN + mp.getLevel(Skill.DIGGING) / 5 + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.DIGGING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
