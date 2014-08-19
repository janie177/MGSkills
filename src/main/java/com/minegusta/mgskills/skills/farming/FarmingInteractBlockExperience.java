package com.minegusta.mgskills.skills.farming;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Farming;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FarmingInteractBlockExperience
{
    private DetailedMPlayer mp;
    private Block block;
    private Action action;

    public FarmingInteractBlockExperience(PlayerInteractEvent e)
    {
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.action = e.getAction();

        if(!getBlock())return;
        block = e.getClickedBlock();

        if(!e.isCancelled() && hasLevel() && isSappling())
        {
            makeTree();
            mp.addFarming(48);
            LevelUpListener.isLevelUp(new Farming(mp));
        }
    }

    private boolean getBlock()
    {
        return action.equals(Action.RIGHT_CLICK_BLOCK);
    }

    private boolean isSappling()
    {
        return block.getType().equals(Material.SAPLING);
    }

    private boolean hasLevel()
    {
        return mp.getFarmingLevel() > 74;
    }

    private void makeTree()
    {
        String treetype;
        switch (block.getData())
        {
            case 0: treetype = "TREE";
                break;
            case 1: treetype = "TALL_REDWOOD";
                break;
            case 2: treetype = "BIRCH";
                break;
            case 3: treetype = "SMALL_JUNGLE";
                break;
            case 4: treetype = "ACACIA";
                break;
            case 5: treetype = "DARK_OAK";
                break;
            default: treetype = "TREE";
                break;

        }
        if(block.getWorld().generateTree(block.getLocation(), TreeType.valueOf((treetype))))
        {
            block.setType(block.getLocation().getBlock().getRelative(BlockFace.UP).getType());
        }
    }
}
