package com.minegusta.mgskills.treasuremaps;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.util.ImageUtil;
import com.minegusta.mgskills.util.RandomNumber;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapView;

import java.util.List;

public class TreasureMapItem
{
    public static ItemStack getNewTreasureMap(World w, Player p) {
        MapView mv = ImageUtil.sendMapImage(p, ImageUtil.getImageResource("TreasureMap.png"));

        ItemStack is = new ItemStack(Material.MAP, 1, mv.getId());
        double x = RandomNumber.get(20000) - 10000;
        double y = RandomNumber.get(62) + 4;
        double z = RandomNumber.get(20000) - 10000;

        ItemMeta meta = is.getItemMeta();
        List<String> lore = Lists.newArrayList(ChatColor.RED + "Treasure Location X,Y,Z:", Double.toString(x), Double.toString(y), Double.toString(z), w.getName(), ChatColor.GRAY + "Rightclick map at location.");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.YELLOW + "Treasure Map");
        is.setItemMeta(meta);

        return is;
    }
}
