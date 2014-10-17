package com.minegusta.mgskills.skills.fishing;

import org.bukkit.inventory.ItemStack;

public class FishingExp
{

    public static int getExp(ItemStack is)
    {
        int exp = 0;
        switch (is.getDurability())
        {
            case 0:
            {
                exp = 100;
            }
            break;
            case 1:
            {
                exp = 130;
            }
            break;
            case 2:
            {   
                exp = 150;
            }
            break;
            case 3:
            {
                exp = 180;
            }
            break;
            default:
            {
                exp = 90;
            }
            break;
        }
        return exp;
    }

}
