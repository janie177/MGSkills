package com.minegusta.mgskills.listeners;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.files.LoadToMap;
import com.minegusta.mgskills.files.RemoveFromMap;
import com.minegusta.mgskills.skills.cooking.CakeEatBoost;
import com.minegusta.mgskills.skills.cooking.CookingCraftExperience;
import com.minegusta.mgskills.skills.cooking.CookingSmeltExperience;
import com.minegusta.mgskills.skills.cooking.FoodBoost;
import com.minegusta.mgskills.skills.digging.DiggingBoost;
import com.minegusta.mgskills.skills.farming.FarmingInteractBlockExperience;
import com.minegusta.mgskills.skills.farming.FarmingInteractEntityExperience;
import com.minegusta.mgskills.skills.hunting.HuntingExperience;
import com.minegusta.mgskills.skills.mining.RandomOreBoost;
import com.minegusta.mgskills.skills.woodcutting.BirdNestBoost;
import com.minegusta.mgskills.skills.woodcutting.SuicideChicken;
import com.minegusta.mgskills.treasuremaps.TreasureListener;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.WorldCheck;
import com.minegusta.mgskills.util.checks.BlockUtil;
import com.minegusta.mgskills.util.checks.ExperienceUtil;
import com.minegusta.mgskills.util.checks.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerJoinEvent e) {
        //Always do this, no matter the world.

        new LoadToMap(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerQuitEvent e) {
        //Always do this, no matter the world.

        new RemoveFromMap(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerFishEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockBreakEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        //Data
        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());
        int exp;
        int level = mp.getLevel(Skill.MINING);
        Player p = e.getPlayer();
        Block b = e.getBlock();


        /**Mining**/
        exp = ExperienceUtil.getMiningExp(e.getBlock().getType(), ItemUtil.hasSilkTouch(p.getItemInHand()));
        //Applying experience and random ore boost
        if(exp != 0)
        {
            if(b.getType().equals(Material.STONE) && RandomNumber.get(10000) < (50 + (level * 5)))
            {
                RandomOreBoost.drop(p, mp);
            }
            mp.addExp(Skill.MINING, exp);
        }
        //Torches at level 100+
        if(exp == 0 && b.getType().equals(Material.TORCH) && level > 99)
        {
            if (p.getInventory().contains(Material.TORCH)) b.setType(Material.AIR);
        }


        /**Woodcutting**/

        boolean isAxe = ItemUtil.isAxe(p.getItemInHand());
        level = mp.getLevel(Skill.WOODCUTTING);
        if(BlockUtil.isLog(b))
        {
            //base exp
            mp.addExp(Skill.WOODCUTTING, 25);

            //Woodcutting speed boost
            if(level > 19 && isAxe)
            {
                int amplifier = level / 20;
                for (PotionEffect pe : p.getActivePotionEffects()) {
                    if (pe.getType().equals(PotionEffectType.FAST_DIGGING) && pe.getAmplifier() <= amplifier - 1) {
                        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    }
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30, (amplifier - 1)));
            }

            //Bird nest check
            if(level > 44 && isAxe && RandomNumber.get(300) == 1)
            {
                new BirdNestBoost(p, b).runNest();
                mp.addExp(Skill.WOODCUTTING, 100);
            }
        }

        /**Digging**/
        exp = ExperienceUtil.getDiggingExp(b);
        level = mp.getLevel(Skill.DIGGING);

        if(exp != 0)
        {
            //Add base exp
            mp.addExp(Skill.DIGGING, exp + level / 5);

            //Check for bonuses
            DiggingBoost.check(p, level, b);
        }

        /**Farming**/
        level = mp.getLevel(Skill.FARMING);
        exp = ExperienceUtil.getFarmingExp(b, ItemUtil.hasSilkTouch(p.getItemInHand()));

        //Applying exp and replant boost
        if(exp != 0)
        {
            //Try replanting
            boolean fullGrown = BlockUtil.tryReplant(level, ItemUtil.isHoe(p.getItemInHand()), b);

            //Boost the exp
            if(fullGrown)
            {
                if (RandomNumber.get(100) <= level * 2) {
                    exp = exp * 2;
                    for (ItemStack is : b.getDrops()) {
                        b.getWorld().dropItemNaturally(b.getLocation(), is);
                    }
                }

                if (level * 2 > 100)
                {
                    if (RandomNumber.get(100) <= (mp.getLevel(Skill.FARMING) * 2) - 100)
                    {
                        for (ItemStack is : b.getDrops())
                        {
                            b.getWorld().dropItemNaturally(b.getLocation(), is);
                        }
                    }
                }
            }

            //apply exp
            mp.addExp(Skill.FARMING, exp);
        }

        //Apple boost
        if(BlockUtil.isLeaves(b) && level > 43 && RandomNumber.get(25) == 1)
        {
            if(RandomNumber.get(5) == 1 && level > 99)
            {
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
            }
            else
            {
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
            }
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockPlaceEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());
        Block b = e.getBlock();

        //Infinite torch placement
        if(b.getType().equals(Material.TORCH) && mp.getLevel(Skill.MINING) > 99)
        {
            if (!e.getPlayer().getInventory().contains(Material.TORCH)) e.getPlayer().getInventory().addItem(new ItemStack(Material.TORCH, 1));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerInteractEvent e) {

        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        //Data
        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());

        int level = mp.getLevel(Skill.FARMING);
        ItemStack is = e.getPlayer().getItemInHand();
        Material m = e.getClickedBlock().getType();

        //Skill checks

        /**treasure**/
        if(is.getType().equals(Material.MAP) && is.hasItemMeta() && is.getItemMeta().hasLore() && is.getItemMeta().getLore().size() == 6 && is.getItemMeta().getLore().get(5).equalsIgnoreCase(ChatColor.GRAY + "Rightclick map at location.")) {
            new TreasureListener(e);
        }

        /**Chicken boom**/
        new SuicideChicken(e);

        /**Cake food boost**/
        if(e.hasBlock() && (m.equals(Material.CAKE_BLOCK) || m.equals(Material.CAKE)) && mp.getLevel(Skill.COOKING) > 67 && mp.getPlayer().getFoodLevel() < 20)
        {
            new CakeEatBoost(e);
        }


        /**Farming**/

        //Farming tree planting grow hand thing
        if (e.hasBlock() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.SAPLING) && level > 74) {
            if(FarmingInteractBlockExperience.makeTree(e.getClickedBlock()))
            {
                mp.addExp(Skill.FARMING, 48);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(FurnaceSmeltEvent e) {
        if (!worldCheck(e.getBlock().getWorld()) || e.isCancelled()) return;

            new CookingSmeltExperience(e);

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(CraftItemEvent e) {
        if (!worldCheck(e.getWhoClicked().getWorld()) || e.isCancelled()) return;
        new CookingCraftExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerItemConsumeEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;
        new FoodBoost(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(EntityDeathEvent e) {
        if (!worldCheck(e.getEntity().getWorld())) return;
        new HuntingExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerInteractEntityEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        EntityType type = e.getRightClicked().getType();
        Material hand = e.getPlayer().getItemInHand().getType();
        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());

        int exp = 0;

        /**Farming**/
        if(type.equals(EntityType.SHEEP) && hand.equals(Material.SHEARS))
        {
            exp = FarmingInteractEntityExperience.woolBonus(e.getRightClicked());
        }


        if(type.equals(EntityType.MUSHROOM_COW) && hand.equals(Material.BOWL))
        {
            exp = FarmingInteractEntityExperience.soupBonus(e.getPlayer());
        }


        if(type.equals(EntityType.COW) && hand.equals(Material.BUCKET))
        {
            exp = FarmingInteractEntityExperience.milkBonus(e.getPlayer());
        }

        //Add experience

        if(exp != 0)
        {
            mp.addExp(Skill.FARMING, exp);
        }
    }

    //World Check
    private boolean worldCheck(World world) {
        return WorldCheck.check(world.getName());
    }

}
