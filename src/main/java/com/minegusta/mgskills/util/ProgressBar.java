package com.minegusta.mgskills.util;


import com.google.common.collect.Maps;
import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.concurrent.ConcurrentMap;

public class ProgressBar
{
    public static ConcurrentMap<Player, Scoreboard> map = Maps.newConcurrentMap();
    final static ScoreboardManager manager = Bukkit.getScoreboardManager();


    public static void showBar(int exp, final Player p, String skill) {

        if(!map.containsKey(p))createboard(p);
        Scoreboard sb = map.get(p);
        Score level = sb.getObjective(p.getName()).getScore(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE + skill + " + "));
        level.setScore(exp);
        p.setScoreboard(sb);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
            @Override
            public void run() {
                p.setScoreboard(manager.getNewScoreboard());
            }
        }, 30);
    }

    private static void createboard(Player p)
    {
        Scoreboard sb = manager.getNewScoreboard();
        Objective experience = sb.registerNewObjective(p.getName(), "dummy");
        experience.setDisplaySlot(DisplaySlot.SIDEBAR);
        experience.setDisplayName(ChatColor.LIGHT_PURPLE + " ");
        map.put(p, sb);
    }


}
