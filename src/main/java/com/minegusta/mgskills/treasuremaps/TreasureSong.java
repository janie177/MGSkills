package com.minegusta.mgskills.treasuremaps;

import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TreasureSong {
    private static final Sound[] SONG = {
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_SNARE,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_SNARE,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_SNARE,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_SNARE,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_SNARE,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_BASS,
            Sound.BLOCK_NOTE_SNARE,
            Sound.BLOCK_NOTE_PLING,
            Sound.BLOCK_NOTE_PLING
    };

    public static void playSong(final Player p) {
        for (int i = 0; i < 20 * 20 + 1; i++) {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run() {
                    if (k % 5 == 0) p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                    if (k % 10 == 0) {
                        int count = k / 10;
                        p.playSound(p.getLocation(), SONG[count], 1, 1);
                    }
                }
            }, i);
        }
    }
}

