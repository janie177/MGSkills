package com.minegusta.mgskills.skills.fishing;

import org.bukkit.inventory.ItemStack;

public class FishingExp {

    public static int getExp(ItemStack is) {
        int exp = 0;
        switch (is.getDurability()) {
            case 0: {
                exp = 250;
            }
            break;
            case 1: {
                exp = 280;
            }
            break;
            case 2: {
                exp = 340;
            }
            break;
            case 3: {
                exp = 400;
            }
            break;
            default: {
                exp = 250;
            }
            break;
        }
        return exp;
    }

}
