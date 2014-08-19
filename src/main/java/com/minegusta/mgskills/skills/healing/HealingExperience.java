package com.minegusta.mgskills.skills.healing;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.CoolDown;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HealingExperience {

    private DetailedMPlayer mp;
    private Player healer;
    private Entity healed;
    private int coolDownTime;
    private int healAmount;
    private ItemStack hand;

    public HealingExperience(PlayerInteractEntityEvent e) {
        this.mp = TempData.pMap.get(e.getPlayer().getUniqueId());
        this.healer = e.getPlayer();
        this.healed = e.getRightClicked();
        this.hand = healer.getItemInHand();
        if (e.isCancelled()) return;


    }

    private boolean isHealPlayer() {
        return healed instanceof Player;
    }

    private boolean isHealItem() {
        return hand.getType().equals(Material.PAPER);
    }

    private boolean isCooledDown() {
        return CoolDown.cooledDown(healer.getUniqueId(), TempData.healMap, coolDownTime);
    }

    private void getHealAmount() {

    }

    private void applyHeal() {

    }
}
