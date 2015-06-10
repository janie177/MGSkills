package com.minegusta.mgskills.skills.mining;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import java.util.List;

public class SmeltingExperience {
    private Material melt;
    private List<Player> players = Lists.newArrayList();
    private int amount;
    private int experience;

    public SmeltingExperience(FurnaceSmeltEvent e) {
        if (!hasPlayerNear(e.getBlock())) return;
        this.melt = e.getResult().getType();
        if (isOre()) {
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

    private boolean isOre() {
        boolean end;

        switch (melt) {
            case IRON_INGOT: {
                experience = 26;
                end = true;
            }
            break;
            case COAL: {
                experience = 16;
                end = true;
            }
            break;
            case GOLD_INGOT: {
                experience = 48;
                end = true;
            }
            break;
            case REDSTONE: {
                experience = 22;
                end = true;
            }
            break;
            case EMERALD: {
                experience = 260;
                end = true;
            }
            break;
            case DIAMOND: {
                experience = 200;
                end = true;
            }
            break;
            case INK_SACK: {
                experience = 40;
                end = true;
            }
            break;
            case QUARTZ: {
                experience = 30;
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
            chance = chance + TempData.getMPlayer(p).getLevel(Skill.MINING);
        }

        if (RandomNumber.get(total) <= chance)
        {
            amount = amount * 2;
            experience = experience * 2;
        }
    }

    private void applyExperience() {
        for (Player p : players) {
            TempData.getMPlayer(p).addExp(Skill.MINING, experience);
        }
    }
}
