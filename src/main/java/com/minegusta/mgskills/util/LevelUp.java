package com.minegusta.mgskills.util;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.highscores.UpdateHighscores;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class LevelUp {
    public static void advanceLevel(Player p, String skill, int level) {
        publicAnounce(level, p, skill);
        fireWorks(p);
        playSong(p);
        sendMessage(p, skill, level);
        addLevel(p, skill, level);
        new UpdateHighscores(p);
    }

    private static void addLevel(Player p, String skill, int level) {
        TempData.getMPlayer(p).addLevel(Skill.valueOf(skill.toUpperCase()));
        ProgressBar.showBar(level, p, skill);
    }

    private static void publicAnounce(int level, Player p, String skill) {
        if (level % 20 == 0) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "-=-=-=-" + ChatColor.RED + "Skills Announcement" + ChatColor.YELLOW + "-=-=-=-");
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + p.getName() + ChatColor.LIGHT_PURPLE + " just reached level " + level + " in " + skill + ChatColor.LIGHT_PURPLE + ".");
        }
    }

    private static void fireWorks(Player p) {
        for (int i = 0; i < 20 * 5 + 1; i++) {
            final int k = i;
            final Player pl = p;

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run() {
                    if (k % 20 == 0) {
                        Entity e = pl.getWorld().spawnEntity(pl.getLocation(), EntityType.FIREWORK);
                        Firework fw = (Firework) e;
                        FireworkMeta meta = fw.getFireworkMeta();
                        meta.setPower(1);
                        FireworkEffect effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.AQUA).withColor(Color.BLUE).withColor(Color.RED).build();
                        meta.addEffect(effect);
                        fw.setFireworkMeta(meta);
                    }
                }
            }, k);
        }
    }

    private static void playSong(Player p) {
        LevelUpSong.playSong(p);
    }

    private static void sendMessage(Player p, String skill, int level) {
        SendMessage.send(p, "Congratulations! You leveled up in " + ChatColor.YELLOW + skill + ChatColor.LIGHT_PURPLE + "!", "You are now level " + ChatColor.YELLOW + level + ChatColor.LIGHT_PURPLE + ".");
    }
}
