package com.minegusta.mgskills.skills.farming;

import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class FarmingInteractBlockExperience
{
    public static boolean makeTree(Block b) {
        String treetype;
        switch (b.getData()) {
            case 0:
                treetype = "TREE";
                break;
            case 1:
                treetype = "TALL_REDWOOD";
                break;
            case 2:
                treetype = "BIRCH";
                break;
            case 3:
                treetype = "SMALL_JUNGLE";
                break;
            case 4:
                treetype = "ACACIA";
                break;
            case 5:
                treetype = "DARK_OAK";
                break;
            default:
                treetype = "TREE";
                break;

        }
        if (b.getWorld().generateTree(b.getLocation(), TreeType.valueOf((treetype))))
        {
            b.setType(b.getLocation().getBlock().getRelative(BlockFace.UP).getType());
            return true;
        }
        return false;
    }
}
