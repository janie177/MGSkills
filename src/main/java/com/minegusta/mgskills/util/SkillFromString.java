package com.minegusta.mgskills.util;

import com.minegusta.mgskills.skills.*;

public enum SkillFromString
{
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


    private Skill skill;

    private SkillFromString(Skill skill)
    {
        this.skill = skill;
    }

    public Skill getSkill()
    {
        return skill;
    }

}
