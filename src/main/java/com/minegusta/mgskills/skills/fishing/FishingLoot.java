package com.minegusta.mgskills.skills.fishing;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.treasuremaps.TreasureMapItem;
import com.minegusta.mgskills.util.RandomNumber;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FishingLoot
{
    //Tier 1-6, 1 being least and 6 being most rare.
    final static int[][] loot = {{50,46,54,63,398,301,281,346},{302,303,304,305,292,297,262},{314,315,316,317,267,272,279,258},{261,268,306,307,308,309},{116,260,265,266,276,279,278,277,276},{266,265,264,56,57,322,276,310,311,312,313}};
    final static List<Integer> enchantable = Lists.newArrayList(302,303,304,305,292,301,346,314,315,316,317,267,272,279,261,258,310,311,312,313,276,279,278,277,306,307,308,309);
    //Enchantment[ID, LEVEL]
    final static int[][] enchantments = {{0,5},{1,4},{2,4},{3,4},{4,4},{5,3},{6,1},{7,3},{16,5},{17,5},{18,5},{18,5},{19,4},{20,3},{21,3},{32,7},{33,1},{34,5},{35,3},{48,5},{49,2},{50,1},{51,1},{61, 3},{62, 3}};

    public static ItemStack get(int level)
    {
        int tiers = level / 20; //Get the tiers unlocked every 20 levels
        int tier = RandomNumber.get(tiers + 1) - 1;
        int chosen = loot[tier][RandomNumber.get(loot[tier].length - 1)];

        ItemStack i = new ItemStack(Material.getMaterial(chosen), 1);

        //Enchanting
        if(RandomNumber.get(10) >= 8 && enchantable.contains(chosen))
        {
            try
            {
                int index = RandomNumber.get(enchantments.length - 1);
                int enchant = enchantments[index][0];
                int enchLevel = RandomNumber.get(enchantments[index][1]);
                i.addUnsafeEnchantment(Enchantment.getById(enchant), enchLevel);
            } catch (Exception ignored)
            {
                Bukkit.getLogger().info("!!ERROR!! Something went wrong while enchanting fishing stuff in skills!!");
            }
        }

        return i;
    }

    //Double catch
    public static void doubleCatch(ItemStack is)
    {
        if(is.getAmount() != 64)is.setAmount(is.getAmount() + 1);
    }


    //Treasure Maps
    public static void giveTreasureMap(Player p)
    {
        p.getWorld().dropItemNaturally(p.getLocation(), TreasureMapItem.getNewTreasureMap(p.getWorld(), p));
        p.getWorld().playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        p.sendMessage(ChatColor.YELLOW + "[MG]" + ChatColor.LIGHT_PURPLE + " You caught a treasure map!");
    }

}
