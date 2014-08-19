package com.minegusta.mgskills.util;

import com.minegusta.mgskills.highscores.UpdateHighscores;
import com.minegusta.mgskills.struct.ISkill;

public class LevelUpListener {
    public static void isLevelUp(ISkill skill) {
        boolean stop = false;
        if (skill.getLevel() == 100) return;
        for (int i = 0; i < 100; i++) {
            if (ExpTable.valueOf("L" + Integer.toString(skill.getLevel() + 1)).getExp() <= skill.getExp() && !(skill.getLevel() == 100)) {
                new LevelUp(skill.getPlayer(), skill.getName(), skill.getLevel());
                skill.levelUp();
            } else stop = true;
            if (stop) {
                new UpdateHighscores(skill.getPlayer());
                break;
            }
        }
    }
}
