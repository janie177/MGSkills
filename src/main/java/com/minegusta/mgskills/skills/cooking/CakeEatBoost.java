package com.minegusta.mgskills.skills.cooking;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CakeEatBoost {
    private DetailedMPlayer mp;
    private Action action;

    public CakeEatBoost(PlayerInteractEvent e) {
        this.mp = TempData.getMPlayer(e.getPlayer().getUniqueId().toString());
        this.action = e.getAction();
        if (e.isCancelled()) return;

        if (!isBlock()) return;

        apply();
    }

    private boolean isBlock() {
        return action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK);
    }

    private void apply() {
        mp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 50, 0));
    }


}


