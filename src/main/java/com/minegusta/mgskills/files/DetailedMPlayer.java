package com.minegusta.mgskills.files;

import com.minegusta.mgskills.util.ExpMultiplier;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.SendMessage;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.json.JsonSection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DetailedMPlayer {
    private String uuid;

    private final static int expMultiplier = ExpMultiplier.get();

    private boolean showExp = false;

    private JsonSection conf;

    public DetailedMPlayer() {
    }

    public DetailedMPlayer(JsonSection conf, Player p) {
        this.uuid = p.getUniqueId().toString();
        this.conf = conf;
    }

    public DetailedMPlayer(JsonSection conf, UUID id) {
        this.uuid = id.toString();
        this.conf = conf;
    }

    public void setShowExp(boolean show) {
        showExp = show;
    }

    public boolean showExp() {
        return showExp;
    }

    public int getAll()
    {
        int total = 0;
        for(String s : conf.getKeys())
        {
            if(s.contains("Level"))
            {
                total = total + conf.getInt(s);
            }
        }
        return total;
    }

    public int getLevel(Skill skill)
    {
        return conf.getInt(skill.getSkillName().toLowerCase() + "Level", 1);
    }

    public int getExp(Skill skill) {
        return conf.getInt(skill.getSkillName().toLowerCase(), 0);
    }

    public void addExp(Skill skill, int experience) {
        if (!getPlayer().getGameMode().equals(GameMode.SURVIVAL)) return;
        int added = experience * expMultiplier;
        if(getLevel(skill) < 100)conf.set(skill.getSkillName().toLowerCase(), getExp(skill) + added);
        LevelUpListener.isLevelUp(getPlayer(), getExp(skill), skill.getSkillName(), getLevel(skill));
        if (showExp)
        {
            getPlayer().sendMessage(ChatColor.YELLOW + "+ " + ChatColor.RED + Integer.toString(added) + " " + ChatColor.GOLD + skill.getSkillName() + ChatColor.YELLOW + " experience.");
            if(expMultiplier > 1) SendMessage.send(getPlayer(), "Your experience is multiplied by " + ChatColor.RED + expMultiplier + ChatColor.YELLOW + "!");
        }
    }

    public void addLevel(Skill skill) {
        conf.set(skill.getSkillName().toLowerCase() + "Level", getLevel(skill) + 1);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getUUID());
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public JsonSection getConf()
    {
        return conf;
    }
}
