package com.minegusta.mgskills.files;

import com.google.common.collect.Maps;
import com.minegusta.mgskills.highscores.UpdateHighscores;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.YamlUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class LoadToMap {
    private UUID uuid;
    private FileConfiguration conf;
    private DetailedMPlayer mPlayer;
    private ConcurrentMap<String, Integer> map = Maps.newConcurrentMap();

    public LoadToMap(PlayerJoinEvent e) {
        this.uuid = e.getPlayer().getUniqueId();
        this.conf = YamlUtil.getConfiguration("/players/", uuid.toString() + ".yml");
        create();

        new UpdateHighscores(e);
    }

    public LoadToMap(Player p) {
        this.uuid = p.getUniqueId();
        this.conf = YamlUtil.getConfiguration("/players/", uuid.toString() + ".yml");
        create();

    }

    private void create() {
        //Create the MPlayer object

        map.put("fishing", conf.getInt("fishing", 0));
        map.put("mining", conf.getInt("mining", 0));
        map.put("cooking", conf.getInt("cooking", 0));
        map.put("summoning", conf.getInt("summoning", 0));
        map.put("farming", conf.getInt("farming", 0));
        map.put("hunting", conf.getInt("hunting", 0));
        map.put("woodcutting", conf.getInt("woodcutting", 0));
        map.put("digging", conf.getInt("digging", 0));
        map.put("brewing", conf.getInt("brewing", 0));
        map.put("healing", conf.getInt("healing", 0));
        map.put("exploration", conf.getInt("exploration", 0));

        map.put("fishingLevel", conf.getInt("fishingLevel", 1));
        map.put("miningLevel", conf.getInt("miningLevel", 1));
        map.put("cookingLevel", conf.getInt("cookingLevel", 1));
        map.put("summoningLevel", conf.getInt("summoningLevel", 1));
        map.put("farmingLevel", conf.getInt("farmingLevel", 1));
        map.put("huntingLevel", conf.getInt("huntingLevel", 1));
        map.put("woodcuttingLevel", conf.getInt("woodcuttingLevel", 1));
        map.put("diggingLevel", conf.getInt("diggingLevel", 1));
        map.put("brewingLevel", conf.getInt("brewingLevel", 1));
        map.put("healingLevel", conf.getInt("healingLevel", 1));
        map.put("explorationLevel", conf.getInt("explorationLevel", 1));

        this.mPlayer = new DetailedMPlayer(map, uuid);
        loadToMap();
    }

    private void loadToMap() {
        //Load to the TempDataMap
        TempData.pMap.put(uuid.toString(), mPlayer);
    }
}
