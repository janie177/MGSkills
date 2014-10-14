/*
 * Copyright 2014 Alex Bennett & Alexander Chauncey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.minegusta.mgskills.util;

import com.minegusta.mgskills.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtil {
    public static BufferedImage getImageResource(String path) {
        try {
            return ImageIO.read(Main.PLUGIN.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Send an image to a player, in the form of an in-game Map.
     *
     * @param player The player to receive the map.
     * @param image  The image to be converted.
     * @return The MapView the player receives.
     */
    public static MapView sendMapImage(Player player, BufferedImage image) {
        MapView map = Bukkit.createMap(player.getWorld());
        map = ImageRenderer.applyToMap(map, image);
        player.sendMap(map);
        return map;
    }

    /**
     * Scale an image.
     *
     * @param image  The image to manipulate.
     * @param width  The desired width.
     * @param height The desired height.
     * @return The new image.
     * @throws java.io.IOException
     */
    public static BufferedImage scaleImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) width / imageWidth;
        double scaleY = (double) height / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(image, new BufferedImage(width, height, image.getType()));
    }

    static class ImageRenderer extends MapRenderer {
        private BufferedImage image;

        public ImageRenderer(BufferedImage image) {
            this.image = MapPalette.resizeImage(image);
        }

        static MapView applyToMap(MapView map, BufferedImage image) {
            for (MapRenderer renderer : map.getRenderers())
                map.removeRenderer(renderer);

            map.addRenderer(new ImageRenderer(image));

            return map;
        }

        @Override
        public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
            mapCanvas.drawImage(0, 0, image);
        }
    }
}
