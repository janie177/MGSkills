package com.minegusta.mgskills.skills.farming;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;

public class FarmingInteractEntityExperience
{
    public static int woolBonus(Entity entity) {
        Sheep sheep = (Sheep) entity;
        if (sheep.isSheared()) return 0;
        sheep.getWorld().dropItemNaturally(sheep.getLocation(), new ItemStack(Material.WOOL, 2, sheep.getColor().getData()));
        return 16;
    }

    public static int soupBonus(Player p) {
        p.setItemInHand(new ItemStack(Material.MUSHROOM_SOUP, 2));
        return 10;
    }

    public static int milkBonus(Player p) {
        int amount = 0;
        int stacks;
        for (ItemStack is : p.getInventory().getContents()) {
            if (is != null && is.getType().equals(Material.MILK_BUCKET)) {
                amount = amount + is.getAmount();
            }
        }
        p.getInventory().remove(Material.MILK_BUCKET);

        stacks = amount / 64;
        stacks++;

        for (int i = 0; i < stacks; i++) {
            if (amount > 64) {
                p.getInventory().addItem(new ItemStack(Material.MILK_BUCKET, 64));
                amount = amount - 64;
            } else {
                if (amount < 1) break;
                p.getInventory().addItem(new ItemStack(Material.MILK_BUCKET, amount));
            }
        }
        p.updateInventory();
        return 8;
    }

}
