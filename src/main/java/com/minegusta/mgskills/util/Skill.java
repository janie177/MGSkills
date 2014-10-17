package com.minegusta.mgskills.util;

import com.minegusta.mgskills.skills.*;
import com.minegusta.mgskills.struct.ISkill;

public enum Skill {
    FISHING(new Fishing(), "Fishing"),
    MINING(new Mining(), "Mining"),
    COOKING(new Cooking(), "Cooking"),
    SUMMONING(new Summoning(), "Summoning"),
    FARMING(new Farming(), "Farming"),
    HUNTING(new Hunting(), "Hunting"),
    WOODCUTTING(new Woodcutting(), "Woodcutting"),
    DIGGING(new Digging(), "Digging"),
    BREWING(new Brewing(), "Brewing"),
    HEALING(new Healing(), "Healing"),
    EXPLORATION(new Exploration(), "Exploration");


    private ISkill skill;
    private String skillName;

    private Skill(ISkill skill, String skillName) {
        this.skill = skill;
        this.skillName = skillName;
    }

    public ISkill getSkill() {
        return skill;
    }

    public String getSkillName() {
        return skillName;
    }

}
