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
                exp = 50;
            }
            break;
            case 1:
            {
                exp = 60;
            }
            break;
            case 2:
            {   
                exp = 70;
            }
            break;
            case 3:
            {
                exp = 80;
            }
            break;
            default:
            {
                exp = 50;
            }
            break;
        }
        return exp;
    }

}
