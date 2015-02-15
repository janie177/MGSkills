package com.minegusta.mgskills.skills.fishing;

import org.bukkit.inventory.ItemStack;

public class FishingExp {

    public static int getExp(ItemStack is) {
        int exp = 0;
        switch (is.getDurability()) {
            case 0: {
                exp = 280;
            }
            break;
            case 1: {
                exp = 300;
            }
            break;
            case 2: {
                exp = 370;
            }
            break;
            case 3: {
                exp = 450;
            }
            break;
            default: {
                exp = 280;
            }
            break;
        }
        return exp;
    }

}
