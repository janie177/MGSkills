package com.minegusta.mgskills.skills.mining;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InfiniteTorchBoost
{
    private Material torch;
    private DetailedMPlayer mp;
    private BlockBreakEvent be;
    private Inventory i;

    public InfiniteTorchBoost(BlockBreakEvent e)
    {
        if(e.isCancelled())return;
        this.torch = e.getBlock().getType();
        if(!isTorch())return;
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.be = e;
        this.i = e.getPlayer().getInventory();

        if(islevel())stopDrop();
    }

    public InfiniteTorchBoost(BlockPlaceEvent e)
    {
        this.torch = e.getBlock().getType();
        if(!isTorch())return;
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.i = e.getPlayer().getInventory();

        if(islevel()) infiniteTorches();
    }

    private boolean islevel()
    {
        return mp.getMiningLevel() > 99;
    }

    private boolean isTorch() { return torch.equals(Material.TORCH);}

    private void stopDrop()
    {
        if(i.contains(Material.TORCH))be.getBlock().setType(Material.AIR);
    }

    private void infiniteTorches()
    {
        if(!i.containsAtLeast(new ItemStack(Material.TORCH), 2)) i.addItem(new ItemStack(Material.TORCH, 1));
    }
}
