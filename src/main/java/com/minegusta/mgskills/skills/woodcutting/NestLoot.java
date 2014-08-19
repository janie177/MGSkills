package com.minegusta.mgskills.skills.woodcutting;

import com.minegusta.mgskills.files.DefaultConfig;
import com.minegusta.mgskills.treasuremaps.TreasureMapItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum NestLoot {
    L1(new ItemStack(Material.APPLE, 5)),
    L2(new ItemStack(Material.POTATO_ITEM, 5)),
    L3(new ItemStack(Material.CARROT_ITEM, 5)),
    L4(new ItemStack(Material.BLAZE_ROD, 4)),
    L5(new ItemStack(Material.GREEN_RECORD)),
    L6(new ItemStack(Material.IRON_INGOT, 5)),
    L7(new ItemStack(Material.GOLD_INGOT, 5)),
    L8(new ItemStack(Material.QUARTZ, 10)),
    L9(TreasureMapItem.getNewTreasureMap(Bukkit.getWorld(DefaultConfig.getConfig().getStringList("enabled_worlds").get(0)))),
    L10(new ItemStack(Material.FEATHER, 15)),
    L11(new ItemStack(Material.RAW_FISH, 3)),
    L12(new ItemStack(Material.FLINT, 4)),
    L13(new ItemStack(Material.EGG, 14)),
    L14(new ItemStack(Material.NETHER_STAR)),
    L15(new ItemStack(Material.EMERALD, 3)),
    L16(new ItemStack(Material.DIAMOND, 2));


    private ItemStack loot;

    private NestLoot(ItemStack loot) {
        this.loot = loot;
    }

    public ItemStack get() {
        return loot;
    }
}

