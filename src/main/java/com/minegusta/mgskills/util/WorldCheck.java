package com.minegusta.mgskills.util;

import com.minegusta.mgskills.files.DefaultConfig;

import java.util.List;

public class WorldCheck
{
    private static List<String> list = DefaultConfig.getConfig().getStringList("enabled_worlds");

    public static boolean check(String worldName) {
        return list.contains(worldName);
    }
}
