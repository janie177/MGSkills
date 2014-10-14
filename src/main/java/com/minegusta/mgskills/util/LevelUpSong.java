package com.minegusta.mgskills.util;

import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LevelUpSong {
    private static final Sound[] BEAT = {
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
    };

    private static final Note[] NOTES = {
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
    };

    public static void playSong(final Player p) {
        for (int i = 0; i < 20 * 22 + 1; i++) {
            if (i % 10 == 0) {
                final int count = i / 10;
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                    @Override
                    public void run() {
                        //Beat
                        if (!(count >= BEAT.length || BEAT[count] == null))
                            p.playSound(p.getLocation(), BEAT[count], 1, 1);
                        //melody
                        if (!(count >= NOTES.length || NOTES[count] == null))
                            p.playNote(p.getLocation(), Instrument.PIANO, NOTES[count]);
                    }
                }, i);
            }
        }
    }
}
