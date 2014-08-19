package com.minegusta.mgskills.skills.cooking;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.skills.Cooking;
import com.minegusta.mgskills.util.LevelUpListener;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import java.util.List;

public class CookingSmeltExperience {
    private Material cooked;
    private List<Player> players = Lists.newArrayList();
    private int amount;
    private int experience;

    public CookingSmeltExperience(FurnaceSmeltEvent e) {
        this.cooked = e.getResult().getType();
        this.amount = e.getResult().getAmount();
        if (!hasPlayerNear(e.getBlock())) return;
        if (e.isCancelled()) return;
        if (isFood()) {
            applyBoost();
            applyExperience();
            e.getResult().setAmount(amount);
        }

        for (Player p : players) {
            LevelUpListener.isLevelUp(new Cooking(TempData.pMap.get(p.getUniqueId())));
        }
    }

    //Private checks
    private boolean hasPlayerNear(Block b) {
        boolean end = false;
        Entity ent = b.getWorld().spawnEntity(b.getLocation(), EntityType.EXPERIENCE_ORB);
        for (Entity entity : ent.getNearbyEntities(15, 15, 15)) {
            if (entity instanceof Player) {
                players.add((Player) entity);
                end = true;
            }
        }
        ent.remove();
        return end;
    }

    private boolean isFood() {
        boolean end;

        switch (cooked) {
            case COOKED_BEEF: {
                experience = 14;
                end = true;
            }
            break;
            case COOKED_CHICKEN: {
                experience = 12;
                end = true;
            }
            break;
            case GRILLED_PORK: {
                experience = 14;
                end = true;
            }
            break;
            case COOKED_FISH: {
                experience = 16;
                end = true;
            }
            break;
            case BAKED_POTATO: {
                experience = 12;
                end = true;
            }
            break;
            default:
                end = false;
                break;
        }
        return end;
    }

    //Apply

    private void applyBoost() {
        int total = 100 * players.size();
        int chance = 0;

        for (Player p : players) {
            chance = chance + TempData.pMap.get(p.getUniqueId()).getCookingLevel();
        }

        if (RandomNumber.get(total) <= chance) {
            amount = amount * 2;
            experience = experience * 2;
        }
    }

    private void applyExperience() {
        for (Player p : players) {
            TempData.pMap.get(p.getUniqueId()).addCooking(experience);
        }
    }
}


