package com.minegusta.mgskills.skills.brewing;

import org.bukkit.inventory.ItemStack;

public class PotionExperience {
    public static int getExperience(ItemStack potion) {
        short id = potion.getDurability();
        int exp;

        switch (id) {
            case (short) 16:
                exp = 40;
                break;
            case (short) 32:
                exp = 40;
                break;
            case (short) 64:
                exp = 40;
                break;
            default:
                exp = 80;
        }

        return exp;

    }
}
