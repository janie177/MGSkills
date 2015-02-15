package com.minegusta.mgskills.skills.woodcutting;

import com.google.common.collect.Sets;
import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.CoolDown;
import com.minegusta.mgskills.util.SendMessage;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SuicideChicken {
    private DetailedMPlayer mp;
    private Player p;
    private Location l;
    private Material hand;
    private Action action;

    public SuicideChicken(PlayerInteractEvent e) {
        if (e.isCancelled()) return;
        this.p = e.getPlayer();
        this.mp = TempData.getMPlayer(p);
        this.hand = p.getItemInHand().getType();
        this.action = e.getAction();
        if (!isRightClick()) return;
        if (!setL()) return;
        run();
    }

    private boolean isRightClick() {
        return action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);
    }

    private void run() {
        if (isBlazeRod()) {
            if (!isLevel()) return;
            if (!cooledDown()) {
                SendMessage.send(p, "You have to wait another " + (20 - CoolDown.getRemainingTime(p.getUniqueId().toString(), TempData.chickenMap)) + " seconds.");
                return;
            }
            spawnChicken();
        }

    }

    private boolean setL() {
        try {
            this.l = p.getTargetBlock(Sets.newHashSet(Material.AIR), 30).getLocation();
        } catch (Exception ignored) {
        }
        return l != null;
    }

    private boolean isBlazeRod() {
        return hand.equals(Material.BLAZE_ROD);
    }

    private boolean isLevel() {
        return mp.getLevel(Skill.WOODCUTTING) > 71;
    }

    private boolean cooledDown() {
        return CoolDown.cooledDown(p.getUniqueId().toString(), TempData.chickenMap, 20);
    }

    private void spawnChicken()
    {
        Entity ent = p.getWorld().spawnEntity(l.add(0, 1, 0), EntityType.CHICKEN);
        final Chicken c = (Chicken) ent;
        CoolDown.newCooldown(p.getUniqueId().toString(), TempData.chickenMap);
        c.setCustomNameVisible(true);
        mp.addExp(Skill.WOODCUTTING, 30);
        boomTask(c);
    }

    private void boomTask(final Chicken chicken) {
        for (int i = 0; i < 20 * 4 + 1; i++) {
            final int k = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                @Override
                public void run() {
                    if (chicken.isDead()) return;
                    if (k % 20 == 0) chicken.setCustomName(ChatColor.DARK_RED + Integer.toString(4 - k / 20));
                    if (k == 4 * 20) {
                        p.getWorld().createExplosion(chicken.getLocation().getX(), chicken.getLocation().getY(), chicken.getLocation().getZ(), 4, false, false);
                        chicken.remove();
                    }
                }
            }, i);
        }
    }
}
