package com.minegusta.mgskills.files;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.highscores.UpdateHighscores;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.json.JsonFileUtil;
import com.minegusta.mgskills.util.json.JsonSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class LoadToMap {
    private UUID uuid;
    private Player p;
    private JsonSection conf;
    private DetailedMPlayer mPlayer;

    public LoadToMap(PlayerJoinEvent e) {
        this.p = e.getPlayer();
        this.uuid = p.getUniqueId();
        this.conf = JsonFileUtil.getSection(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid.toString() + ".json");
        create();

        new UpdateHighscores(e);
    }

    public LoadToMap(Player p) {
        this.p = p;
        this.uuid = p.getUniqueId();
        this.conf = JsonFileUtil.getSection(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid.toString() + ".json");
        create();
    }

    private void create() {
        //Create the MPlayer object

        //Just a small check in case of..
        if (uuid == null) p.kickPlayer("Your UUID is null! This causes plugins to malfunction. Please re-log.");

        this.mPlayer = new DetailedMPlayer(conf, p);
    }

    private void loadToMap() {
        //Load to the TempDataMap
        TempData.addMPlayer(uuid.toString(), mPlayer);
    }
}
