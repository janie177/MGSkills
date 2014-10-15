package com.minegusta.mgskills.skills.brewing.custombrewing;


import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.concurrent.ConcurrentMap;

public class BrewingData {

    public static void addLocation(Location l, BrewingProcess b)
    {
        brewMap.put(l, b);
    }
    public static boolean hasBrewingLab(Location l)
    {
        return brewMap.containsKey(l);
    }
    public static void removeFromMap(Location l)
    {
        if(brewMap.containsKey(l))brewMap.remove(l);
    }
    public static BrewingProcess getBrew(Location l)
    {
        return brewMap.get(l);
    }

    public static ConcurrentMap<Location, BrewingProcess> brewMap = Maps.newConcurrentMap();
    public static ConcurrentMap<String, Block> brewInvMap = Maps.newConcurrentMap();


}
