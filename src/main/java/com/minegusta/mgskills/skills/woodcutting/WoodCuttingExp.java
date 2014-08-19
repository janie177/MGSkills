package com.minegusta.mgskills.skills.woodcutting;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Woodcutting;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.ProgressBar;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class WoodCuttingExp
{
    private DetailedMPlayer mp;
    private Material m;

    public WoodCuttingExp(BlockBreakEvent e)
    {
        if(e.isCancelled())return;
        this.m = e.getBlock().getType();
        if(!isLog())return;
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());


        giveExp();
        LevelUpListener.isLevelUp(new Woodcutting(mp));
    }

    //Checks

    private boolean isLog()
    {
        return m.equals(Material.LOG) || m.equals(Material.LOG_2);
    }

    //Apply
    private void giveExp()
    {
        mp.addWoodcutting(25);
    }
}
