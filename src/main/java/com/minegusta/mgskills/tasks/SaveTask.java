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

public class SaveTask {
    public static int playerSaveTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run() {
            for (String uuid : TempData.getKeySet()) {
                FileConfiguration conf = YamlUtil.getConfiguration("/players/", uuid + ".yml");
                Map<String, Object> map = TempData.getMPlayer(uuid).serialize();

                for (String s : map.keySet()) {
                    conf.set(s, map.get(s));
                }
                //Save
                YamlUtil.saveFile("/players/", uuid + ".yml", conf);
            }
            HighScoreFile.saveFile();
            UpdateHighScoreBoard.updateHighScoreBoard();

        }
    }, 20 * 60, 20 * 300);

    public static void save() {
        for (String uuid : TempData.getKeySet()) {
            FileConfiguration conf = YamlUtil.getConfiguration("/players/", uuid + ".yml");
            Map<String, Object> map = TempData.getMPlayer(uuid).serialize();

            for (String s : map.keySet()) {
                conf.set(s, map.get(s));
            }
            //Save
            YamlUtil.saveFile("/players/", uuid + ".yml", conf);
        }
        HighScoreFile.saveFile();
    }

    public static void savePlayer(UUID uuid) {
        FileConfiguration conf = YamlUtil.getConfiguration("/players/", uuid.toString() + ".yml");
        Map<String, Object> map = TempData.getMPlayer(uuid.toString()).serialize();

        for (String s : map.keySet()) {
            conf.set(s, map.get(s));
        }
        //Save
        YamlUtil.saveFile("/players/", uuid.toString() + ".yml", conf);
    }
}
