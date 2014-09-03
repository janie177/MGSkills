package com.minegusta.mgskills.skills.farming;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Farming;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class FarmingInteractBlockExperience implements IExp
{
    private DetailedMPlayer mp;
    private Block b;

    private boolean isSappling() {
        return b.getType().equals(Material.SAPLING);
    }

    private boolean hasLevel() {
        return mp.getLevel(Skill.FARMING) > 74;
    }

    private void makeTree() {
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
        if (b.getWorld().generateTree(b.getLocation(), TreeType.valueOf((treetype)))) {
            b.setType(b.getLocation().getBlock().getRelative(BlockFace.UP).getType());
        }
    }

    @Override
    public IExp build(Player p, Block b) {
        this.mp = TempData.getMPlayer(p);
        this.b = b;
        return this;
    }

    @Override
    public boolean check() {
        return hasLevel() && isSappling();
    }

    @Override
    public boolean apply()
    {
        if(check())
        {
            makeTree();
            mp.addExp(Skill.FARMING, 48);
            LevelUpListener.isLevelUp(new Farming(mp));
            return true;
        }
        return false;
    }
}
