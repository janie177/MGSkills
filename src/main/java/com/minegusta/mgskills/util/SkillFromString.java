package com.minegusta.mgskills.util;

import com.minegusta.mgskills.skills.*;
import com.minegusta.mgskills.struct.ISkill;

public enum SkillFromString {
    fishing(new Fishing()),
    mining(new Mining()),
    cooking(new Cooking()),
    summoning(new Summoning()),
    farming(new Farming()),
    hunting(new Hunting()),
    woodcutting(new Woodcutting()),
    digging(new Digging()),
    brewing(new Brewing()),
    healing(new Healing()),
    exploration(new Exploration());


    private ISkill skill;

    private SkillFromString(ISkill skill) {
        this.skill = skill;
    }

    public ISkill getSkill() {
        return skill;
    }

}
