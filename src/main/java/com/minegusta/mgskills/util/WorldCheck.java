package com.minegusta.mgskills.util;

import com.minegusta.mgskills.files.DefaultConfig;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class WorldCheck
{
    private FileConfiguration conf = DefaultConfig.getConfig();
    private String worldName;

    public WorldCheck(World world)
    {
        worldName = world.getName();
    }

    public boolean check()
    {
        return conf.getStringList("enabled_worlds").contains(worldName);
    }
}
