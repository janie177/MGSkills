package com.minegusta.mgskills.util;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.files.DetailedMPlayer;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class TempData {
    public static ConcurrentMap<String, DetailedMPlayer> pMap = Maps.newConcurrentMap();

    public static DetailedMPlayer getMPlayer(Player player) {
        return pMap.get(player.getUniqueId().toString());
    }

    public static DetailedMPlayer getMPlayer(String uuid) {
        return pMap.get(uuid);
    }

    public static void addMPlayer(String uuid, DetailedMPlayer mp) {
        pMap.put(uuid, mp);
    }

    public static Set<String> getKeySet() {
        return pMap.keySet();
    }

    public static void removeMPlayer(String uuid) {
        pMap.remove(uuid);
    }

    public static boolean containsMPlayer(String uuid) {
        return pMap.containsKey(uuid);
    }

    public static ConcurrentMap<String, Long> wolfMap = Maps.newConcurrentMap();

    public static ConcurrentMap<String, Long> healMap = Maps.newConcurrentMap();

    public static ConcurrentMap<String, Long> healStormMap = Maps.newConcurrentMap();

    public static ConcurrentMap<String, Boolean> poisonMap = Maps.newConcurrentMap();

    public static ConcurrentMap<String, Long> chickenMap = Maps.newConcurrentMap();

}
