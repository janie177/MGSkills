package com.minegusta.mgskills.commands;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.highscores.HighScoreManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class HighScoreCommand implements CommandExecutor {
    private Player p;

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("highscore") && s instanceof Player) {
            HighScoreManager m = new HighScoreManager();
            List<String> hs = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                int k = i + 1;
                hs.add(" Number " + k + ": " + ChatColor.LIGHT_PURPLE + getName(m, k) + ChatColor.YELLOW + " Total Level: " + ChatColor.GREEN + m.getLevel(k));
            }
            this.p = (Player) s;
            sendList(hs);
        }
        return true;
    }

    private String getName(HighScoreManager m, int k) {
        String name = "xXObamaSwaggXx";
        if (!m.getUUID(k).equals("xXObamaSwaggXx"))
            name = Bukkit.getOfflinePlayer(UUID.fromString(m.getUUID(k))).getName();
        return name;
    }

    private void sendList(List<String> list) {
        p.sendMessage(ChatColor.DARK_RED + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.DARK_RED + "-=-=-=-=" + ChatColor.RED + "The MG HighScores" + ChatColor.DARK_RED + "=-=-=-=-");
        p.sendMessage(ChatColor.DARK_RED + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (String s : list) {
            p.sendMessage(ChatColor.YELLOW + s);
        }
    }
}
