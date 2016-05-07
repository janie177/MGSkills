package com.minegusta.mgskills.treasuremaps;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.util.RandomNumber;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TreasureListener {
    private Player p;
    private Action click;
    private ItemStack is;

    public TreasureListener(PlayerInteractEvent e) {
        this.p = e.getPlayer();
        this.is = p.getInventory().getItemInMainHand();
        this.click = e.getAction();

        if (isRightClick() && isLocation()) {
            if (!hasInventory()) {
                p.sendMessage(ChatColor.RED + "You need at least 6 free inventory spaces!");
            } else {
                giveTreasure();
            }
        }
    }

    //Checks

    private boolean isRightClick() {
        return click.equals(Action.RIGHT_CLICK_AIR) || click.equals(Action.RIGHT_CLICK_BLOCK);
    }

    private boolean isLocation() {
        double x = Double.parseDouble(is.getItemMeta().getLore().get(1));
        double y = Double.parseDouble(is.getItemMeta().getLore().get(2));
        double z = Double.parseDouble(is.getItemMeta().getLore().get(3));
        double x2 = p.getLocation().getX();
        double y2 = p.getLocation().getY();
        double z2 = p.getLocation().getZ();

        return p.getWorld().getName().equalsIgnoreCase(is.getItemMeta().getLore().get(4)) && isNear(x, x2) && isNear(y, y2) && isNear(z, z2);
    }

    private boolean hasInventory() {
        int count = 0;
        for (ItemStack s : p.getInventory().getContents()) {
            if (s == null || s.getType().equals(Material.AIR)) {
                count++;
                if (count == 5) return true;
            }
        }
        return false;
    }

    private boolean isNear(double integer, double integer2) {
        return Math.abs(integer - integer2) < 2;
    }

    //Apply
    private void giveTreasure() {
        List<ItemStack> treasure = Lists.newArrayList();
        int amount = RandomNumber.get(5);
        if (amount < 2) amount = 2;

        for (int i = 0; i <= amount; i++) {
            treasure.add(Treasures.valueOf("T" + Integer.toString(RandomNumber.get(Treasures.values().length))).get());
        }

        //Remove TreasureMap
        p.setItemInHand(new ItemStack(Material.AIR));

        //Send message
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.YELLOW + "-=- " + ChatColor.LIGHT_PURPLE + " Congratulations! You found the treasure! " + ChatColor.YELLOW + "-=-");
        p.sendMessage(ChatColor.YELLOW + "-=- " + ChatColor.GRAY + "                      YARR HARR!!                  " + ChatColor.YELLOW + "-=-");
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        TreasureSong.playSong(p);

        //Add items

        for (ItemStack itemStack : treasure) {
            p.getInventory().addItem(itemStack);
        }
        p.updateInventory();
    }
}
