package com.minegusta.mgskills.util;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
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
    private Player p;
    private String skill;
    private int level;

    public LevelUp(Player p, String skill, int level) {
        this.level = level + 1;
        this.skill = skill;
        this.p = p;

        publicAnounce();
        fireWorks();
        playSong();
        sendMessage();
    }

    private void publicAnounce() {
        if (level == 100) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "-=-=-=-" + ChatColor.RED + "Skills Anouncement" + ChatColor.YELLOW + "-=-=-=-");
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + p.getName() + ChatColor.LIGHT_PURPLE + " just reached level 100 in " + skill + ChatColor.LIGHT_PURPLE + ".");
        }
    }

    private void fireWorks() {
        for (int i = 0; i < 20 * 5 + 1; i++) {
            final int k = i;

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run() {
                    if (k % 5 == 0) {
                        Entity e = p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
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

    private void playSong() {
        new LevelUpSong(p);
    }

    private void sendMessage() {
        new SendMessage(p, Lists.newArrayList("Congratulations! You leveled up in " + ChatColor.YELLOW + skill + ChatColor.LIGHT_PURPLE + "!", "You are now level " + ChatColor.YELLOW + level + ChatColor.LIGHT_PURPLE + "."));
    }
}
