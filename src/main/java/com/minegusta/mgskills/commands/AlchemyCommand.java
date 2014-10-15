package com.minegusta.mgskills.commands;

import com.minegusta.mgskills.skills.brewing.custombrewing.BrewingRecipeBook;
import com.minegusta.mgskills.util.WorldCheck;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlchemyCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s instanceof Player)
        {
            if(WorldCheck.check(((Player) s).getWorld().getName()))BrewingRecipeBook.openHelp((Player)s);
            else s.sendMessage(ChatColor.YELLOW + "[Skills]" + ChatColor.RED + " You are not in a skills world.");

            return true;
        }
        s.sendMessage(ChatColor.YELLOW + "[Skills]" + ChatColor.RED + " This command is for players only.");
        return true;
    }
}
