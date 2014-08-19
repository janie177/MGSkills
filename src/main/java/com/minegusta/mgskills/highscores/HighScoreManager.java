package com.minegusta.mgskills.highscores;

import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class HighScoreManager {
    private static FileConfiguration getConf() {
        return HighScoreFile.getConf();
    }

    //Setters

    public void set(String uuid, int index, int level) {
        getConf().set("player" + Integer.toString(index), uuid);
        getConf().set("level" + Integer.toString(index), level);
    }

    //Getters

    public int getLevel(int index) {
        return getConf().getInt("level" + Integer.toString(index), 0);
    }

    public String getUUID(int index) {
        return getConf().getString("player" + Integer.toString(index), "xXObamaSwaggXx");
    }

    public List<Double> getLoc(int index)
    {
        return getConf().getDoubleList("loc" + Integer.toString(index));
    }

    public void setLoc(int index, Player p)
    {
        Location loc = p.getTargetBlock(null, 8).getLocation();
        getConf().set("loc" + Integer.toString(index), Lists.newArrayList(loc.getX(), loc.getY(), loc.getZ()));
        getConf().set("world", p.getWorld().getName());
    }

    public String getWorld()
    {
        return getConf().getString("world", "world");
    }
}

