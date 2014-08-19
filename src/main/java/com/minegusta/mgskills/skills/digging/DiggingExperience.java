package com.minegusta.mgskills.skills.digging;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Digging;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class DiggingExperience {

    private Material m;

    public DiggingExperience(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        this.m = e.getBlock().getType();
        if (getExp() == 0) return;
        DetailedMPlayer mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        int bonus = mp.getDiggingLevel() / 5;
        int exp = getExp() + bonus;
        mp.addDigging(exp);
        LevelUpListener.isLevelUp(new Digging(mp));
    }

    //Apply

    private int getExp() {
        int exp;
        switch (m) {
            case SAND:
                exp = 12;
                break;
            case SOUL_SAND:
                exp = 14;
                break;
            case DIRT:
                exp = 12;
                break;
            case GRASS:
                exp = 13;
                break;
            case GRAVEL:
                exp = 16;
                break;
            case SOIL:
                exp = 16;
                break;
            default:
                exp = 0;
                break;
        }
        return exp;
    }

}
