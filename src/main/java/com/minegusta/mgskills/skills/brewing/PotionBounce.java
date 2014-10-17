package com.minegusta.mgskills.skills.brewing;

import com.minegusta.mgskills.util.RandomNumber;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.util.Vector;

public class PotionBounce {
    public static void bounce(int level, ThrownPotion p) {
        int amount = (level * 3);
        for (int i = 0; i < 3; i++) {
            if (RandomNumber.get(100) < amount) {
                throwPotion(p);
            }
            amount = amount - 100;
            if (amount < 0) break;
        }
    }

    private static void throwPotion(ThrownPotion p) {
        ThrownPotion potion = (ThrownPotion) p.getWorld().spawnEntity(p.getLocation(), EntityType.SPLASH_POTION);

        potion.setItem(p.getItem());

        potion.setVelocity(new Vector(potion.getVelocity().getX(), 0.3 + ((double)RandomNumber.get(10))/ 100, potion.getVelocity().getZ()));
    }
}
