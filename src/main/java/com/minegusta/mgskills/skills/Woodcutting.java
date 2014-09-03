package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Woodcutting implements ISkill {
    private DetailedMPlayer mp;

    public Woodcutting() {
    }

    public Woodcutting(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Woodcutting";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.WOODCUTTING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.WOODCUTTING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Fast woodcutting boost strength: " + ChatColor.GREEN + mp.getLevel(Skill.WOODCUTTING) / 20 + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.WOODCUTTING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
