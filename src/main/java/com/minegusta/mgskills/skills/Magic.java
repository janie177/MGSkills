package com.minegusta.mgskills.skills;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Magic implements ISkill {
    private DetailedMPlayer mp;

    public Magic() {
    }

    public Magic(DetailedMPlayer mp) {
        this.mp = mp;
    }


    @Override
    public String getName() {
        return "Magic";
    }

    @Override
    public int getLevel() {
        return mp.getLevel(Skill.MAGIC);
    }

    @Override
    public int getExp() {
        return mp.getExp(Skill.MAGIC);
    }

    @Override
    public void insertMPlayer(DetailedMPlayer mp) {
        this.mp = mp;
    }

    @Override
    public String getSpecialBoost() {
        return ChatColor.DARK_PURPLE + "Mana: " + ChatColor.DARK_AQUA + (mp.getLevel(Skill.MAGIC) + 15) + "%" + ChatColor.LIGHT_PURPLE + ".";
    }

    @Override
    public void levelUp() {
        mp.addLevel(Skill.MAGIC);
    }

    @Override
    public Player getPlayer() {
        return mp.getPlayer();
    }
}
