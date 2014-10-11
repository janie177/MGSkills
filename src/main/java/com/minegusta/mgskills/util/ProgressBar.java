package com.minegusta.mgskills.util;


import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ProgressBar {

    public static void showBar(int exp, final Player p, String skill) {
        final ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        Objective experience = sb.registerNewObjective(p.getName(), "dummy");

        experience.setDisplaySlot(DisplaySlot.SIDEBAR);
        experience.setDisplayName(ChatColor.LIGHT_PURPLE + " ");
        Score level = experience.getScore(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE + skill + " + "));

        level.setScore(exp);

        p.setScoreboard(sb);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
            @Override
            public void run() {
                p.setScoreboard(manager.getNewScoreboard());
            }
        }, 30);
    }


}
