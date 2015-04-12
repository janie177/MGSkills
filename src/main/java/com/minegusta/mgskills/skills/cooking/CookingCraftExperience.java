package com.minegusta.mgskills.skills.cooking;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CookingCraftExperience {
    private Material cooked;
    private int amount;
    private ItemStack is;
    private int experience;
    private int level;
    private int bonusLoot;
    private DetailedMPlayer mp;

    public CookingCraftExperience(CraftItemEvent e) {
        this.mp = TempData.getMPlayer(e.getWhoClicked().getUniqueId().toString());
        this.is = e.getRecipe().getResult();
        this.cooked = e.getRecipe().getResult().getType();
        this.amount = e.getCurrentItem().getAmount();
        this.level = mp.getLevel(Skill.COOKING);

        if (e.isCancelled()) return;

        if (isFood())
        {
            if(!(e.getCursor() == null || e.getCursor().getType() == Material.AIR))
            {
                e.getWhoClicked().sendMessage(ChatColor.RED + "You can only craft food with an empty hand!");
                e.setCancelled(true);
                return;
            }
            applyBoost();
            if (amount > 1)
                mp.getPlayer().getInventory().addItem(new ItemStack(e.getRecipe().getResult().getType(), bonusLoot));
            applyExp();
            mp.getPlayer().updateInventory();
        }
    }


    private boolean isFood() {
        boolean end;

        switch (cooked) {
            case CAKE: {
                end = true;
                experience = 150;
            }
            break;
            case CAKE_BLOCK: {
                end = true;
                experience = 150;
            }
            break;
            case COOKIE: {
                experience = 3;
                end = true;
            }
            break;
            case PUMPKIN_PIE: {
                experience = 50;
                end = true;
            }
            break;
            case GOLDEN_APPLE: {
                if (is.getDurability() == (short) 1) {
                    experience = 800;
                    end = true;
                } else {
                    experience = 200;
                    end = true;
                }
            }
            break;
            case GOLDEN_CARROT: {
                experience = 100;
                end = true;
            }
            break;
            case MUSHROOM_SOUP: {
                experience = 50;
                end = true;
            }
            break;
            case BREAD: {
                experience = 45;
                end = true;
            }
            break;
            default:
                end = false;
                break;
        }
        return end;
    }

    private void applyBoost() {
        for (int i = 0; i < amount; i++) {
            if (i > 63) break;
            if (RandomNumber.get(100) <= level) bonusLoot++;
        }
    }

    private void applyExp() {
        experience = (bonusLoot + amount) * experience;
        mp.addExp(Skill.COOKING, experience);
    }
}
