package com.minegusta.mgskills.util;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.files.DetailedMPlayer;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class TempData
{
    public static ConcurrentMap<UUID, DetailedMPlayer> pMap = Maps.newConcurrentMap();

    public static ConcurrentMap<UUID, Long> wolfMap = Maps.newConcurrentMap();

    public static ConcurrentMap<UUID, Long> healMap = Maps.newConcurrentMap();

    public static ConcurrentMap<UUID, Long> chickenMap = Maps.newConcurrentMap();
}
