package com.minegusta.mgskills.util;

public class LevelFromExp
{
    public static int getLevel(int exp)
    {
        int level = 1;
        for(ExpTable expTable : ExpTable.values())
        {
            if(expTable.getExp() <= exp)
            {
                level = Integer.parseInt(expTable.name());
            }
            else break;
        }
        return level;
    }
}
