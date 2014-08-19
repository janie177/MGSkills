package com.minegusta.mgskills.skills.digging;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Digging;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class DiggingExperience implements IExp{


    private DetailedMPlayer mp;
    private Block b;

    //Apply

    private int getExp() {
        int exp;
        switch (b.getType()) {
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

    @Override
    public IExp build(Player p, Block b) {
        this.mp = TempData.getMPlayer(p);
        this.b = b;
        return this;
    }

    @Override
    public boolean check() {
        return getExp() != 0;
    }

    @Override
    public boolean apply() {
        if(check())
        {
            mp.addDigging(getExp() + mp.getDiggingLevel() / 5);
            LevelUpListener.isLevelUp(new Digging(mp));
            return true;
        }
        return false;
    }
}
