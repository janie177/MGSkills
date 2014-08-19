package com.minegusta.mgskills.files;

import com.minegusta.mgskills.highscores.UpdateHighscores;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.YamlUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class LoadToMap
{
    private UUID uuid;
    private FileConfiguration conf;
    private DetailedMPlayer mPlayer;

    public LoadToMap(PlayerJoinEvent e)
    {
        this.uuid = e.getPlayer().getUniqueId();
        this.conf = YamlUtil.getConfiguration("/players/", uuid.toString() + ".yml");
        create();

        new UpdateHighscores(e);
    }

    public LoadToMap(Player p)
    {
        this.uuid = p.getUniqueId();
        this.conf = YamlUtil.getConfiguration("/players/", uuid.toString() + ".yml");
        create();

    }

    private void create()
    {
        //Create the MPlayer object
        this.mPlayer = new DetailedMPlayer(conf, uuid);
        loadToMap();
    }

    private void loadToMap()
    {
        //Load to the TempDataMap
        TempData.pMap.put(uuid, mPlayer);
    }
}
