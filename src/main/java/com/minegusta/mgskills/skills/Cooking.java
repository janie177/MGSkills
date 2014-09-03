package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Cooking implements ISkill {
    private DetailedMPlayer mp;

    public Cooking() {
    }

    public Cooking(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getName() {
        return "Cooking";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.COOKING);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.COOKING);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return "Chance to get twice as much food: " + ChatColor.GREEN + mp.getLevel(Skill.COOKING) + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.COOKING);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
