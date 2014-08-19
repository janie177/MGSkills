package com.minegusta.mgskills.util;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class LevelUpSong
{
    private final Player p;

    public LevelUpSong(Player p)
    {
        this.p = p;
        playSong();
    }

    List<Sound> beat = Lists.newArrayList(
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS,
            Sound.NOTE_BASS,
            Sound.NOTE_SNARE_DRUM,
            null,
            Sound.NOTE_BASS_DRUM
    );

    List<Note> song = Lists.newArrayList(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.C),
            Note.sharp(0, Note.Tone.E),
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.C),
            Note.sharp(0, Note.Tone.E),
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.D),
            Note.sharp(0, Note.Tone.C),
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.D),
            null,
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.C),
            Note.sharp(0, Note.Tone.E),
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.C),
            Note.sharp(0, Note.Tone.G),
            Note.sharp(0, Note.Tone.C),
            null,
            Note.sharp(0, Note.Tone.E),
            Note.sharp(0, Note.Tone.C),
            Note.sharp(0, Note.Tone.E),
            Note.sharp(0, Note.Tone.D),
            Note.sharp(0, Note.Tone.C)
    );

    private void playSong()
    {
        for (int i = 0; i < 20 * 22 + 1; i++)
        {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable()
            {
                @Override
                public void run()
                {
                    if(k % 10 == 0)
                    {
                        int count = k/10;
                        //Beat
                        if(!(count >= beat.size() || beat.get(count) == null)) p.playSound(p.getLocation(), beat.get(count), 1, 1);
                        //melody
                        if(!(count >= song.size() || song.get(count) == null)) p.playNote(p.getLocation(), Instrument.PIANO, song.get(count));
                    }
                }
            }, i);

        }
    }
}