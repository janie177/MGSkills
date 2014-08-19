package com.minegusta.mgskills;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.commands.HighScoreCommand;
import com.minegusta.mgskills.commands.ScoreBoardCommand;
import com.minegusta.mgskills.commands.SkillCommand;
import com.minegusta.mgskills.commands.SkillInfoCommand;
import com.minegusta.mgskills.files.DefaultConfig;
import com.minegusta.mgskills.files.OnReload;
import com.minegusta.mgskills.highscores.HighScoreFile;
import com.minegusta.mgskills.listeners.SkillListener;
import com.minegusta.mgskills.skills.hunting.WolfBoost;
import com.minegusta.mgskills.tasks.SaveTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin
{
    public static Plugin PLUGIN;
    private static int SAVETASK;

    @Override
    public void onEnable()
    {
        //plugin
        PLUGIN = this;

        //Listener
        Bukkit.getPluginManager().registerEvents(new SkillListener(), this);

        //Commands
        getCommand("skills").setExecutor(new SkillCommand());
        getCommand("highscore").setExecutor(new HighScoreCommand());
        getCommand("wolf").setExecutor(new WolfBoost());
        getCommand("hsb").setExecutor(new ScoreBoardCommand());

        List<String> skillCommands = Lists.newArrayList("brewing", "cooking", "digging", "exploration", "farming", "fishing","healing", "hunting", "mining", "summoning", "woodcutting");
        for(String s : skillCommands)
        {
            getCommand(s).setExecutor(new SkillInfoCommand());
        }

        //Tasks
        SAVETASK = SaveTask.playerSaveTask;

        //Config

        HighScoreFile.loadFile();
        DefaultConfig.loadConfig();

        //On Reload
        OnReload.reLoadToMap();

    }

    @Override
    public void onDisable()
    {
        //Save one last time
        SaveTask.save();

        //Unregister Tasks
        Bukkit.getScheduler().cancelTask(SAVETASK);


    }
}
