package com.minegusta.mgskills.skills.farming;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.Farming;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class FarmingInteractEntityExperience {
    private DetailedMPlayer mp;
    private EntityType type;
    private int experience = 0;
    private Material hand;
    private Entity entity;
    private Player p;

    public FarmingInteractEntityExperience(PlayerInteractEntityEvent e) {
        this.mp = TempData.getMPlayer(p);
        this.type = e.getRightClicked().getType();
        this.hand = e.getPlayer().getItemInHand().getType();
        this.entity = e.getRightClicked();
        this.p = e.getPlayer();

        if (!e.isCancelled()) {
            if (isSheepShear()) {
                woolBonus();
                addExperience();
            }

            if (isSoup()) {
                soupBonus();
                addExperience();
            }

            if (isMilk()) {
                milkBonus();
                addExperience();
            }
        }
    }

    private void addExperience() {
        mp.addExp(Skill.FARMING, experience);
        LevelUpListener.isLevelUp(new Farming(mp));
    }

    private boolean isSheepShear() {
        return type.equals(EntityType.SHEEP) && hand.equals(Material.SHEARS);
    }

    private boolean isSoup() {
        return type.equals(EntityType.MUSHROOM_COW) && hand.equals(Material.BOWL);
    }

    private boolean isMilk() {
        return type.equals(EntityType.COW) && hand.equals(Material.BUCKET);
    }

    //apply

    private void woolBonus() {
        Sheep sheep = (Sheep) entity;
        if (sheep.isSheared()) return;
        sheep.getWorld().dropItemNaturally(sheep.getLocation(), new ItemStack(Material.WOOL, 2, sheep.getColor().getData()));
        experience = 16;
    }

    private void soupBonus() {
        p.setItemInHand(new ItemStack(Material.MUSHROOM_SOUP, 2));
        experience = 10;
    }

    private void milkBonus() {
        int amount = 0;
        int stacks;
        for (ItemStack is : p.getInventory().getContents()) {
            if (is != null && is.getType().equals(Material.MILK_BUCKET)) {
                amount = amount + is.getAmount();
            }
        }
        p.getInventory().remove(Material.MILK_BUCKET);

        stacks = amount / 64;
        stacks++;

        for (int i = 0; i < stacks; i++) {
            if (amount > 64) {
                p.getInventory().addItem(new ItemStack(Material.MILK_BUCKET, 64));
                amount = amount - 64;
            } else {
                if (amount < 1) break;
                p.getInventory().addItem(new ItemStack(Material.MILK_BUCKET, amount));
            }
        }

        experience = 8;
        p.updateInventory();
    }

}
