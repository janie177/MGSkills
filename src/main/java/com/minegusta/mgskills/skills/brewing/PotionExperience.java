package com.minegusta.mgskills.skills.brewing;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

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

        try
        {
            Potion pot = Potion.fromItemStack(potion);

            if(pot.hasExtendedDuration() || pot.getLevel() > 1)
            {
                exp = exp/2;
            }
        } catch (Exception ignored){}

        return exp;

    }
}
