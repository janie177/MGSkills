package com.minegusta.mgskills.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillInfoCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player)
        {
            ((Player) s).performCommand("skill info " + cmd.getName());
            return true;
        }
        s.sendMessage(ChatColor.YELLOW + "[Skills]" + ChatColor.RED + " This command is for players only.");
        return true;
    }
}
