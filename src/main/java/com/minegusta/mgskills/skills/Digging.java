package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
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
        return mp.getDiggingLevel();
    }

    @Override
    public int getExp() {
        return mp.getDigging();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Bonus experience: " + ChatColor.GREEN + mp.getDiggingLevel() / 5 + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addDigginglevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
