package com.minegusta.mgskills.highscores;

import org.bukkit.*;
import org.bukkit.block.Sign;

import java.util.UUID;

public class UpdateHighScoreBoard
{
    private static HighScoreManager m = new HighScoreManager();

    public static boolean updateHighScoreBoard()
    {
        int playerCount = 1;
        int scoreCount = 1;

        World w = Bukkit.getWorld(m.getWorld());
        for(int i = 1; i < 7; i++)
        {
            if(m.getLoc(i) == null)return false;
            Location loc = new Location(w, m.getLoc(i).get(0), m.getLoc(i).get(1), m.getLoc(i).get(2));
            if(!(w.getBlockAt(loc) != null && w.getBlockAt(loc).getType().equals(Material.WALL_SIGN)))
            {
                return false;
            }
            else
            {
                Sign sign = (Sign) w.getBlockAt(loc).getState();
                for(int x = 0; x < 4; x++)
                {
                    if(playerCount > 10)
                    {
                        if(!(playerCount == 13))playerCount++;
                        else
                        {
                            if(scoreCount > 10)scoreCount++;
                            else
                            {
                                sign.setLine(x, ChatColor.AQUA + Integer.toString(m.getLevel(scoreCount)));
                                scoreCount++;
                                sign.update();
                            }
                        }
                    }
                    else
                    {
                        String name;
                        if(m.getUUID(playerCount).equals("xXObamaSwaggXx")) name = "xXObamaSwaggXx";
                        else name = Bukkit.getOfflinePlayer(UUID.fromString(m.getUUID(playerCount))).getName();
                        sign.setLine(x, name);
                        sign.update();
                        playerCount++;
                    }
                }
            }
        }
        return true;
    }
}
