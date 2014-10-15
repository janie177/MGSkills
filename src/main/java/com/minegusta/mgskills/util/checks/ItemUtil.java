package com.minegusta.mgskills.util.checks;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemUtil
{

    //All the static components
    private static Material[] axes = {Material.IRON_AXE, Material.GOLD_AXE, Material.STONE_AXE, Material.WOOD_AXE, Material.DIAMOND_AXE};
    private static Material[] shovels = {Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE, Material.WOOD_SPADE, Material.STONE_SPADE};
    private static List<Material> hoes = Lists.newArrayList(Material.DIAMOND_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.GOLD_HOE, Material.WOOD_HOE);


    /**
     *
     * @param i
     * @return Return whether the item has the Silk Touch enchantment on it.
     */
    public static boolean hasSilkTouch(ItemStack i)
    {
        return !i.getType().equals(Material.AIR) && i.containsEnchantment(Enchantment.SILK_TOUCH);
    }


    /**
     *
     * @param is
     * @return Returns whether the given item stack is an axe.
     */
    public static boolean isAxe(ItemStack is)
    {
        if (is.getType().equals(Material.AIR)) return false;
        for (Material mat : axes) {
            if (mat.equals(is.getType())) return true;
        }
        return false;
    }

    /**
     *
     * @param is
     * @return Show whether the itemstack given is a shovel.
     */
    public static boolean isShovel(ItemStack is) {
        if (is.getType().equals(Material.AIR)) return false;
        for (Material mat : shovels) {
            if (mat.equals(is.getType())) return true;
        }
        return false;
    }


    /**
     *
     * @param i
     * @return Is the given item a hoe? I know you are.
     */
    public static boolean isHoe(ItemStack i) {
        return hoes.contains(i.getType());
    }
}
