package com.minegusta.mgskills.skills.mining;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Mining;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class MiningExp implements IExp {
    private Material m;
    private DetailedMPlayer mp;

    @Override
    public IExp build(Player p, Block b) {
        this.m = b.getType();
        this.mp = TempData.pMap.get(p.getUniqueId());
        return this;
    }

    //Check

    private boolean hasSilkTouch() {
        return !mp.getPlayer().getItemInHand().getType().equals(Material.AIR) && mp.getPlayer().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
    }

    @Override
    public boolean check() {
        int exp = getExp();
        return exp != 0;
    }

    //Apply

    private int getExp() {
        int exp;
        switch (m) {
            case DIAMOND_ORE: {
                if (hasSilkTouch()) exp = 2;
                else exp = 160;
            }
            break;
            case LAPIS_ORE: {
                if (hasSilkTouch()) exp = 2;
                else exp = 120;
            }
            break;
            case IRON_ORE: {
                exp = 3;
            }
            break;
            case EMERALD_ORE: {
                if (hasSilkTouch()) exp = 2;
                else exp = 200;
            }
            break;
            case QUARTZ_ORE: {
                if (hasSilkTouch()) exp = 2;
                else exp = 25;
            }
            break;
            case GOLD_ORE:
                exp = 3;
                break;
            case STONE:
                exp = 2;
                break;
            case NETHERRACK:
                exp = 1;
                break;
            case COBBLESTONE:
                exp = 1;
                break;
            case REDSTONE_ORE: {
                if (hasSilkTouch()) exp = 2;
                else exp = 40;
            }
            break;
            case COAL_ORE: {
                if (hasSilkTouch()) exp = 2;
                else exp = 6;
            }
            break;
            default:
                exp = 0;
                break;
        }
        return exp;
    }

    @Override
    public boolean apply() {
        if (check()) {
            mp.addMining(getExp());
            LevelUpListener.isLevelUp(new Mining(mp));
            return true;
        }
        return false;
    }
}
