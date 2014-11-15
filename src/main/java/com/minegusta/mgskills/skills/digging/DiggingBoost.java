package com.minegusta.mgskills.skills.digging;

import com.minegusta.mgskills.treasuremaps.TreasureMapItem;
import com.minegusta.mgskills.util.RandomNumber;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DiggingBoost {
    private static int chance = 0;
    private static Player p;
    private static Block b;
    private static int level;


    public static boolean check(Player pl, int lv, Block bl) {
        p = pl;
        level = lv;
        b = bl;
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

    //Global check


    //Gravel boost --------------------------------------------------------------------------------
    private static boolean isGravelBoost() {
        return level > 14 && b.getType().equals(Material.GRAVEL) && RandomNumber.get(100) <= 20;
    }

    private static void giveFlint() {
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.FLINT, 1));
    }


    //Tool boost ----------------------------------------------------------------------------------
    private static boolean isToolBoost() {
        chance = RandomNumber.get(1250);
        if (level > 29 && chance < 7) {
            return true;
        }
        return false;
    }

    private static void giveTool() {
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

    private static boolean isGrassBoost() {
        return b.getType().equals(Material.GRASS) && level > 69;
    }

    private static void giveGrass() {
        b.setType(Material.AIR);
        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GRASS, 1));
    }

    //Treasuremap boost ------------------------------------------------------------------------------

    private static boolean isMap() {
        return level > 59 && RandomNumber.get(1200) < 2;
    }

    private static void giveMap() {
        b.getWorld().dropItemNaturally(b.getLocation(), TreasureMapItem.getNewTreasureMap(b.getWorld(), p));
        b.getWorld().playSound(b.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        p.sendMessage(ChatColor.YELLOW + "[MG]" + ChatColor.LIGHT_PURPLE + " You found a treasure map!");
    }

    //Unbreaking shovel boost ------------------------------------------------------------------------

    private static boolean isUnBreakingBoost() {
        return level > 99;
    }

    private static void repairShovel() {
        p.getItemInHand().setDurability(new ItemStack(p.getItemInHand().getType(), 1).getDurability());
    }

}
