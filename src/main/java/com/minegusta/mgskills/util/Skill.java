package com.minegusta.mgskills.util;

import com.minegusta.mgskills.skills.*;
import com.minegusta.mgskills.struct.ISkill;

public enum Skill {
    FISHING(new Fishing()),
    MINING(new Mining()),
    COOKING(new Cooking()),
    SUMMONING(new Summoning()),
    FARMING(new Farming()),
    HUNTING(new Hunting()),
    WOODCUTTING(new Woodcutting()),
    DIGGING(new Digging()),
    BREWING(new Brewing()),
    HEALING(new Healing()),
    EXPLORATION(new Exploration());


    private ISkill skill;

    private Skill(ISkill skill) {
        this.skill = skill;
    }

    public ISkill getSkill() {
        return skill;
    }

    public String getName()
    {
        return name().toLowerCase();
    }

}
