package com.minegusta.mgskills.files;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OnReload {
    public static void reLoadToMap()
    {
        for (Player p : Bukkit.getOnlinePlayers()) {
            new LoadToMap(p);
        }
    }
}
