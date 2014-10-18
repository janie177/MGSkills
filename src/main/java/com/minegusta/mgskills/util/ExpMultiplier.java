package com.minegusta.mgskills.util;

import com.minegusta.mgskills.Main;

public class ExpMultiplier {
    final static int expMultiplier = Main.PLUGIN.getConfig().getInt("exp_multiplier", 1);

    public static int get() {
        return expMultiplier;
    }
}
