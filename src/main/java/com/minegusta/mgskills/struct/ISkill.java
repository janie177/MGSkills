package com.minegusta.mgskills.struct;

import com.minegusta.mgskills.files.DetailedMPlayer;
import org.bukkit.entity.Player;

public interface ISkill {
    public String getName();

    public int getLevel();

    public int getExp();

    public void insertMPlayer(DetailedMPlayer mp);

    public String getSpecialBoost();

    public void levelUp();

    public Player getPlayer();
}
