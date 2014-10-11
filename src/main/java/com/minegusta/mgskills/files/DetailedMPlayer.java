package com.minegusta.mgskills.files;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.ProgressBar;
import com.minegusta.mgskills.util.Skill;
import org.apache.commons.lang.WordUtils;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class DetailedMPlayer implements ConfigurationSerializable {

    private String uuid;

    private Player p;

    private ConcurrentMap<String, Integer> map = Maps.newConcurrentMap();
    
    public DetailedMPlayer(){}

    public DetailedMPlayer(ConcurrentMap<String, Integer> map, Player p)
    {
        this.p = p;
        this.uuid = p.getUniqueId().toString();
        this.map = map;
    }


    public int getAll()
    {
        int i = 0;
        for(String s : map.keySet())
        {
            if(s.contains("Level")) i = i + map.get(s);
        }
        return i;
    }

    public int getLevel(Skill skill)
    {
        return map.get(skill.getSkillName().toLowerCase() + "Level");
    }

    public int getExp(Skill skill)
    {
        return map.get(skill.getSkillName().toLowerCase());
    }

    public void addExp(Skill skill, int experience)
    {
        ProgressBar.showBar(experience, getPlayer(), WordUtils.capitalize(skill.getSkillName()));
        map.put(skill.getSkillName().toLowerCase(), getExp(skill) + experience);
        LevelUpListener.isLevelUp(getPlayer(), getExp(skill), skill.getSkillName(), getLevel(skill));
    }

    public void addLevel(Skill skill)
    {
        map.put(skill.getSkillName().toLowerCase() + "Level", getLevel(skill) + 1);
    }

    public Player getPlayer() {
        return p;
    }

    
    public UUID getUUID() {
        return UUID.fromString(uuid);
    }
    
    public ConcurrentMap<String, Object> serialize() {
        ConcurrentMap<String, Object> map2 = Maps.newConcurrentMap();

        for(String s : map.keySet())
        {
            map2.put(s, map.get(s));
        }
        return map2;
    }
}
