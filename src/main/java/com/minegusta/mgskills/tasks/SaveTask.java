package com.minegusta.mgskills.tasks;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.highscores.HighScoreFile;
import com.minegusta.mgskills.highscores.UpdateHighScoreBoard;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.json.JsonFileUtil;
import com.minegusta.mgskills.util.json.JsonSection;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;

public class SaveTask {
    public static int playerSaveTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run() {
            for (String uuid : TempData.getKeySet()) {
                JsonSection conf = JsonFileUtil.getSection(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid + ".json");
                Map<String, Object> map = TempData.getMPlayer(uuid).serialize();

                for (String s : map.keySet()) {
                    conf.set(s, map.get(s));
                }

                //Save
                JsonFileUtil.saveFile(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid + ".json", conf);
            }
            HighScoreFile.saveFile();
            UpdateHighScoreBoard.updateHighScoreBoard();

        }
    }, 20 * 60, 20 * 300);

    public static void save() {
        for (String uuid : TempData.getKeySet()) {
            JsonSection conf = JsonFileUtil.getSection(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid + ".json");
            Map<String, Object> map = TempData.getMPlayer(uuid).serialize();

            for (String s : map.keySet()) {
                conf.set(s, map.get(s));
            }

            //Save
            JsonFileUtil.saveFile(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid + ".json", conf);
        }
        HighScoreFile.saveFile();
    }

    public static void savePlayer(UUID uuid) {
        JsonSection conf = JsonFileUtil.getSection(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid.toString() + ".json");
        Map<String, Object> map = TempData.getMPlayer(uuid.toString()).serialize();

        for (String s : map.keySet()) {
            conf.set(s, map.get(s));
        }
        //Save
        JsonFileUtil.saveFile(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid.toString() + ".json", conf);
    }
}
