package com.minegusta.mgskills.util.checks;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Crops;

public class ExperienceUtil {
    /**
     * @param m
     * @param silkTouch
     * @return Returns the amount of experience earned when mining a block. SilkTouch parameter determines whether to apply a lot or a little exp.
     */
    public static int getMiningExp(Material m, boolean silkTouch) {
        int exp;
        switch (m) {
            case DIAMOND_ORE: {
                if (silkTouch) exp = 2;
                else exp = 350;
            }
            break;
            case LAPIS_ORE: {
                if (silkTouch) exp = 2;
                else exp = 230;
            }
            break;
            case IRON_ORE: {
                exp = 8;
            }
            break;
            case EMERALD_ORE: {
                if (silkTouch) exp = 2;
                else exp = 400;
            }
            break;
            case QUARTZ_ORE: {
                if (silkTouch) exp = 2;
                else exp = 40;
            }
            break;
            case GOLD_ORE:
                exp = 9;
                break;
            case STONE:
                exp = 3;
                break;
            case NETHERRACK:
                exp = 1;
                break;
            case COBBLESTONE:
                exp = 1;
                break;
            case REDSTONE_ORE: {
                if (silkTouch) exp = 2;
                else exp = 90;
            }
            break;
            case GLOWING_REDSTONE_ORE: {
                if (silkTouch) exp = 2;
                else exp = 90;
            }
            break;
            case COAL_ORE: {
                if (silkTouch) exp = 2;
                else exp = 50;
            }
            break;
            default:
                exp = 0;
                break;
        }
        return exp;
    }

    /**
     * @param b
     * @return Calculate how much experience a player should get from a certain block.
     */
    public static int getDiggingExp(Block b) {
        int exp;
        switch (b.getType()) {
            case SAND:
                exp = 12;
                break;
            case SOUL_SAND:
                exp = 14;
                break;
            case DIRT:
                exp = 12;
                break;
            case GRASS:
                exp = 13;
                break;
            case GRAVEL:
                exp = 16;
                break;
            case SOIL:
                exp = 16;
                break;
            default:
                exp = 0;
                break;
        }
        return exp;
    }


    /**
     * @param b
     * @param silkTouch
     * @return Get the farming experience for the given block.
     */
    public static int getFarmingExp(Block b, boolean silkTouch) {
        int exp;
        switch (b.getType()) {
            case MELON_BLOCK: {
                if (silkTouch) exp = 1;
                else exp = 60;
            }
            break;
            case PUMPKIN_STEM: {
                if (b.getData() == 7) exp = 150;
                else exp = 1;
            }
            break;
            case CROPS: {
                if (((Crops)b.getState().getData()).getState() == CropState.RIPE) exp = 30;
                else {
                    exp = 1;
                }
            }
            break;
            case MELON_STEM: {
                if (b.getData() == 7) exp = 150;
                else exp = 1;
            }
            break;
            case CARROT: {
                if (((Crops)b.getState().getData()).getState() == CropState.RIPE) exp = 25;
                else {
                    exp = 1;
                }
            }
            break;
            case POTATO: {
                if (((Crops)b.getState().getData()).getState() == CropState.RIPE) exp = 25;
                else {
                    exp = 1;
                }
            }
            break;
            case LONG_GRASS:
                exp = 2;
                break;
            case NETHER_STALK: {
                if (b.getData() == 3) exp = 25;
                else {
                    exp = 0;
                }
            }
            break;
            case NETHER_WARTS: {
                if (((Crops)b.getState().getData()).getState() == CropState.RIPE) exp = 25;
                else {
                    exp = 0;
                }
            }
            break;
            case BEETROOT_BLOCK: {
                if(((Crops)b.getState().getData()).getState() == CropState.RIPE) exp = 25;
                else exp = 0;
            }
            break;
            default:
                exp = 0;
                break;
        }
        return exp;
    }

}
