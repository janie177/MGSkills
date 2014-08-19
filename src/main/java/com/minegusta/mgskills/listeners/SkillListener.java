package com.minegusta.mgskills.listeners;

import com.minegusta.mgskills.files.LoadToMap;
import com.minegusta.mgskills.files.RemoveFromMap;
import com.minegusta.mgskills.skills.cooking.CakeEatBoost;
import com.minegusta.mgskills.skills.cooking.CookingCraftExperience;
import com.minegusta.mgskills.skills.cooking.CookingSmeltExperience;
import com.minegusta.mgskills.skills.cooking.FoodBoost;
import com.minegusta.mgskills.skills.digging.DiggingBoost;
import com.minegusta.mgskills.skills.digging.DiggingExperience;
import com.minegusta.mgskills.skills.farming.FarmingBreakBlockExperience;
import com.minegusta.mgskills.skills.farming.FarmingInteractBlockExperience;
import com.minegusta.mgskills.skills.farming.FarmingInteractEntityExperience;
import com.minegusta.mgskills.skills.hunting.HuntingExperience;
import com.minegusta.mgskills.skills.mining.InfiniteTorchBoost;
import com.minegusta.mgskills.skills.mining.MiningExp;
import com.minegusta.mgskills.skills.mining.RandomOreBoost;
import com.minegusta.mgskills.skills.woodcutting.SuicideChicken;
import com.minegusta.mgskills.skills.woodcutting.WoodCuttingBoost;
import com.minegusta.mgskills.skills.woodcutting.WoodCuttingExp;
import com.minegusta.mgskills.treasuremaps.TreasureListener;
import com.minegusta.mgskills.util.WorldCheck;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.*;

public class SkillListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerJoinEvent e)
    {
        //Always do this, no matter the world.

        new LoadToMap(e);

    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerQuitEvent e)
    {
        //Always do this, no matter the world.

        new RemoveFromMap(e);

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerFishEvent e)
    {
        if(!worldCheck(e.getPlayer().getWorld()))return;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockBreakEvent e)
    {
        if(!worldCheck(e.getPlayer().getWorld()))return;

        new MiningExp(e);
        new RandomOreBoost(e);
        new WoodCuttingExp(e);
        new WoodCuttingBoost(e);
        new DiggingBoost(e);
        new DiggingExperience(e);
        new FarmingBreakBlockExperience(e);
        new InfiniteTorchBoost(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockPlaceEvent e)
    {
        if(!worldCheck(e.getPlayer().getWorld()))return;

        new InfiniteTorchBoost(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerInteractEvent e)
    {
        if(!worldCheck(e.getPlayer().getWorld()))return;
        new TreasureListener(e);
        new FarmingInteractBlockExperience(e);
        new SuicideChicken(e);
        new CakeEatBoost(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(FurnaceSmeltEvent e)
    {
        if(!worldCheck(e.getBlock().getWorld()))return;
        new CookingSmeltExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(CraftItemEvent e)
    {
        if(!worldCheck(e.getWhoClicked().getWorld()))return;
        new CookingCraftExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerItemConsumeEvent e)
    {
        if(!worldCheck(e.getPlayer().getWorld()))return;
        new FoodBoost(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(EntityDeathEvent e)
    {
        if(!worldCheck(e.getEntity().getWorld()))return;
        new HuntingExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerInteractEntityEvent e)
    {
        if(!worldCheck(e.getPlayer().getWorld()))return;
        new FarmingInteractEntityExperience(e);
    }

    //World Check
    private boolean worldCheck(World world)
    {
        WorldCheck check = new WorldCheck(world);
        return check.check();
    }

}
