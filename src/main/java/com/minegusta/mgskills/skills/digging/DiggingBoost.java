package com.minegusta.mgskills.skills.digging;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.treasuremaps.TreasureMapItem;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DiggingBoost implements IExp {
    private Block b;
    private DetailedMPlayer mp;
    private int chance;

    @Override
    public IExp build(Player p, Block b) {
        this.mp = TempData.getMPlayer(p);
        this.b = b;
        return this;
    }

    @Override
    public boolean check() {
        return isDig() && isShovel();
    }

    @Override
    public boolean apply() {
        if(check())
        {
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
            return true;
        }
        return false;
    }

    //Global check

    private boolean isDig() {
        List<Material> mList = Lists.newArrayList(Material.DIRT, Material.GRAVEL, Material.SOUL_SAND, Material.SAND, Material.GRASS, Material.SOIL);
        return mList.contains(b.getType());
    }

    private boolean isShovel() {
        if (mp.getPlayer().getItemInHand().getType().equals(Material.AIR)) return false;
        Material[] shovels = {Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE, Material.WOOD_SPADE, Material.STONE_SPADE};
        for (Material mat : shovels) {
            if (mat.equals(mp.getPlayer().getItemInHand().getType())) return true;
        }
        return false;
    }


    //Gravel boost --------------------------------------------------------------------------------
    private boolean isGravelBoost() {
        return mp.getLevel(Skill.DIGGING) > 14 && b.getType().equals(Material.GRAVEL) && RandomNumber.get(100) <= 20;
    }

    private void giveFlint() {
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.FLINT, 1));
    }


    //Tool boost ----------------------------------------------------------------------------------
    private boolean isToolBoost() {
        int chance = RandomNumber.get(600);
        if (mp.getLevel(Skill.DIGGING) > 29 && chance < 7) {
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
        return b.getType().equals(Material.GRASS) && mp.getLevel(Skill.DIGGING) > 69;
    }

    private void giveGrass() {
        b.setType(Material.AIR);
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GRASS, 1));
    }

    //Treasuremap boost ------------------------------------------------------------------------------

    private boolean isMap() {
        return mp.getLevel(Skill.DIGGING) > 59 && RandomNumber.get(100000) <= mp.getLevel(Skill.DIGGING);
    }

    private void giveMap() {
        b.getWorld().dropItemNaturally(b.getLocation(), TreasureMapItem.getNewTreasureMap(b.getWorld()));
        b.getWorld().playSound(b.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        mp.getPlayer().sendMessage(ChatColor.YELLOW + "[MG]" + ChatColor.LIGHT_PURPLE + " You found a treasure map!");
    }

    //Unbreaking shovel boost ------------------------------------------------------------------------

    private boolean isUnBreakingBoost() {
        return mp.getLevel(Skill.DIGGING) > 99;
    }

    private void repairShovel() {
        mp.getPlayer().getItemInHand().setDurability(new ItemStack(mp.getPlayer().getItemInHand().getType(), 1).getDurability());
    }

}
