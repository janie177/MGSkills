package com.minegusta.mgskills.skills.magic;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public interface ISpell {

	String getName();

	int getMana();

	int getLevel();

	String[] getInfo();

	boolean cast(Player player);

	boolean cast(Event event);

	int getCooldown();

	int getExperience();
}
