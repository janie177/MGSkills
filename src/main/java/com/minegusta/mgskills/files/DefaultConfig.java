package com.minegusta.mgskills.files;

import com.minegusta.mgskills.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class DefaultConfig
{
    public static void loadConfig()
    {
        Main.PLUGIN.saveDefaultConfig();
    }

    public static FileConfiguration getConfig()
    {
        return Main.PLUGIN.getConfig();
    }
}
