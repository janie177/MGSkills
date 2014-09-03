package com.minegusta.mgskills.skills.woodcutting;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.struct.IExp;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.SendMessage;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WoodCuttingBoost implements IExp {
    private DetailedMPlayer mp;
    private ItemStack is;
    private Material m;
    private int level;
    private Player p;
    private Location l;
    private Block b;

    @Override
    public IExp build(Player p, Block b) {
        this.m = b.getType();
        this.mp = TempData.getMPlayer(p);
        this.p = p;
        this.is = p.getItemInHand();
        this.level = mp.getLevel(Skill.WOODCUTTING);
        this.b = b;
        this.l = b.getLocation().add(0.5, 0.5, 0.5);
        return this;
    }

    //Checks
    private boolean isLevel20() {
        return level > 19;
    }

    private boolean hasAxe() {
        Material[] axes = {Material.IRON_AXE, Material.GOLD_AXE, Material.STONE_AXE, Material.WOOD_AXE, Material.DIAMOND_AXE};
        if (is.getType().equals(Material.AIR)) return false;
        for (Material mat : axes) {
            if (mat.equals(is.getType())) return true;
        }
        return false;
    }

    private boolean isBirdNest() {
        return level > 44 && RandomNumber.get(300) == 1;
    }

    private boolean isLog() {
        return m.equals(Material.LOG) || m.equals(Material.LOG_2);
    }

    @Override
    public boolean check() {
        return isLog() && isLevel20() && hasAxe();
    }

    //Apply
    private void giveBoost() {
        int amplifier = level / 20;
        for (PotionEffect pe : p.getActivePotionEffects()) {
            if (pe.getType().equals(PotionEffectType.FAST_DIGGING) && pe.getAmplifier() <= amplifier - 1) {
                p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            }
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30, (amplifier - 1)));
    }

    private void runNest() {
        Material type = b.getRelative(BlockFace.DOWN).getType();
        if (type.equals(Material.LOG) || type.equals(Material.AIR) || type.equals(Material.LOG_2) || type.equals(Material.LEAVES) || type.equals(Material.LEAVES_2) || type.equals(Material.DIRT) || type.equals(Material.GRASS))
            b.getRelative(BlockFace.DOWN).setType(Material.HAY_BLOCK);
        sendMessage();
        spawnChickens();
        giveItems();
        eggs();
        playSound();
        b.setType(Material.AIR);
    }

    private void sendMessage() {
        new SendMessage(p, Lists.newArrayList("You hit a bird's nest!", "It looks like the birds collected some items.."));
    }

    private void spawnChickens() {
        for (int i = 0; i < 3; i++) {
            Chicken chicken = (Chicken) p.getWorld().spawnEntity(l, EntityType.CHICKEN);
            chicken.setBaby();
        }
    }

    private void giveItems() {
        p.getWorld().dropItemNaturally(l, NestLoot.valueOf("L" + Integer.toString(RandomNumber.get(NestLoot.values().length))).get());
    }

    private void eggs() {
        p.getWorld().dropItemNaturally(l, new ItemStack(Material.EGG, 2));
        p.getWorld().dropItemNaturally(l, new ItemStack(Material.FEATHER, 3));
    }

    private void playSound() {
        for (int i = 0; i < 20 * 4 + 1; i++) {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run() {
                    if (k % 5 == 0) {
                        p.getWorld().spigot().playEffect(l, Effect.CRIT, 0, 0, 0, 0, 0, 1, 20, 15);
                        if (k % 10 == 0) p.getWorld().playSound(l, Sound.CHICKEN_HURT, 1, 1);
                    }
                }
            }, i);
        }
    }

    @Override
    public boolean apply() {
        if (check()) {
            if (isBirdNest()) {
                runNest();
                mp.addExp(Skill.WOODCUTTING, 100);
            }
            giveBoost();
            return true;
        }
        return false;
    }
}
