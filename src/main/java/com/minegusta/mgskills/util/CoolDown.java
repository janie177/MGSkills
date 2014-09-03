package com.minegusta.mgskills.util;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CoolDown {
    public static void newCooldown(String uuid, ConcurrentMap<String, Long> map) {
        map.put(uuid, System.currentTimeMillis());
    }

    public static boolean cooledDown(String uuid, ConcurrentMap<String, Long> map, int coolDown) {
        return !map.containsKey(uuid) || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - map.get(uuid)) >= coolDown;
    }

    public static long getRemainingTime(String uuid, ConcurrentMap<String, Long> map) {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - map.get(uuid));
    }
}
