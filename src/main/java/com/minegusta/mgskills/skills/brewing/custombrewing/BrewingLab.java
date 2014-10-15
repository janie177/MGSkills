package com.minegusta.mgskills.skills.brewing.custombrewing;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BrewingLab {

    public static boolean isLab(Block b)
    {
        Block b2 = b.getWorld().getBlockAt(b.getX(), b.getY(), b.getZ());
        return b2.getType().equals(Material.CAULDRON) && b2.getRelative(BlockFace.DOWN).getType().equals(Material.FIRE);
    }
}
