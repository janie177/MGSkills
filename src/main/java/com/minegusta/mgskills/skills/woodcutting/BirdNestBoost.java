package com.minegusta.mgskills.skills.woodcutting;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.SendMessage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BirdNestBoost
{
    private Player p;
    private Block b;
    private Location l;

    public BirdNestBoost(Player p, Block b)
    {
        this.p = p;
        this.b = b;
        this.l = b.getLocation();
    }

    public void runNest()
    {
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
}
