package com.minegusta.mgskills.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendMessage {
    public static void send(Player p, String... message) {
        for (String s : message) {
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "S" + ChatColor.GOLD + "] " + ChatColor.YELLOW + s);
        }
    }
}
