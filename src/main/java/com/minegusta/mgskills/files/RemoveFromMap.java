package com.minegusta.mgskills.files;

import com.minegusta.mgskills.tasks.SaveTask;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class RemoveFromMap {
    private UUID uuid;

    public RemoveFromMap(PlayerQuitEvent e) {
        this.uuid = e.getPlayer().getUniqueId();

        //Remove player from map, is always added anyways so no null check MHUAHHAHAA
        removeFromMap();
    }

    public RemoveFromMap(Player p) {
        this.uuid = p.getUniqueId();

        //Remove player from map, is always added anyways so no null check MHUAHHAHAA
        removeFromMap();
    }

    private void removeFromMap() {
        SaveTask.savePlayer(uuid);
        TempData.removeMPlayer(uuid.toString());
    }
}
