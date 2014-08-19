package com.minegusta.mgskills.skills.cooking;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class FoodBoost
{
    private DetailedMPlayer mp;

    public FoodBoost(PlayerItemConsumeEvent e)
    {
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        if(e.isCancelled())return;

        Player p = e.getPlayer();
        int newFoodLevel = p.getFoodLevel() + apply();
        if(newFoodLevel > 20) newFoodLevel = 20;
        p.setFoodLevel(newFoodLevel);
    }

    private int apply()
    {
        int level = mp.getCookingLevel();
        int amount = 0;

        if(level >= 100)
        {
            amount = 20;
        }
        else if(level >= 75)
        {
            amount = 8;
        }
        else if(level >= 50)
        {
            amount = 4;
        }
        else if(level >= 25)
        {
            amount = 1;
        }
        return amount;
    }
}
