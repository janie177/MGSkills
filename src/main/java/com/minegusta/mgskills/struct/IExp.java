package com.minegusta.mgskills.struct;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface IExp {
    IExp build(Player p, Block b);

    boolean check();

    boolean apply();
}
