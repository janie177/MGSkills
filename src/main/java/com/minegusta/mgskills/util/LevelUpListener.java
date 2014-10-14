package com.minegusta.mgskills.util;

import org.bukkit.entity.Player;

public class LevelUpListener {
    public static boolean isLevelUp(Player p, int exp, String skill, int level) {
        if (ExpTable.valueOf("L" + Integer.toString(level + 1)).getExp() <= exp && !(level == 100)) {
            LevelUp.advanceLevel(p, skill, level);
            return isLevelUp(p, TempData.getMPlayer(p).getExp(Skill.valueOf(skill.toUpperCase())), skill, level + 1);
        }
        return false;
    }
}
