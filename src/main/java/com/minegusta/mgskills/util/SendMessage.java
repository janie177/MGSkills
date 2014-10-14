package com.minegusta.mgskills.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendMessage {
    public static void send(Player p, String... message) {
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-" + ChatColor.RED + "Skills Info" + ChatColor.YELLOW + "-=-=-=-");
        for (String s : message) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
    }
}
