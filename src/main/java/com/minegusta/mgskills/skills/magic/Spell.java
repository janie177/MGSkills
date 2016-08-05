package com.minegusta.mgskills.skills.magic;

import com.minegusta.mgskills.skills.magic.spells.Repair;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public enum Spell {

	REPAIR(new Repair());

	private ISpell spell;

	Spell(ISpell spell)
	{
		this.spell = spell;
	}

	public ISpell getSpell()
	{
		return spell;
	}

	public String getName()
	{
		return spell.getName();
	}

	public int getCooldown()
	{
		return spell.getCooldown();
	}

	public String[] getInfo()
	{
		return spell.getInfo();
	}

	public int getMana()
	{
		return spell.getMana();
	}

	public int getLevel()
	{
		return spell.getLevel();
	}

	public boolean cast(Player player)
	{
		return spell.cast(player);
	}

	public boolean cast(Event event)
	{
		return spell.cast(event);
	}

	public int getExperience()
	{
		return spell.getExperience();
	}
}
