package com.minegusta.mgskills.files;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.util.ExpMultiplier;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.Skill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class DetailedMPlayer {
    private String uuid;

    private final static int expMultiplier = ExpMultiplier.get();

    private boolean showExp = false;

    private ConcurrentMap<String, Integer> map = Maps.newConcurrentMap();

    public DetailedMPlayer() {
    }

    public DetailedMPlayer(ConcurrentMap<String, Integer> map, Player p) {
        this.uuid = p.getUniqueId().toString();
        this.map = map;
    }

    public DetailedMPlayer(ConcurrentMap<String, Integer> map, UUID id) {
        this.uuid = id.toString();
        this.map = map;
    }

    public void setShowExp(boolean show) {
        showExp = show;
    }

    public boolean showExp() {
        return showExp;
    }

    public int getAll() {
        int i = 0;
        for (String s : map.keySet()) {
            if (s.contains("Level")) i = i + map.get(s);
        }
        return i;
    }

    public int getLevel(Skill skill) {
        return map.get(skill.getSkillName().toLowerCase() + "Level");
    }

    public int getExp(Skill skill) {
        return map.get(skill.getSkillName().toLowerCase());
    }

    public void addExp(Skill skill, int experience) {
        if (!getPlayer().getGameMode().equals(GameMode.SURVIVAL)) return;
        int added = experience * expMultiplier;
        map.put(skill.getSkillName().toLowerCase(), getExp(skill) + added);
        LevelUpListener.isLevelUp(getPlayer(), getExp(skill), skill.getSkillName(), getLevel(skill));
        if (showExp)
            getPlayer().sendMessage(ChatColor.YELLOW + "+ " + ChatColor.RED + Integer.toString(added) + " " + ChatColor.GOLD + skill.getSkillName() + ChatColor.YELLOW + " experience.");
    }

    public void addLevel(Skill skill) {
        map.put(skill.getSkillName().toLowerCase() + "Level", getLevel(skill) + 1);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getUUID());
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public ConcurrentMap<String, Object> serialize() {
        ConcurrentMap<String, Object> map2 = Maps.newConcurrentMap();

        for (String s : map.keySet()) {
            map2.put(s, map.get(s));
        }
        return map2;
    }
}
