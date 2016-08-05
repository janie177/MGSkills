package com.minegusta.mgskills;

import com.minegusta.mgskills.commands.*;
import com.minegusta.mgskills.files.DefaultConfig;
import com.minegusta.mgskills.files.OnReload;
import com.minegusta.mgskills.highscores.HighScoreFile;
import com.minegusta.mgskills.listeners.SkillListener;
import com.minegusta.mgskills.skills.hunting.WolfBoost;
import com.minegusta.mgskills.tasks.SaveTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Plugin PLUGIN;
    private static int SAVETASK;

    @Override
    public void onEnable() {
        //plugin
        PLUGIN = this;

        //Listener
        Bukkit.getPluginManager().registerEvents(new SkillListener(), this);

        //Commands
        getCommand("skills").setExecutor(new SkillCommand());
        getCommand("highscore").setExecutor(new HighScoreCommand());
        getCommand("wolf").setExecutor(new WolfBoost());
        getCommand("hsb").setExecutor(new ScoreBoardCommand());
        getCommand("alchemy").setExecutor(new AlchemyCommand());

        String[] skillCommands = {"brewing", "cooking", "digging", "exploration", "farming", "fishing", "healing", "hunting", "mining", "magic", "woodcutting"};
        for (String s : skillCommands) {
            getCommand(s).setExecutor(new SkillInfoCommand());
        }

        //Tasks
        SAVETASK = SaveTask.playerSaveTask;

        //Config
        DefaultConfig.loadConfig();
        HighScoreFile.loadFile();

        //On Reload
        OnReload.reLoadToMap();
    }

    @Override
    public void onDisable() {
        //Save one last time
        SaveTask.save();

        //Unregister Tasks
        Bukkit.getScheduler().cancelTask(SAVETASK);
    }
}
