package com.minegusta.mgskills.skills.mining;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InfiniteTorchPlaceBoost implements IExp
{
    private Block b;
    private DetailedMPlayer mp;
    private Inventory i;


    private boolean islevel() {
        return mp.getMiningLevel() > 99;
    }

    private boolean isTorch() {
        return b.getType().equals(Material.TORCH);
    }

    private void infiniteTorches()
    {
        if (!i.containsAtLeast(new ItemStack(Material.TORCH), 2)) i.addItem(new ItemStack(Material.TORCH, 1));
    }

    @Override
    public IExp build(Player p, Block b) {
        this.mp = TempData.getMPlayer(p);
        this.b = b;
        this.i = p.getInventory();
        return this;
    }

    @Override
    public boolean check() {
        return isTorch() && islevel();
    }

    @Override
    public boolean apply() {
        if(check())
        {
            infiniteTorches();
            return true;
        }
        return false;
    }
}
