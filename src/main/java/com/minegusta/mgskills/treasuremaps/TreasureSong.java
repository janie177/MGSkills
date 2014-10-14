package com.minegusta.mgskills.treasuremaps;

import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TreasureSong {
    private static final Sound[] SONG = {
            Sound.NOTE_BASS,
            Sound.NOTE_STICKS,
            Sound.NOTE_BASS,
            Sound.NOTE_STICKS,
            Sound.NOTE_PIANO,
            Sound.NOTE_BASS,
            Sound.NOTE_PIANO,
            Sound.NOTE_BASS,
            Sound.NOTE_STICKS,
            Sound.NOTE_STICKS,
            Sound.NOTE_PLING,
            Sound.NOTE_STICKS,
            Sound.NOTE_BASS_DRUM,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS_DRUM,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS_GUITAR,
            Sound.NOTE_BASS_GUITAR,
            Sound.NOTE_PIANO,
            Sound.NOTE_PLING,
            Sound.NOTE_BASS,
            Sound.NOTE_STICKS,
            Sound.NOTE_BASS,
            Sound.NOTE_STICKS,
            Sound.NOTE_PIANO,
            Sound.NOTE_BASS,
            Sound.NOTE_PIANO,
            Sound.NOTE_BASS,
            Sound.NOTE_STICKS,
            Sound.NOTE_STICKS,
            Sound.NOTE_PLING,
            Sound.NOTE_STICKS,
            Sound.NOTE_BASS_DRUM,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS_DRUM,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS_GUITAR,
            Sound.NOTE_BASS_GUITAR,
            Sound.NOTE_PIANO,
            Sound.NOTE_PLING,
            Sound.NOTE_PLING
    };

    public static void playSong(final Player p) {
        for (int i = 0; i < 20 * 20 + 1; i++) {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run() {
                    if (k % 5 == 0) p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    if (k % 10 == 0) {
                        int count = k / 10;
                        p.playSound(p.getLocation(), SONG[count], 1, 1);
                    }
                }
            }, i);
        }
    }
}

