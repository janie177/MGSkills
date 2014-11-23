package com.minegusta.mgskills.tasks;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.highscores.HighScoreFile;
import com.minegusta.mgskills.highscores.UpdateHighScoreBoard;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.json.JsonFileUtil;
import org.bukkit.Bukkit;

import java.util.UUID;

public class SaveTask {
    public static int playerSaveTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
        @Override
        public void run() {
            for (String uuid : TempData.getKeySet()) {

                JsonFileUtil.saveFile(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid + ".json", TempData.getMPlayer(uuid).getConf());
            }
            HighScoreFile.saveFile();
            UpdateHighScoreBoard.updateHighScoreBoard();

        }
    }, 20 * 60, 20 * 300);

    public static void save() {
        for (String uuid : TempData.getKeySet()) {
            JsonFileUtil.saveFile(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid + ".json", TempData.getMPlayer(uuid).getConf());
        }
        HighScoreFile.saveFile();
    }

    public static void savePlayer(UUID uuid) {
        JsonFileUtil.saveFile(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid.toString() + ".json", TempData.getMPlayer(uuid.toString()).getConf());
    }
}
