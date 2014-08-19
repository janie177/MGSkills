package com.minegusta.mgskills.skills.farming;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Farming;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public class FarmingBreakBlockExperience {
    private DetailedMPlayer mp;
    private byte data;
    private Collection<ItemStack> drops;
    private Material m;
    private Block b;
    private int experience = 0;
    private boolean isGrownPlant = false;
    private ItemStack hand;
    private List<Material> hoes = Lists.newArrayList(Material.DIAMOND_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.GOLD_HOE, Material.WOOD_HOE);

    public FarmingBreakBlockExperience(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        this.m = e.getBlock().getType();
        this.data = e.getBlock().getData();
        this.hand = e.getPlayer().getItemInHand();
        if (getExp() == 0) return;
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());

        this.b = e.getBlock();
        this.drops = e.getBlock().getDrops();

        this.experience = getExp();
        replant();
        appleBoost();
        lootBoost();
        if (experience > 0) addExperience();
        LevelUpListener.isLevelUp(new Farming(mp));

    }

    private boolean hasSilkTouch() {
        return !mp.getPlayer().getItemInHand().getType().equals(Material.AIR) && mp.getPlayer().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
    }

    //Apply

    private int getExp() {
        int exp;
        switch (m) {
            case MELON_BLOCK: {
                if (hasSilkTouch()) exp = 1;
                else exp = 20;
            }
            break;
            case PUMPKIN_STEM: {
                if (data == 7) exp = 40;
                else exp = 1;
            }
            break;
            case CROPS: {
                if (data == 7) exp = 10;
                else {
                    exp = 1;
                }
            }
            break;
            case MELON_STEM: {
                if (data == 7) exp = 40;
                else exp = 1;
            }
            break;
            case CARROT: {
                if (data == 7) exp = 10;
                else {
                    exp = 1;
                }
            }
            break;
            case POTATO: {
                if (data == 7) exp = 10;
                else {
                    exp = 1;
                }
            }
            break;
            case LONG_GRASS:
                exp = 2;
                break;
            case NETHER_STALK: {
                if (data == 3) exp = 10;
                else {
                    exp = 0;
                }
            }
            break;
            case NETHER_WARTS: {
                if (data == 3) exp = 10;
                else {
                    exp = 0;
                }
            }
            break;
            default:
                exp = 0;
                break;
        }
        return exp;
    }

    private boolean hasHoe() {
        return hoes.contains(hand.getType());
    }

    private void replant() {
        if (mp.getFarmingLevel() >= 62 && hasHoe()) {
            boolean replant = false;

            switch (m) {
                case CARROT: {
                    if (data == 7) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case POTATO: {
                    if (data == 7) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case CROPS: {
                    if (data == 7) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case NETHER_WARTS: {
                    if (data == 3) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case NETHER_STALK: {
                    if (data == 3) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                default:
                    replant = false;
                    break;
            }

            if (replant) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                    @Override
                    public void run() {
                        b.setType(m);
                    }
                }, 10);
            }
        }
    }

    private void appleBoost() {
        if (m.equals(Material.LEAVES) || m.equals(Material.LEAVES_2)) {
            if (mp.getFarmingLevel() >= 44) {
                if (RandomNumber.get(100) < 5)
                    b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
            }

            if (mp.getFarmingLevel() == 100) {
                if (RandomNumber.get(400) == 1)
                    b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
            }
        }
    }

    private void lootBoost() {
        if (!isGrownPlant) return;
        if (RandomNumber.get(100) <= mp.getFarmingLevel() * 2) {
            experience = experience * 2;
            for (ItemStack is : drops) {
                b.getWorld().dropItemNaturally(b.getLocation(), is);
            }
        }

        if (mp.getFarmingLevel() * 2 > 100) {
            if (RandomNumber.get(100) <= (mp.getFarmingLevel() * 2) - 100) {
                for (ItemStack is : drops) {
                    b.getWorld().dropItemNaturally(b.getLocation(), is);
                }
            }
        }
    }

    private void addExperience() {
        mp.addFarming(experience);
    }

}
