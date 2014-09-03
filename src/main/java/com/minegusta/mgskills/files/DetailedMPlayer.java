package com.minegusta.mgskills.files;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.util.ProgressBar;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class DetailedMPlayer implements ConfigurationSerializable {

    private String uuid;

    private ConcurrentMap<String, Integer> map = Maps.newConcurrentMap();
    
    public DetailedMPlayer(){}

    public DetailedMPlayer(ConcurrentMap<String, Integer> map, UUID uuid) {
        this.uuid = uuid.toString();
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

    public int getLevel(String skill)
    {
        return map.get(skill.toLowerCase() + "Level");
    }

    public int getExp(String skill)
    {
        return map.get(skill.toLowerCase());
    }

    public void addExp(String skill, int experience)
    {
        new ProgressBar(experience, getPlayer(), WordUtils.capitalize(skill.toLowerCase()));
        map.put(skill.toLowerCase(), getExp(skill.toLowerCase()) + experience);
    }

    public void addLevel(String skill)
    {
        map.put(skill.toLowerCase() + "Level", getLevel(skill) + 1);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getUUID());
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
