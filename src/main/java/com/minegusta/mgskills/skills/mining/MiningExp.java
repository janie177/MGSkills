package com.minegusta.mgskills.skills.mining;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Mining;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;

public class MiningExp
{
    private DetailedMPlayer mp;
    private Material m;

    public MiningExp(BlockBreakEvent e)
    {
        if(e.isCancelled())return;
        this.m = e.getBlock().getType();
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        int exp = getExp();
        if(exp == 0)return;


        mp.addMining(exp);
        LevelUpListener.isLevelUp(new Mining(mp));

    }
    private boolean hasSilkTouch()
    {
        return !mp.getPlayer().getItemInHand().getType().equals(Material.AIR) && mp.getPlayer().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
    }

    //Apply

    private int getExp()
    {
        int exp;
        switch(m)
        {
            case DIAMOND_ORE:
            {
                if(hasSilkTouch()) exp = 2;
                else exp = 160;
            }
                break;
            case LAPIS_ORE:
            {
                if(hasSilkTouch()) exp = 2;
                else exp = 120;
            }
            break;
            case IRON_ORE:
            {
                exp = 3;
            }
            break;
            case EMERALD_ORE:
            {
                if(hasSilkTouch()) exp = 2;
                else exp = 200;
            }
            break;
            case QUARTZ_ORE:
            {
                if(hasSilkTouch()) exp = 2;
                else exp = 25;
            }
                break;
            case GOLD_ORE: exp = 3;
                break;
            case STONE: exp = 2;
                break;
            case NETHERRACK: exp = 1;
                break;
            case COBBLESTONE: exp = 1;
                break;
            case REDSTONE_ORE:
            {
                if(hasSilkTouch()) exp = 2;
                else exp = 40;
            }
            break;
            case COAL_ORE:
            {
                if(hasSilkTouch())exp = 2;
                else exp = 6;
            }
            break;
            default: exp = 0;
            break;
        }
        return exp;
    }

}
