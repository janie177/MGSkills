package com.minegusta.mgskills.util;

import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ProgressBar {
    final static ScoreboardManager manager = Bukkit.getScoreboardManager();

    public static void showBar(int level, final Player p, String skill) {
        Scoreboard sb = manager.getNewScoreboard();

        Objective experience = sb.registerNewObjective(p.getName(), "dummy");

        experience.setDisplaySlot(DisplaySlot.SIDEBAR);
        experience.setDisplayName(ChatColor.LIGHT_PURPLE + " ");
        Score score = experience.getScore(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE + skill + " "));

        score.setScore(level);

        p.setScoreboard(sb);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
            @Override
            public void run() {
                p.setScoreboard(manager.getNewScoreboard());
            }
        }, 120);
    }
}
