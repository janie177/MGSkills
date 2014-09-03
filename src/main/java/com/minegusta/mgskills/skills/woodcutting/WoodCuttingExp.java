package com.minegusta.mgskills.skills.woodcutting;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Woodcutting;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WoodCuttingExp implements IExp {
    private Material m;
    private DetailedMPlayer mp;

    @Override
    public WoodCuttingExp build(Player p, Block b) {
        this.m = b.getType();
        this.mp = TempData.getMPlayer(p);
        return this;
    }

    //Checks

    private boolean isLog() {
        return m.equals(Material.LOG) || m.equals(Material.LOG_2);
    }

    @Override
    public boolean check() {
        return isLog();
    }

    //Apply
    private void giveExp() {
        mp.addExp(Skill.WOODCUTTING, 25);
    }

    @Override
    public boolean apply() {
        if (check()) {
            giveExp();
            LevelUpListener.isLevelUp(new Woodcutting(mp));
            return true;
        }
        return false;
    }
}
