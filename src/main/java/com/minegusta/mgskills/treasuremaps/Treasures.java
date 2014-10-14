package com.minegusta.mgskills.treasuremaps;


import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public enum Treasures {
    T1(new ItemStack(Material.DIAMOND_HELMET, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Pirate Hat");
            List<String> lore = Lists.newArrayList("Yarr Harr Fiddle Tee Dee,");
            meta.setLore(lore);
            setItemMeta(meta);

            addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
            addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        }
    }),
    T2(new ItemStack(Material.DIAMOND_LEGGINGS, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Pirates' Booty Holder");
            List<String> lore = Lists.newArrayList("Do What You Want Cause A Pirate Is Free,");
            meta.setLore(lore);
            setItemMeta(meta);

            addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        }
    }),
    T3(new ItemStack(Material.DIAMOND_CHESTPLATE, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Pirates' Treasure Chest");
            List<String> lore = Lists.newArrayList("Being A Pirate Is Alright To Be!");
            meta.setLore(lore);
            setItemMeta(meta);

            addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        }
    }),
    T4(new ItemStack(Material.DIAMOND_BOOTS, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Pirates' Booties");
            List<String> lore = Lists.newArrayList("You Are A Pirate!");
            meta.setLore(lore);
            setItemMeta(meta);

            addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        }
    }),
    T5(new ItemStack(Material.COOKIE, 15) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "Nom Nom Pirate Snacks");
            setItemMeta(meta);
        }
    }),
    T6(new ItemStack(Material.SPONGE, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "Spongebob Squarepants");
            setItemMeta(meta);
        }
    }),
    T7(new ItemStack(Material.BOOK_AND_QUILL, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "The Blind Pirate's Handbook");
            setItemMeta(meta);
        }
    }),
    T8(new ItemStack(Material.DIAMOND_BOOTS, 1) {
        {

        }
    }),
    T9(new ItemStack(Material.DIAMOND, 5) {
        {

        }
    }),
    T10(new ItemStack(Material.EMERALD, 8) {
        {

        }
    }),
    T11(new ItemStack(Material.IRON_ORE, 15) {
        {

        }
    }),
    T12(new ItemStack(Material.DIAMOND_LEGGINGS, 1) {
        {

        }
    }), T13(new ItemStack(Material.DIAMOND_CHESTPLATE, 1) {
        {

        }
    }),
    T14(new ItemStack(Material.DIAMOND_HELMET, 1) {
        {

        }
    }),
    T15(new ItemStack(Material.DIAMOND_SWORD, 1) {
        {
            ItemMeta meta = getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Sting");
            List<String> lore = Lists.newArrayList("Bilbo's Sword.", "Glows blue when danger is close.", "Not in MC though,", "I have no idea how to code that.");
            meta.setLore(lore);
            setItemMeta(meta);

            addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
            addUnsafeEnchantment(Enchantment.DURABILITY, 4);
            addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
            addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
            addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
            addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        }
    }),
    T16(new ItemStack(Material.GOLD_INGOT, 20) {
        {

        }
    }),
    T17(new ItemStack(Material.BOAT, 1) {
        {

        }
    }),
    T18(new ItemStack(Material.LEATHER_HELMET, 1) {
        {

        }
    }),
    T19(new ItemStack(Material.ENCHANTMENT_TABLE, 1) {
        {

        }
    }),
    T20(new ItemStack(Material.BEACON, 1) {
        {

        }
    }),
    T21(new ItemStack(Material.NETHER_STAR, 1) {
        {

        }
    }),
    T22(new ItemStack(Material.DIAMOND, 10) {
        {

        }
    }),
    T23(new ItemStack(Material.GOLD_ORE, 6) {
        {

        }
    }),
    T24(new ItemStack(Material.IRON_ORE, 12) {
        {

        }
    }),
    T25(new ItemStack(Material.BREAD, 5) {
        {

        }
    }),
    T26(new ItemStack(Material.DIAMOND_ORE, 8) {
        {

        }
    }),
    T27(new ItemStack(Material.DIAMOND_SWORD, 1) {
        {

        }
    }),
    T28(new ItemStack(Material.DIAMOND_BARDING, 1) {
        {

        }
    }),
    T29(new ItemStack(Material.IRON_BARDING, 1) {
        {

        }
    }),
    T30(new ItemStack(Material.DIAMOND, 12) {
        {

        }
    }),
    T31(new ItemStack(Material.DIAMOND, 6) {
        {

        }
    }),
    T32(new ItemStack(Material.GOLD_NUGGET, 50) {
        {

        }
    }),

    T33(new ItemStack(Material.EMERALD_ORE, 10) {
        {

        }
    }),
    T34(new ItemStack(Material.MOB_SPAWNER, 2) {
        {

        }
    }),
    T35(new ItemStack(Material.EMERALD_BLOCK, 2) {
        {

        }
    }),
    T36(new ItemStack(Material.QUARTZ, 35) {
        {

        }
    }),
    T37(new ItemStack(Material.LAVA_BUCKET, 3) {
        {

        }
    }),
    T38(new ItemStack(Material.POTION, 10) {
        {

        }
    }),
    T39(new ItemStack(Material.MYCEL, 10) {
        {

        }
    }),
    T40(new ItemStack(Material.QUARTZ_BLOCK, 20) {
        {

        }
    }),
    T41(new ItemStack(Material.EMERALD_BLOCK, 1) {
        {

        }
    }),
    T42(new ItemStack(Material.DRAGON_EGG, 1) {
        {

        }
    }),
    T43(new ItemStack(Material.IRON_AXE, 1) {
        {

        }
    }),
    T44(new ItemStack(Material.BOOKSHELF, 6) {
        {

        }
    }),
    T45(new ItemStack(Material.GREEN_RECORD, 1) {
        {

        }
    }),
    T46(new ItemStack(Material.EMERALD_BLOCK, 2) {
        {

        }
    }),
    T47(new ItemStack(Material.DIAMOND_BLOCK, 1) {
        {

        }
    }),
    T48(new ItemStack(Material.JUKEBOX, 1) {
        {

        }
    }),
    T49(new ItemStack(Material.NOTE_BLOCK, 6) {
        {

        }
    }),
    T50(new ItemStack(Material.BREWING_STAND_ITEM, 4) {
        {

        }
    });

    private ItemStack is;

    private Treasures(ItemStack is) {
        this.is = is;
    }

    public ItemStack get() {
        return is;
    }
}
