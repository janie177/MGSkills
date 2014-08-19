package com.minegusta.mgskills.commands;

import com.minegusta.mgskills.highscores.HighScoreManager;
import com.minegusta.mgskills.highscores.UpdateHighScoreBoard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScoreBoardCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args)
    {
        if(!(s instanceof Player) || !s.isOp())return false;
        Player p = (Player) s;

        int index = 1;
        if(args.length == 1 && args[0].equalsIgnoreCase("update"))
        {
            UpdateHighScoreBoard.updateHighScoreBoard();
            return true;
        }
        try
        {
            if(args.length == 0) index = 1;
            else index = Integer.parseInt(args[0]);
        } catch (Exception ignored)
        {
            p.sendMessage(ChatColor.RED + "Index not recognized. Use 1-6.");
        }
        if(index > 6 || index < 0) index = 1;
        if(p.getWorld().getBlockAt(p.getTargetBlock(null, 8).getLocation()) == null || !p.getTargetBlock(null, 8).getType().equals(Material.WALL_SIGN))
        {
            p.sendMessage(ChatColor.RED + "You might want to have your eyes checked.");
            p.sendMessage(ChatColor.RED + "That is clearly not a sign.");
            p.sendMessage(ChatColor.RED + "Make sure you are looking at the sign.");
            return true;
        }

        HighScoreManager m = new HighScoreManager();
        try {
            m.setLoc(index, p);
            p.sendMessage(ChatColor.GREEN + "Successfully set sign with index " + index + " out of 6.");
        } catch (Exception ignored)
        {
            p.sendMessage(ChatColor.RED + "Could not set board for some reason!");
        }
        return true;
    }
}
