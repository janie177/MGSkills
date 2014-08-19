package com.minegusta.mgskills.highscores;

import com.minegusta.mgskills.util.YamlUtil;
import org.bukkit.configuration.file.FileConfiguration;

public class HighScoreFile {
    public static FileConfiguration conf;

    public static void loadFile() {
        conf = YamlUtil.getConfiguration("/highscores/", "highscores.yml");
    }

    public static FileConfiguration getConf() {
        return conf;
    }

    public static void saveFile() {
        YamlUtil.saveFile("/highscores/", "highscores.yml", conf);
    }
}
