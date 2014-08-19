package com.minegusta.mgskills.skills.digging;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.treasuremaps.TreasureMapItem;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DiggingBoost {
    private Material m;
    private int level;
    private Block b;
    private int chance;
    private ItemStack is;
    private Player p;

    public DiggingBoost(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        this.m = e.getBlock().getType();
        if (!isDig()) return;
        DetailedMPlayer mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.level = mp.getDiggingLevel();
        this.b = e.getBlock();
        this.is = e.getPlayer().getItemInHand();
        this.p = e.getPlayer();

        if (isShovel()) {
            if (isGravelBoost()) {
                giveFlint();
            }
            if (isToolBoost()) {
                giveTool();
            }
            if (isGrassBoost()) {
                giveGrass();
            }
            if (isUnBreakingBoost()) {
                repairShovel();
            }
            if (isMap()) {
                giveMap();
            }
        }
    }

    //Global check

    private boolean isDig() {
        List<Material> mList = Lists.newArrayList(Material.DIRT, Material.GRAVEL, Material.SOUL_SAND, Material.SAND, Material.GRASS, Material.SOIL);
        return mList.contains(m);
    }

    private boolean isShovel() {
        if (is.getType().equals(Material.AIR)) return false;
        Material[] shovels = {Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE, Material.WOOD_SPADE, Material.STONE_SPADE};
        for (Material mat : shovels) {
            if (mat.equals(is.getType())) return true;
        }
        return false;
    }


    //Gravel boost --------------------------------------------------------------------------------
    private boolean isGravelBoost() {
        return level > 14 && m.equals(Material.GRAVEL) && RandomNumber.get(100) <= 20;
    }

    private void giveFlint() {
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.FLINT, 1));
    }


    //Tool boost ----------------------------------------------------------------------------------
    private boolean isToolBoost() {
        int chance = RandomNumber.get(600);
        if (level > 29 && chance < 7) {
            this.chance = chance;
            return true;
        }
        return false;
    }

    private void giveTool() {
        Material material;
        switch (chance) {
            case 1:
                material = Material.IRON_AXE;
                break;
            case 2:
                material = Material.IRON_SPADE;
                break;
            case 3:
                material = Material.IRON_SWORD;
                break;
            case 4:
                material = Material.IRON_HOE;
                break;
            case 5:
                material = Material.IRON_PICKAXE;
                break;
            case 6:
                material = Material.SHEARS;
                break;
            default:
                material = Material.IRON_SPADE;
        }
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(material, 1));
    }

    //Grass boost ------------------------------------------------------------------------------------

    private boolean isGrassBoost() {
        return m.equals(Material.GRASS) && level > 69;
    }

    private void giveGrass() {
        b.setType(Material.AIR);
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GRASS, 1));
    }

    //Treasuremap boost ------------------------------------------------------------------------------

    private boolean isMap() {
        return level > 59 && RandomNumber.get(100000) <= level;
    }

    private void giveMap() {
        b.getWorld().dropItemNaturally(b.getLocation(), TreasureMapItem.getNewTreasureMap(b.getWorld()));
        b.getWorld().playSound(b.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        p.sendMessage(ChatColor.YELLOW + "[MG]" + ChatColor.LIGHT_PURPLE + " You found a treasure map!");
    }

    //Unbreaking shovel boost ------------------------------------------------------------------------

    private boolean isUnBreakingBoost() {
        return level > 99;
    }

    private void repairShovel() {
        is.setDurability(new ItemStack(is.getType(), 1).getDurability());
    }

}
