package com.minegusta.mgskills.treasuremaps;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class TreasureSong
{
    private final Player p;

    public TreasureSong(Player p)
    {
        this.p = p;
        playSong();
    }

    List<Sound> song = Lists.newArrayList(
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
    );

    private void playSong()
    {
        for (int i = 0; i < 20 * 20 + 1; i++)
        {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable()
            {
                @Override
                public void run()
                {
                    if(k % 5 == 0) p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    if(k % 10 == 0)
                    {
                        int count = k/10;
                        p.playSound(p.getLocation(), song.get(count), 1, 1);
                    }
                }
            }, i);

        }
    }
}
