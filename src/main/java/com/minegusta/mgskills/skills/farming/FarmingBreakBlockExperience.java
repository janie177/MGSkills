package com.minegusta.mgskills.skills.farming;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Farming;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FarmingBreakBlockExperience implements IExp{

    private DetailedMPlayer mp;
    private Block b;
    private int experience = 0;
    private boolean isGrownPlant = false;
    private List<Material> hoes = Lists.newArrayList(Material.DIAMOND_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.GOLD_HOE, Material.WOOD_HOE);

    private boolean hasSilkTouch() {
        return !mp.getPlayer().getItemInHand().getType().equals(Material.AIR) && mp.getPlayer().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
    }

    //Apply

    private int getExp() {
        int exp;
        switch (b.getType()) {
            case MELON_BLOCK: {
                if (hasSilkTouch()) exp = 1;
                else exp = 20;
            }
            break;
            case PUMPKIN_STEM: {
                if (b.getData() == 7) exp = 40;
                else exp = 1;
            }
            break;
            case CROPS: {
                if (b.getData() == 7) exp = 10;
                else {
                    exp = 1;
                }
            }
            break;
            case MELON_STEM: {
                if (b.getData() == 7) exp = 40;
                else exp = 1;
            }
            break;
            case CARROT: {
                if (b.getData() == 7) exp = 10;
                else {
                    exp = 1;
                }
            }
            break;
            case POTATO: {
                if (b.getData() == 7) exp = 10;
                else {
                    exp = 1;
                }
            }
            break;
            case LONG_GRASS:
                exp = 2;
                break;
            case NETHER_STALK: {
                if (b.getData() == 3) exp = 10;
                else {
                    exp = 0;
                }
            }
            break;
            case NETHER_WARTS: {
                if (b.getData() == 3) exp = 10;
                else {
                    exp = 0;
                }
            }
            break;
            default:
                exp = 0;
                break;
        }
        experience = exp;
        return exp;
    }

    private boolean hasHoe() {
        return hoes.contains(mp.getPlayer().getItemInHand().getType());
    }

    private void replant() {
        if (mp.getFarmingLevel() >= 62 && hasHoe()) {
            boolean replant = false;

            switch (b.getType()) {
                case CARROT: {
                    if (b.getData() == 7) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case POTATO: {
                    if (b.getData() == 7) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case CROPS: {
                    if (b.getData() == 7) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case NETHER_WARTS: {
                    if (b.getData() == 3) {
                        isGrownPlant = true;
                        replant = true;
                    }
                }
                break;
                case NETHER_STALK: {
                    if (b.getData() == 3) {
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
                        b.setType(b.getType());
                    }
                }, 10);
            }
        }
    }

    private void appleBoost() {
        if (b.getType().equals(Material.LEAVES) || b.getType().equals(Material.LEAVES_2)) {
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
            for (ItemStack is : b.getDrops()) {
                b.getWorld().dropItemNaturally(b.getLocation(), is);
            }
        }

        if (mp.getFarmingLevel() * 2 > 100) {
            if (RandomNumber.get(100) <= (mp.getFarmingLevel() * 2) - 100) {
                for (ItemStack is : b.getDrops()) {
                    b.getWorld().dropItemNaturally(b.getLocation(), is);
                }
            }
        }
    }

    @Override
    public IExp build(Player p, Block b) {
        this.b = b;
        this.mp = TempData.pMap.get(p.getUniqueId());
        return this;
    }

    @Override
    public boolean check() {
        return getExp() != 0;
    }

    @Override
    public boolean apply() {
        if(check())
        {
            replant();
            appleBoost();
            lootBoost();
            mp.addFarming(experience);
            LevelUpListener.isLevelUp(new Farming(mp));
            return true;
        }
        return false;
    }
}
