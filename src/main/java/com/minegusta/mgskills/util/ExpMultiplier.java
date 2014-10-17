package com.minegusta.mgskills.util;

import com.minegusta.mgskills.files.DefaultConfig;

public class ExpMultiplier {
    final static int expMultiplier = DefaultConfig.getConfig().getInt("exp_multiplier", 1);

    public static int get() {
        return expMultiplier;
    }
}
