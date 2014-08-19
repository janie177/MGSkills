package com.minegusta.mgskills.skills.mining;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class RandomOreBoost
{

    private DetailedMPlayer mp;
    private int level;
    private Material m;

    public RandomOreBoost(BlockBreakEvent e)
    {
        if(e.isCancelled())return;
        this.m = e.getBlock().getType();
        if(!isStone())return;
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        Player p = e.getPlayer();
        this.level = mp.getMiningLevel();

        if(isLucky())
        {
            p.getWorld().dropItemNaturally(e.getBlock().getLocation(), getOre());
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 5, 5);
        }
    }

    //Checks

    private boolean isLucky()
    {
        return RandomNumber.get(10000) < (50 + (mp.getMiningLevel() * 5));
    }

    private boolean isStone() { return m.equals(Material.STONE);}

    //Apply

    private ItemStack getOre()
    {
        ItemStack is = new ItemStack(Material.COAL, 1);

        if(RandomNumber.get(4) < 4) return is;
        else
        {
            String chosen = "D" + Integer.toString(RandomNumber.get(OresAndLevels.values().length));
            if(OresAndLevels.valueOf(chosen).getLevel() > level)
            {
                mp.addMining(15);
                return is;
            }
            else
            {
                mp.addMining(OresAndLevels.valueOf(chosen).getExp());
                return OresAndLevels.valueOf(chosen).get();
            }
        }
    }

    private enum OresAndLevels
    {
        D1(new ItemStack(Material.COAL, 2), 1, 6),
        D2(new ItemStack(Material.QUARTZ, 3), 10, 25),
        D3(new ItemStack(Material.REDSTONE, 7), 30, 40),
        D4(new ItemStack(Material.GOLD_ORE, 1), 40, 30),
        D5(new ItemStack(Material.IRON_ORE, 1), 55, 30),
        D6(new ItemStack(Material.EMERALD, 1), 80, 70),
        D7(new ItemStack(Material.INK_SACK, 4, (short) 4), 75, 80),
        D8(new ItemStack(Material.DIAMOND, 1), 90, 90);

        private ItemStack is;
        private int level;
        private int exp;

        private OresAndLevels(ItemStack is, int level, int exp)
        {
            this.exp = exp;
            this.is = is;
            this.level = level;

        }

        public ItemStack get()
        {
            return is;
        }

        public int getLevel()
        {
            return level;
        }

        public int getExp()
        {
            return exp;
        }
    }
}