package com.minegusta.mgskills.skills.magic.spells;

import com.minegusta.mgskills.skills.magic.ISpell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class Repair implements ISpell {
	@Override
	public String getName() {
		return ChatColor.GRAY + "Repair";
	}

	@Override
	public int getMana() {
		return 30;
	}

	@Override
	public int getLevel() {
		return 48;
	}

	private static String[] info = new String[]{
			"Repair stuff and shit.",
			"Pretty neat."
	};

	@Override
	public String[] getInfo() {
		return info;
	}

	@Override
	public boolean cast(Player player) {
		return false;
	}

	@Override
	public boolean cast(Event event) {
		return false;
	}

	@Override
	public int getCooldown() {
		return 120;
	}

	@Override
	public int getExperience() {
		return 160;
	}
}
