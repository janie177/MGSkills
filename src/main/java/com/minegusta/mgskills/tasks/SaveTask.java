package com.minegusta.mgskills.tasks;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.highscores.HighScoreFile;
import com.minegusta.mgskills.highscores.UpdateHighScoreBoard;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;
import java.util.UUID;

public class SaveTask
{
    public static int playerSaveTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run()
        {
            for(UUID uuid : TempData.pMap.keySet())
            {
                FileConfiguration conf = YamlUtil.getConfiguration("/players/", uuid.toString()+".yml");
                Map<String,Object> map = TempData.pMap.get(uuid).serialize();

                for(String s : map.keySet())
                {
                    conf.set(s, map.get(s));
                }
                //Save
                YamlUtil.saveFile("/players/", uuid.toString()+".yml", conf);
            }
            HighScoreFile.saveFile();
            UpdateHighScoreBoard.updateHighScoreBoard();

        }
    }, 20 * 60, 20 * 300);

    public static void save()
    {
        for(UUID uuid : TempData.pMap.keySet())
        {
            FileConfiguration conf = YamlUtil.getConfiguration("/players/", uuid.toString()+".yml");
            Map<String,Object> map = TempData.pMap.get(uuid).serialize();

            for(String s : map.keySet())
            {
                conf.set(s, map.get(s));
            }
            //Save
            YamlUtil.saveFile("/players/", uuid.toString()+".yml", conf);
        }
        HighScoreFile.saveFile();
    }

    public static void savePlayer(UUID uuid)
    {
        FileConfiguration conf = YamlUtil.getConfiguration("/players/", uuid.toString()+".yml");
        Map<String,Object> map = TempData.pMap.get(uuid).serialize();

        for(String s : map.keySet())
        {
            conf.set(s, map.get(s));
        }
        //Save
        YamlUtil.saveFile("/players/", uuid.toString()+".yml", conf);
    }
}
