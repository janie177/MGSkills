package com.minegusta.mgskills.skills.cooking;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import java.util.List;

public class CookingSmeltExperience {
    private Material cooked;
    private List<Player> players = Lists.newArrayList();
    private int amount;
    private int experience;

    public CookingSmeltExperience(FurnaceSmeltEvent e) {
        if (!hasPlayerNear(e.getBlock())) return;
        this.cooked = e.getResult().getType();
        if (isFood()) {
            this.amount = e.getResult().getAmount();
            applyBoost();
            applyExperience();
            e.getResult().setAmount(amount);
        }
    }

    //Private checks
    private boolean hasPlayerNear(Block b) {
        b.getWorld().getPlayers().stream().filter(p -> p.getLocation().distance(b.getLocation()) < 16).forEach(players::add);

        return !players.isEmpty();
    }

    private boolean isFood() {
        boolean end;

        switch (cooked) {
            case COOKED_BEEF: {
                experience = 35;
                end = true;
            }
            break;
            case COOKED_MUTTON: {
                experience = 30;
                end = true;
            }
            break;
            case COOKED_RABBIT: {
                experience = 30;
                end = true;
            }
            break;
            case COOKED_CHICKEN: {
                experience = 30;
                end = true;
            }
            break;
            case GRILLED_PORK: {
                experience = 35;
                end = true;
            }
            break;
            case COOKED_FISH: {
                experience = 55;
                end = true;
            }
            break;
            case BAKED_POTATO: {
                experience = 25;
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
            chance = chance + TempData.getMPlayer(p).getLevel(Skill.COOKING);
        }

        if (RandomNumber.get(total) <= chance) {
            amount = amount * 2;
            experience = experience * 2;
        }
    }

    private void applyExperience() {
        for (Player p : players) {
            TempData.getMPlayer(p).addExp(Skill.COOKING, experience);
        }
    }
}


