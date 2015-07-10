package com.minegusta.mgskills.util;

import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class DummyPotionShooter implements ProjectileSource{
    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
        return null;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
        return null;
    }
}
