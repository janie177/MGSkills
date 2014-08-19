package com.minegusta.mgskills.skills.fishing;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingExperience {
    private DetailedMPlayer mp;
    private int level;
    private int newExp;
    private int expGiven;
    private Entity fish;
    private PlayerFishEvent.State state;

    public FishingExperience(PlayerFishEvent e) {
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.fish = e.getCaught();
        this.state = e.getState();
        this.level = mp.getFishingLevel();
        if (e.isCancelled()) return;

        if (!isCaught()) return;

        expGiven = getExpGiven();
        giveExp();
    }


    //Setters

    private void giveExp() {
        mp.addFishing(expGiven);
        newExp = mp.getFishing();
    }

    //Checks

    private boolean isCaught() {
        return state.equals(PlayerFishEvent.State.CAUGHT_FISH);
    }

    private int getExpGiven() {
        return 0;
    }
}
