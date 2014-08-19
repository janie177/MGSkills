package com.minegusta.mgskills.skills.cooking;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CakeEatBoost
{
    private DetailedMPlayer mp;
    private Action action;
    private Material m;

    public CakeEatBoost(PlayerInteractEvent e)
    {
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.action = e.getAction();
        if(e.isCancelled())return;

        if(!isBlock())return;

        this.m = e.getMaterial();

        if(!isCake())return;

        apply();
    }

    private boolean isBlock()
    {
        return action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK);
    }

    private boolean isCake()
    {
        return (m.equals(Material.CAKE_BLOCK) || m.equals(Material.CAKE)) && mp.getCookingLevel() > 67 && mp.getPlayer().getFoodLevel() < 20;
    }

    private void apply()
    {
        mp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 50, 0));
    }


}


