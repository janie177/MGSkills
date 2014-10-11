package com.minegusta.mgskills.util.checks;


import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtil
{
    /**
     *
     * @param b
     * @return Returns whether the given block is a tree log.
     */
    public static boolean isLog(Block b) {
        return b.getType().equals(Material.LOG) || b.getType().equals(Material.LOG_2);
    }


    /**
     *
     * @param level
     * @param hasHoe
     * @param b
     */
    public static boolean tryReplant(int level, boolean hasHoe, final Block b)
    {
        boolean replant = false;
        switch (b.getType()) {
            case CARROT: {
                if (b.getData() == 7) {

                    replant = true;
                }
            }
            break;
            case POTATO:
            {
                if (b.getData() == 7) {

                    replant = true;
                }
            }
            break;
            case CROPS: {
                if (b.getData() == 7) {

                    replant = true;
                }
            }
            break;
            case NETHER_WARTS: {
                if (b.getData() == 3) {

                    replant = true;
                }
            }
            break;
            case NETHER_STALK: {
                if (b.getData() == 3) {

                    replant = true;
                }
            }
            break;
            default:
                replant = false;
                break;
        }

        if (replant && level > 61 && hasHoe) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run()
                {
                    b.setType(b.getType());
                }
            }, 10);

        }
        return replant;
    }

    /**
     *
     * @param b
     * @return is the given block leaves?
     */
    public static boolean isLeaves(Block b)
    {
        return b.getType().equals(Material.LEAVES) || b.getType().equals(Material.LEAVES_2);
    }
}
