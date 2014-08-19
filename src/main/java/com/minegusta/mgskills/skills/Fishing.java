package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
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
        return mp.getFishingLevel();
    }

    @Override
    public int getExp() {
        return mp.getFishing();
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance to catch double fish: " + ChatColor.GREEN + mp.getFishingLevel() + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addFishingLevel();
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
