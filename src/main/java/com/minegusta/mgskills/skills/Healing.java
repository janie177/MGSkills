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
        int healable = 1 + (mp.getLevel(Skill.HEALING) / 5);
        return "You can heal " + ChatColor.DARK_RED + healable + ChatColor.LIGHT_PURPLE + " health.";
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
