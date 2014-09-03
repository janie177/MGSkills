package com.minegusta.mgskills.highscores;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.UUID;

public class UpdateHighscores {
    private UUID uuid;
    private int all;
    HighScoreManager manager = new HighScoreManager();
    List<String> playerList = Lists.newArrayList();
    List<Integer> levelList = Lists.newArrayList();


    public UpdateHighscores(PlayerJoinEvent e) {
        this.uuid = e.getPlayer().getUniqueId();
        all = TempData.pMap.get(uuid).getAll();

        update();
    }

    public UpdateHighscores(Player p) {
        this.uuid = p.getUniqueId();
        all = TempData.pMap.get(uuid).getAll();

        update();
    }

    private void update() {
        if (all <= manager.getLevel(10)) return;

        for (int i = 0; i < 10; i++) {
            playerList.add(i, manager.getUUID(i + 1));
            levelList.add(i, manager.getLevel(i + 1));
        }
        int start = 10;
        int count = 1;
        if (playerList.contains(uuid.toString())) {
            for (String s : playerList) {
                if (s.equals(uuid.toString())) {
                    start = count;
                }
                count++;
            }
        }
        for (int i = start; i > 0; i--) {
            if (all > levelList.get(i - 1)) {
                manager.set(uuid.toString(), i, all);
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.LIGHT_PURPLE + "MG" + ChatColor.YELLOW + "] " + ChatColor.RED + Bukkit.getOfflinePlayer(uuid).getName() + ChatColor.YELLOW + " is now " + ChatColor.RED + "#" + i + ChatColor.YELLOW + " in the highscores!");
                if (i < 10 && !(playerList.get(i - 1).equals(uuid.toString()))) {
                    manager.set(playerList.get(i - 1), i + 1, levelList.get(i - 1));
                }
            } else {
                break;
            }
        }

    }
}
