package com.minegusta.mgskills.util;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.files.DetailedMPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class TempData {
    public static ConcurrentMap<UUID, DetailedMPlayer> pMap = Maps.newConcurrentMap();

    public static DetailedMPlayer getMPlayer(Player player) {
        return getMPlayer(player.getUniqueId());
    }

    public static DetailedMPlayer getMPlayer(UUID playerId) {
        return pMap.get(playerId);
    }

    public static ConcurrentMap<UUID, Long> wolfMap = Maps.newConcurrentMap();

    public static ConcurrentMap<UUID, Long> healMap = Maps.newConcurrentMap();

    public static ConcurrentMap<UUID, Long> chickenMap = Maps.newConcurrentMap();
}
