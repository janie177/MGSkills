package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Healing implements Skill
{
    private DetailedMPlayer mp;

    public Healing(){}

    public Healing(DetailedMPlayer mp)
    {
        this.mp = mp;
    }



    @Override
    public String getName() {
        return "Healing";
    }

    @Override
    public int getLevel() {
        return mp.getHealingLevel();
    }

    @Override
    public int getExp() {
        return mp.getHealing();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp)
    {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "le le le: " + ChatColor.GREEN + mp.getHealingLevel() * 5 +"%"+ChatColor.LIGHT_PURPLE + ".";
    }
    @Override
    public void levelUp() {
        mp.addHealinglevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
