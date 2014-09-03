package com.minegusta.mgskills.treasuremaps;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.net.URL;

public class TreasureMapImage extends MapRenderer {
    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        try {
            mapCanvas.drawImage(14, 14, ImageIO.read(new URL("http://minegusta.com/img/TreasureMap.png")));
        } catch (Exception ignored)
        {

        }
    }
}
