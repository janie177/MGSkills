package com.minegusta.mgskills.files;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OnReload {
    public static void reLoadToMap() {
        if (DefaultConfig.getConfig().getBoolean("convert_from_yaml", true)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                new com.minegusta.mgskills.files.yaml.LoadToMap(p);
            }
            DefaultConfig.getConfig().set("convert_from_yaml", false);
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                new LoadToMap(p);
            }
        }
    }
}
