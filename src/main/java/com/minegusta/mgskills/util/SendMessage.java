package com.minegusta.mgskills.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class SendMessage {
    private Player p;
    private List<String> message;

    public SendMessage(Player p, List<String> message) {
        this.message = message;
        this.p = p;

        send();
    }

    private void send() {
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-" + ChatColor.RED + "Skills Info" + ChatColor.YELLOW + "-=-=-=-");
        for (String s : message) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
    }
}
