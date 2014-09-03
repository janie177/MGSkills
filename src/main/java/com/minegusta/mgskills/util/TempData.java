package com.minegusta.mgskills.util;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.files.DetailedMPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class TempData {
    private static ConcurrentMap<String, DetailedMPlayer> pMap = Maps.newConcurrentMap();

    public static DetailedMPlayer getMPlayer(Player player) {
        return getMPlayer(player.getUniqueId().toString());
    }

    public static DetailedMPlayer getMPlayer(String playerId) {
        return pMap.get(playerId);
    }

    public static boolean containsMPlayer(String uuid)
    {
        return pMap.containsKey(uuid);
    }

    public static ConcurrentMap<String, Long> wolfMap = Maps.newConcurrentMap();

    public static ConcurrentMap<String, Long> healMap = Maps.newConcurrentMap();

    public static ConcurrentMap<String, Long> chickenMap = Maps.newConcurrentMap();
}
