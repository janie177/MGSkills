package com.minegusta.mgskills.skills.brewing;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class PotionExperience {
    public static int getExperience(ItemStack potion) {
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        PotionType type = meta.getBasePotionData().getType();
        int exp;

        switch (type) {
            case  AWKWARD:
                exp = 150;
                break;
            case MUNDANE:
                exp = 150;
                break;
            case WATER:
                exp = 5;
                break;
            case THICK:
                exp =150;
                break;
            default:
                exp = 190;
        }

        try
        {
            if(meta.getBasePotionData().isExtended() || meta.getBasePotionData().isUpgraded())
            {
                exp = exp/2;
            }
        } catch (Exception ignored){}

        return exp;

    }
}
