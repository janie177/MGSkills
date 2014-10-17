package com.minegusta.mgskills.files;

import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class OnReload {
    public static void reLoadToMap() {
        if (Main.PLUGIN.getConfig().getBoolean("convert_from_yaml", true)) {
            File folder = new File(Main.PLUGIN.getDataFolder().getPath() + "/players/");
            for (File file : folder.listFiles()) {
                if (file.getName().toLowerCase().contains(".yml")) {
                    new com.minegusta.mgskills.files.yaml.LoadToMap(UUID.fromString(file.getName().substring(0, file.getName().length() - 4)));
                }
            }
            Main.PLUGIN.getConfig().set("convert_from_yaml", false);
            Main.PLUGIN.saveConfig();
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                new LoadToMap(p);
            }
        }
    }
}
