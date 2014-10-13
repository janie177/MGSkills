package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Brewing implements ISkill {
    private DetailedMPlayer mp;

    public Brewing() {
    }

    public Brewing(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Brewing";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.BREWING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.BREWING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance to clone potions on throwing them: " + ChatColor.GREEN + mp.getLevel(Skill.BREWING) * 3 + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp()
    {
        mp.addLevel(Skill.BREWING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
